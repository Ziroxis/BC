package com.yuanno.block_clover.challenge.devil;

import com.yuanno.block_clover.BeRegistry;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.challenges.*;
import com.yuanno.block_clover.challenge.arena.DevilRitualArena;
import com.yuanno.block_clover.challenge.arena.WalgnerDevilRitualArena;
import com.yuanno.block_clover.init.ModEntities;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.spells.devil.CrowAbility;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;

public class WalgnerDevilChallenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.walgner", "Walgner");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.walgner", "Defeat Walgner");




    public static final ChallengeCore<WalgnerDevilChallenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", WalgnerDevilChallenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, WalgnerDevilRitualArena.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.WALGNER_DEVIL})
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
                        Ability ability = new CrowAbility();
                        return ability;
                    })
                    .addDevil(() -> {
                        String walgner = ModValues.WALGNER;
                        return walgner;
                    })
            )
            .build();
    public WalgnerDevilChallenge(ChallengeCore core) {
        super(core);
    }
}
