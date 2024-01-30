package com.yuanno.block_clover.init;

import com.yuanno.block_clover.BeRegistry;
import com.yuanno.block_clover.api.challenges.Challenge;
import com.yuanno.block_clover.api.challenges.ChallengeCore;
import com.yuanno.block_clover.challenge.devil.LilithDevilChallenge;
import com.yuanno.block_clover.challenge.devil.NahamanDevilChallenge;
import com.yuanno.block_clover.challenge.devil.WalgnerDevilChallenge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

public class ModChallenges {

    public static final RegistryObject<ChallengeCore<WalgnerDevilChallenge>> WALGNER_DEVIL = registerChallenge(WalgnerDevilChallenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<NahamanDevilChallenge>> NAHAMAN_DEVIL = registerChallenge(NahamanDevilChallenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<LilithDevilChallenge>> LILITH_DEVIL = registerChallenge(LilithDevilChallenge.INSTANCE);

    public static void register(IEventBus bus) {
        BeRegistry.CHALLENGES.register(bus);
    }
    public static <T extends Challenge> RegistryObject<ChallengeCore<T>> registerChallenge(ChallengeCore<T> challenge) {
        RegistryObject<ChallengeCore<T>> reg =  BeRegistry.CHALLENGES.register(challenge.getUnlocalizedTitle(), () -> challenge);
        return reg;
    }


}
