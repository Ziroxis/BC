package com.yuanno.block_clover.api.challenges;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Interval;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.data.world.ChallengesWorldData;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.init.ModI18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.world.ForgeChunkManager;

import javax.annotation.Nullable;
import java.util.*;


// todo fix this
public class InProgressChallenge {
	
	private static final int COUNTDOWN_SECONDS = 10;
	private static final int COUNTDOWN_TICKS = COUNTDOWN_SECONDS * 20;
	
	private final UUID id;
	private int inProgressTick;
	private Interval readyInterval = Interval.startAtMax(COUNTDOWN_SECONDS); 	 // Both of these two are in seconds due to the tickInterval being
	private Interval endWaitInterval = Interval.startAtMax(COUNTDOWN_SECONDS);  // (at this point in time) set to 20 ticks (equiv of 1 second)
	private Interval.Mutable tickInterval = new Interval.Mutable(5);
	private Phase phase = Phase.BUILD;
	private Result result = Result.TBD;
	private ServerPlayerEntity owner;
	private ServerWorld shard;
	private ChallengeArena arena;
	private BlockPos pos;
	private List<LivingEntity> group;
	private Set<LivingEntity> enemies = new HashSet<>();
	private int tickLimit;
	private long startTick;
	private ChallengeCore<?> core;
	private boolean isFree = false;
	private boolean rewardsAwarded = false;
	
	public InProgressChallenge(UUID id, ServerPlayerEntity owner, ServerWorld shard, List<LivingEntity> group, Challenge challenge, boolean isFree) {
		this.id = id;
		this.owner = owner;
		this.core = challenge.getCore();
		this.shard = shard;
		this.arena = this.core.pickRandomArena();
		this.pos = new BlockPos(0, 90, 0);
		this.group = new ArrayList<>();
		this.group.add(owner);
		this.group.addAll(group);
		this.isFree = isFree;
		
		this.tickLimit = challenge.getCore().getTimeLimit() * 60 * 20;
	}
	
	public void tick() {
		this.inProgressTick++;
		
		/*
		 * Check as frequently as possible if the challengers are dead and if so, clean
		 * the arena and set it up for destruction
		 */
		if (this.phase == Phase.RUN || this.phase == Phase.END) {
			if (this.group.isEmpty()) {
				this.cleanupArena(false);
				this.phase = Phase.POSTEND;
				return;
			}
		}
		
		if(!this.tickInterval.canTick()) {
			return;
		}
		
		if (this.phase.equals(Phase.END)) {
			if (this.endWaitInterval.canTick()) {
				this.phase = Phase.POSTEND;
				ChallengesWorldData.get().stopChallenge(this);
			}
			else {
				if (!this.rewardsAwarded) {
					this.completeChallenge();
					this.rewardsAwarded = true;
				}

				ITextComponent countdownMessage = new StringTextComponent("§f§l" + new TranslationTextComponent("End countdown", this.endWaitInterval.getTick()).getString() + "§r");
				this.sendGroupActionbar(countdownMessage, 5, 20, 5);
			}
			return;
		}
		else if(this.phase.equals(Phase.RUN)) {
			if (this.inProgressTick > this.tickLimit) {
				this.phase = Phase.END;
				this.result = Result.TIMEOUT;
				this.tickInterval.setInterval(20);
				return;
			}
			
			// Checks to see how many group members are still alive
			int groupLeft = 0;
			for (LivingEntity groupMember : this.group) {
				if (groupMember.isAlive() && Beapi.isInChallengeDimension(groupMember.level)) {
					groupLeft++;
					break;
				}
			}

			// If none are alive then we end the challenge as a total defeat
			if (groupLeft <= 0) {
				this.group.clear();
				this.phase = Phase.END;
				this.result = Result.DEATH;
				return;
			}

			// Removing dead enemies from the list, when the list is empty we end the challenge as a victory
			if (!this.enemies.isEmpty()) {
				this.enemies.removeIf(entity -> entity == null || !entity.isAlive());
			}
			else if (this.enemies.isEmpty()) {
				this.phase = Phase.END;
				this.result = Result.WIN;
				this.tickInterval.setInterval(20);
				return;
			}			
		}
		else if(this.phase.equals(Phase.BUILD)) {
			if (this.getCore().getChallengeOffering() != null)
			{
				for (LivingEntity entity : this.getGroup()) {
					if (entity.isAlive() && entity instanceof ServerPlayerEntity) {

						ServerPlayerEntity player = (ServerPlayerEntity) entity;
						IChallengesData challengesData = ChallengesDataCapability.get(player);
						Challenge challenge = challengesData.getChallenge(this.getCore());
						String offering = "";
						offering = challenge.getCore().getChallengeOffering().takeOffering(player);
						player.sendMessage(new StringTextComponent(offering), Util.NIL_UUID);
					}
				}
			}
			this.sendGroupTitle(ModI18n.CHALLENGE_MESSAGE_START_TITLE, ModI18n.CHALLENGE_MESSAGE_START_SUBTITLE, 5, 90, 10);
			long start = System.currentTimeMillis();
			
			this.arena.buildArena(this);
			this.phase = Phase.SPAWN;
			this.tickInterval.setInterval(100);
			
			long end = System.currentTimeMillis() - start;
			System.out.println("BUILD TIME: " + (end / 1000.0f) + "s");
		}
		else if(this.phase.equals(Phase.SPAWN)) {
			/*
			 * Clean any possible old entity, force loading chunks loaded since they're most
			 * likely not,
			 * 
			 * Note that in the previous build step we didn't update any blocks and thus
			 * didn't load the world, we've only replaced palette data, all the actual
			 * loading will be done whenever the player joins the dimension and loads the
			 * chunks nearby. Therefore there are no loaded chunks before the player joins.
			 */
			this.cleanupArena(true);

			// Handles enemy spawns
			this.spawnEnemies();
			
			// Handles player and their group spawns
			this.spawnChallengers();
			
			this.phase = Phase.READY;
			this.tickInterval.setInterval(20);
		}
		else if (this.phase.equals(Phase.READY)) {
			if(this.readyInterval.getTick() <= 6) {
				ITextComponent countdownMessage = new StringTextComponent(TextFormatting.GOLD + "" + (this.readyInterval.getTick() - 1));
				this.sendGroupTitle(countdownMessage, 5, 10, 5);
			}
			if (this.readyInterval.canTick()) {
				this.phase = Phase.RUN;	
				this.group.forEach(entity -> entity.removeEffect(ModEffects.IN_EVENT.get()));
				this.enemies.forEach(entity -> entity.removeEffect(ModEffects.IN_EVENT.get()));
				this.sendGroupTitle(ModI18n.CHALLENGE_MESSAGE_START_FIGHT, 2, 5, 2);
				this.startTick = this.shard.getGameTime();
			}
		}
	}

	private void completeChallenge() {
		for (LivingEntity entity : this.getGroup()) {
			if (entity.isAlive() && entity instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
				IChallengesData props = ChallengesDataCapability.get(player);
				if (props == null) {
					continue;
				}
				
				Challenge challenge = props.getChallenge(this.getCore());
				if (challenge == null) {
					continue;
				}
				
				int time = Math.round((this.shard.getGameTime() - this.startTick) / 20.0f);

				String mode = "";
				if (this.hasRestrictions() && !this.hasActiveRestrictions()) {
					mode = " (FREE)";
				}

				String timeStr = String.format("§2§l%02d:%02d§r%s", time / 60, time % 60, mode);
				String rewards = "";
				if (this.hasRewards() && !challenge.isComplete()) {
					rewards = challenge.getCore().getReward().giveRewards(player);
					challenge.tryUpdateBestTime(time);
					challenge.setComplete(true);
				}
				else if (this.hasRewards() && challenge.isComplete())
				{
					rewards = challenge.getCore().getSecondReward().giveRewards(player);
					challenge.tryUpdateBestTime(time);
					challenge.setComplete(true);
				}
				StringTextComponent reportStr = new StringTextComponent(ModI18n.CHALLENGE_MESSAGE_COMPLETION_REPORT.getString() + "" + timeStr);
				
				player.sendMessage(this.core.getLocalizedTitle(), Util.NIL_UUID);
				player.sendMessage(reportStr, Util.NIL_UUID);
				player.sendMessage(new StringTextComponent(rewards), Util.NIL_UUID);
			}
		}
	}

	public boolean hasRewards() {
		return this.isStandardDifficulty() ? true : this.hasActiveRestrictions();
	}

	public boolean hasRestrictions() {
		return !this.isStandardDifficulty();
	}
	
	public boolean hasActiveRestrictions() {
		return !this.isFree;
	}

	public boolean canDelete() {
		return this.phase == Phase.POSTEND;
	}
	
	private void spawnEnemies() {
		// Spawn new arena specific enemies
		this.enemies = this.arena.spawnEnemies(this);

		/*
		 * Gives all of them 10s of IN_EVENT effect, making them still and unable to use
		 * abilities
		 */
		for (LivingEntity entity : this.enemies) {
			entity.addEffect(new EffectInstance(ModEffects.IN_EVENT.get(), COUNTDOWN_TICKS, 0));
			entity.heal(entity.getMaxHealth());
		}
	}
	
	private void spawnChallengers() {
		/*
		 * Spawn the challenging players, teleports them to the correct dimension and
		 * positions them
		 */
		this.arena.spawnPlayers(this);

		/*
		 * Gives all of them 10s of IN_EVENT effect, making them still and unable to use
		 * abilities as well as stopping any inuse ability and making sure all cooldowns
		 * are ready to be used
		 */
		for (LivingEntity entity : this.group) {
			entity.heal(entity.getMaxHealth());
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).getFoodData().eat(20, 20);
			}
			entity.addEffect(new EffectInstance(ModEffects.IN_EVENT.get(), COUNTDOWN_TICKS, 0));

			/*
			for (IAbility ability : props.getEquippedAbilities()) {
				if (ability == null) {
					continue;
				}

				ability.getComponent(ModAbilityKeys.DISABLE).ifPresent(c -> c.startDisable(entity, 1));
				ability.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(c -> c.stopCooldown(entity));
			}

			 */
			

			// Remove potion effects during Hard mode and above
			if (this.hasRestrictions() && this.hasActiveRestrictions()) {
				Iterator<EffectInstance> iter = entity.getActiveEffectsMap().values().iterator();

				while (iter.hasNext()) {
					Effect effect = iter.next().getEffect();
					if (effect != ModEffects.IN_EVENT.get()) {
						entity.removeEffect(effect.getEffect());
					}
				}
			}
		}
	}

	public void cleanupArena(boolean force) {
		if (force) {
			/*
			 * Forcefully load the chunks otherwise no entities will get returned and the
			 * below code will be useless
			 */
			ForgeChunkManager.forceChunk(this.shard, Main.MODID, this.pos, 0, 0, true, false);
		}

		/*
		 * Get all entities and remove all non-player ones
		 */
		this.shard.getEntities().forEach(entity -> {
			if (!(entity instanceof PlayerEntity)) {
				entity.remove();
			}
		});
	}
	
	private void sendGroupTitle(ITextComponent title, int fadeInTime, int stayTime, int fadeOutTime) {
		this.sendGroupTitle(title, null, fadeInTime, stayTime, fadeOutTime);
	}

	private void sendGroupTitle(ITextComponent title, @Nullable ITextComponent subtitle, int fadeInTime, int stayTime, int fadeOutTime) {
		for (LivingEntity groupMember : this.group) {
			if (groupMember instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = ((ServerPlayerEntity) groupMember);
				try {
					player.connection.send(new STitlePacket(fadeInTime, stayTime, fadeOutTime));
					ITextComponent titleComponent = TextComponentUtils.updateForEntity(player.createCommandSourceStack(), title, player, 0);
					player.connection.send(new STitlePacket(STitlePacket.Type.TITLE, titleComponent));

					if (subtitle != null) {
						ITextComponent subtitleComponent = TextComponentUtils.updateForEntity(player.createCommandSourceStack(), subtitle, player, 0);
						player.connection.send(new STitlePacket(STitlePacket.Type.SUBTITLE, subtitleComponent));
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void sendGroupActionbar(ITextComponent text, int fadeInTime, int stayTime, int fadeOutTime) {
		for (LivingEntity groupMember : this.group) {
			if (groupMember.isAlive() && groupMember instanceof ServerPlayerEntity && Beapi.isInChallengeDimension(groupMember.level)) {
				ServerPlayerEntity player = ((ServerPlayerEntity) groupMember);
				try {
					player.connection.send(new STitlePacket(fadeInTime, stayTime, fadeOutTime));
					ITextComponent titleComponent = TextComponentUtils.updateForEntity(player.createCommandSourceStack(), text, player, 0);
					player.connection.send(new STitlePacket(STitlePacket.Type.ACTIONBAR, titleComponent));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isStandardDifficulty() {
		return this.core.getDifficulty() == ChallengeDifficulty.STANDARD;
	}
	
	public BlockPos getArenaPos() {
		return this.pos;
	}
	
	public UUID getId() {
		return this.id;
	}

	public ChallengeCore getCore() {
		return this.core;
	}
	
	public List<LivingEntity> getGroup() {
		return this.group;
	}
	
	public Set<LivingEntity> getEnemies() {
		return this.enemies;
	}
	
	public ServerPlayerEntity getOwner() {
		return this.owner;
	}
	
	public ServerWorld getShard() {
		return this.shard;
	}

	private enum Phase {
		BUILD,
		SPAWN,
		READY,
		RUN,
		END,
		POSTEND,
		;
	}
	
	private enum Result {
		TBD,
		WIN,
		TIMEOUT,
		DEATH,
		;
	}
}
