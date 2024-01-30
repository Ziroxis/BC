package com.yuanno.block_clover.challenge.devil;

import com.yuanno.block_clover.BeRegistry;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.challenges.*;
import com.yuanno.block_clover.challenge.arena.DevilRitualArena;
import com.yuanno.block_clover.init.ModEntities;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.spells.devil.CrowAbility;
import net.minecraftforge.fml.RegistryObject;

public class WalgnerDevilChallenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.walgner", "Walgner");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.walgner", "Defeat Walgner");




    public static final ChallengeCore<WalgnerDevilChallenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", WalgnerDevilChallenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, DevilRitualArena.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.WALGNER_DEVIL})
            .setTimeLimit(10)
            .setOrder(0)
            .setReward(new ChallengeReward()
                    .addAbility(() -> {
                        Ability ability = new CrowAbility();
                        return ability;
                    })
                    .addDevil(() -> {
                        String walgner = ModValues.WALGNER;
                        return walgner;
                    }))
            .build();
    public WalgnerDevilChallenge(ChallengeCore core) {
        super(core);
    }
}
