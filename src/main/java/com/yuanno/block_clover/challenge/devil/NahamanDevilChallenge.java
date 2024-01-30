package com.yuanno.block_clover.challenge.devil;

import com.yuanno.block_clover.BeRegistry;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.challenges.*;
import com.yuanno.block_clover.challenge.arena.DevilRitualArena;
import com.yuanno.block_clover.challenge.arena.NahamanDevilRitualArena;
import com.yuanno.block_clover.init.ModEntities;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.spells.devil.CrowAbility;
import com.yuanno.block_clover.spells.devil.DarkFireBallAbility;
import net.minecraftforge.fml.RegistryObject;

public class NahamanDevilChallenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.nahaman", "Nahaman");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.nahaman", "Defeat Nahaman");




    public static final ChallengeCore<NahamanDevilChallenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", NahamanDevilChallenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, NahamanDevilRitualArena.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.NAHAMAN_DEVIL})
            .setTimeLimit(10)
            .setOrder(0)
            .setReward(new ChallengeReward()
                    .addAbility(() -> {
                        Ability ability = new DarkFireBallAbility();
                        return ability;
                    })
                    .addDevil(() -> {
                        String walgner = ModValues.NAHAMAN;
                        return walgner;
                    }))
            .build();
    public NahamanDevilChallenge(ChallengeCore core) {
        super(core);
    }
}
