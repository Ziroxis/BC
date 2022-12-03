package com.yuanno.block_clover.networking.server;

import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncTriggerQuest {

    private int questId;
    private int entityId;


    public SSyncTriggerQuest() {}

    public SSyncTriggerQuest(int questId, int entityId)
    {
        this.questId = questId;
        this.entityId = entityId;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.questId);
        buffer.writeInt(this.entityId);

    }

    public static SSyncTriggerQuest decode(PacketBuffer buffer)
    {
        SSyncTriggerQuest msg = new SSyncTriggerQuest();
        msg.questId = buffer.readInt();
        msg.entityId = buffer.readInt();
        return msg;
    }

    public static void handle(SSyncTriggerQuest message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                ClientHandler.handle(message);
            });
        }
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SSyncTriggerQuest message)
        {
            Entity target = Minecraft.getInstance().level.getEntity(message.entityId);
            if(target == null || !(target instanceof PlayerEntity))
                return;

            PlayerEntity player = (PlayerEntity) target;
            IQuestData props = QuestDataCapability.get((PlayerEntity) target);
            Quest[] quests = props.getInProgressQuests();
            quests[message.questId].triggerCompleteEvent(player);
            //QuestDataCapability.INSTANCE.getStorage().readNBT(QuestDataCapability.INSTANCE, props, null, message.data);
        }
    }
}
