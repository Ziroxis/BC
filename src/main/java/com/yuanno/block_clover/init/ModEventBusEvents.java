package com.yuanno.block_clover.init;

import com.yuanno.block_clover.entities.beastial.CloverSharkEntity;
import com.yuanno.block_clover.entities.beastial.FlameBoarEntity;
import com.yuanno.block_clover.entities.beastial.MonkeyEntity;
import com.yuanno.block_clover.entities.devils.LilithDevilEntity;
import com.yuanno.block_clover.entities.devils.NahamanDevilEntity;
import com.yuanno.block_clover.entities.devils.WalgnerDevilEntity;
import com.yuanno.block_clover.entities.humanoid.BanditEntity;
import com.yuanno.block_clover.entities.humanoid.GrimoireMagicianEntity;
import com.yuanno.block_clover.entities.humanoid.QuestBoardManagerEntity;
import com.yuanno.block_clover.entities.misc.VolcanoMonsterEntity;
import com.yuanno.block_clover.entities.summons.earth.EarthGolemEntity;
import com.yuanno.block_clover.entities.summons.earth.EarthMinionEntity;
import com.yuanno.block_clover.entities.summons.earth.EarthSummons;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {
        //MOBS
        event.put(EarthSummons.EARTH_MINION.get(), EarthMinionEntity.setCustomAttributes().build());
        event.put(EarthSummons.EARTH_GOLEM.get(), EarthGolemEntity.setCustomAttributes().build());
        event.put(ModEntities.GRIMOIRE_MAGICIAN.get(), GrimoireMagicianEntity.setCustomAttributes().build());
        event.put(ModEntities.GUILD_ENTITY.get(), GrimoireMagicianEntity.setCustomAttributes().build());
        event.put(ModEntities.BANDIT.get(), BanditEntity.setCustomAttributes().build());
        event.put(ModEntities.VOLCANO_MONSTER.get(), VolcanoMonsterEntity.setCustomAttributes().build());
        event.put(ModEntities.MONKEY_ENTITY.get(), MonkeyEntity.setCustomAttributes().build());
        event.put(ModEntities.CLOVER_SHARK.get(), CloverSharkEntity.setCustomAttributes().build());
        event.put(ModEntities.FLAME_BOAR.get(), FlameBoarEntity.setCustomAttributes().build());
        event.put(ModEntities.WALGNER_DEVIL.get(), WalgnerDevilEntity.setCustomAttributes().build());
        event.put(ModEntities.LILITH_DEVIL.get(), LilithDevilEntity.setCustomAttributes().build());
        event.put(ModEntities.NAHAMAN_DEVIL.get(), NahamanDevilEntity.setCustomAttributes().build());
        event.put(ModEntities.QUESTBOARD_MANAGER.get(), QuestBoardManagerEntity.setCustomAttributes().build());

    }

    /*
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {

        event.getRegistry().registerAll(
                new WitchHatShredAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Main.MODID, "witch_hat_shred_from_witch"))

        );
    }


     */

}