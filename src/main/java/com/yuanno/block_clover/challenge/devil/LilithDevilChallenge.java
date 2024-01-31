package com.yuanno.block_clover.challenge.devil;

import com.yuanno.block_clover.BeRegistry;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.challenges.*;
import com.yuanno.block_clover.challenge.arena.DevilRitualArena;
import com.yuanno.block_clover.challenge.arena.LilithDevilRitualArena;
import com.yuanno.block_clover.init.ModEntities;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.spells.devil.CrowAbility;
import com.yuanno.block_clover.spells.devil.DarkIceAbility;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;

public class LilithDevilChallenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.lilith", "Lilith");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.lilith", "Defeat Lilith");




    public static final ChallengeCore<LilithDevilChallenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", LilithDevilChallenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, LilithDevilRitualArena.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.LILITH_DEVIL})
            .setTimeLimit(10)
            .setOrder(0)
            .setOffering(new ChallengeOffering()
                    .addItemsToRemove(new ArrayList<>(Arrays.asList(
                            () -> new ItemStack(Items.ENCHANTED_GOLDEN_APPLE),
                            () -> new ItemStack(Items.NETHER_STAR),
                            () -> new ItemStack(Items.DRAGON_EGG)
                    )))
            )
            .setReward(new ChallengeReward()
                    .addAbility(() -> {
                        Ability ability = new DarkIceAbility();
                        return ability;
                    })
                    .addDevil(() -> {
                        String walgner = ModValues.LILITH;
                        return walgner;
                    })
            )
            .build();
    public LilithDevilChallenge(ChallengeCore core) {
        super(core);
    }
}
