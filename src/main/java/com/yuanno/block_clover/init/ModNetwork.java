package com.yuanno.block_clover.init;

import com.yuanno.block_clover.networking.*;
import com.yuanno.block_clover.networking.client.*;
import com.yuanno.block_clover.networking.server.*;

public class ModNetwork {

    public static void init()
    {
        //CLIENT PACKETS
        PacketHandler.registerPacket(COpenPlayerScreenPacket.class, COpenPlayerScreenPacket::encode, COpenPlayerScreenPacket::decode, COpenPlayerScreenPacket::handle);
        PacketHandler.registerPacket(CChangeCombatBarPacket.class, CChangeCombatBarPacket::encode, CChangeCombatBarPacket::decode, CChangeCombatBarPacket::handle);
        PacketHandler.registerPacket(CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);
        PacketHandler.registerPacket(CToggleCombatModePacket.class, CToggleCombatModePacket::encode, CToggleCombatModePacket::decode, CToggleCombatModePacket::handle);
        PacketHandler.registerPacket(CRemoveAbilityPacket.class, CRemoveAbilityPacket::encode, CRemoveAbilityPacket::decode, CRemoveAbilityPacket::handle);
        PacketHandler.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(CTogglePassiveAbilityPacket.class, CTogglePassiveAbilityPacket::encode, CTogglePassiveAbilityPacket::decode, CTogglePassiveAbilityPacket::handle);
        PacketHandler.registerPacket(CEquipAbilityPacket.class, CEquipAbilityPacket::encode, CEquipAbilityPacket::decode, CEquipAbilityPacket::handle);
        PacketHandler.registerPacket(CGiveItemStackPacket.class, CGiveItemStackPacket::encode, CGiveItemStackPacket::decode, CGiveItemStackPacket::handle);
        PacketHandler.registerPacket(COpenPlayerStatsScreenPacket.class, COpenPlayerStatsScreenPacket::encode, COpenPlayerStatsScreenPacket::decode, COpenPlayerStatsScreenPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsPacket.class, CSyncentityStatsPacket::encode, CSyncentityStatsPacket::decode, CSyncentityStatsPacket::handle);
        PacketHandler.registerPacket(CUpdateQuestStatePacket.class, CUpdateQuestStatePacket::encode, CUpdateQuestStatePacket::decode, CUpdateQuestStatePacket::handle);
        PacketHandler.registerPacket(CSyncQuestDataPacket.class, CSyncQuestDataPacket::encode, CSyncQuestDataPacket::decode, CSyncQuestDataPacket::handle);
        PacketHandler.registerPacket(CCreateGuildPacket.class, CCreateGuildPacket::encode, CCreateGuildPacket::decode, CCreateGuildPacket::handle);
        PacketHandler.registerPacket(CLeaveGuildPacket.class, CLeaveGuildPacket::encode, CLeaveGuildPacket::decode, CLeaveGuildPacket::handle);
        PacketHandler.registerPacket(CKickFromGuildPacket.class, CKickFromGuildPacket::encode, CKickFromGuildPacket::decode, CKickFromGuildPacket::handle);
        PacketHandler.registerPacket(CPromoteGuildPacket.class, CPromoteGuildPacket::encode, CPromoteGuildPacket::decode, CPromoteGuildPacket::handle);
        PacketHandler.registerPacket(CRequestSyncWorldDataPacket.class, CRequestSyncWorldDataPacket::encode, CRequestSyncWorldDataPacket::decode, CRequestSyncWorldDataPacket::handle);
        PacketHandler.registerPacket(CJoinGuildPacket.class, CJoinGuildPacket::encode, CJoinGuildPacket::decode, CJoinGuildPacket::handle);

        //SERVER PACKETS
        PacketHandler.registerPacket(SSyncEntityStatsPacket.class, SSyncEntityStatsPacket::encode, SSyncEntityStatsPacket::decode, SSyncEntityStatsPacket::handle);
        PacketHandler.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(SUpdateEquippedAbilityPacket.class, SUpdateEquippedAbilityPacket::encode, SUpdateEquippedAbilityPacket::decode, SUpdateEquippedAbilityPacket::handle);
        PacketHandler.registerPacket(SOpenPlayerScreenPacket.class, SOpenPlayerScreenPacket::encode, SOpenPlayerScreenPacket::decode, SOpenPlayerScreenPacket::handle);
        PacketHandler.registerPacket(SOpenPlayerStatsScreenPacket.class, SOpenPlayerStatsScreenPacket::encode, SOpenPlayerStatsScreenPacket::decode, SOpenPlayerStatsScreenPacket::handle);
        PacketHandler.registerPacket(SChangeCombatBarPacket.class, SChangeCombatBarPacket::encode, SChangeCombatBarPacket::decode, SChangeCombatBarPacket::handle);
        PacketHandler.registerPacket(SSyncQuestDataPacket.class, SSyncQuestDataPacket::encode, SSyncQuestDataPacket::decode, SSyncQuestDataPacket::handle);
        PacketHandler.registerPacket(SOpenChatPromptScreenPacket.class, SOpenChatPromptScreenPacket::encode, SOpenChatPromptScreenPacket::decode, SOpenChatPromptScreenPacket::handle);
        PacketHandler.registerPacket(SSyncTriggerQuest.class, SSyncTriggerQuest::encode, SSyncTriggerQuest::decode, SSyncTriggerQuest::handle);
        PacketHandler.registerPacket(SOpenGuildScreenpacket.class, SOpenGuildScreenpacket::encode, SOpenGuildScreenpacket::decode, SOpenGuildScreenpacket::handle);
        PacketHandler.registerPacket(SSyncWorldDataPacket.class, SSyncWorldDataPacket::encode, SSyncWorldDataPacket::decode, SSyncWorldDataPacket::handle);
        PacketHandler.registerPacket(SOpenGuildScreenInvitationpacket.class, SOpenGuildScreenInvitationpacket::encode, SOpenGuildScreenInvitationpacket::decode, SOpenGuildScreenInvitationpacket::handle);
        PacketHandler.registerPacket(SUpdateExtraDataPacket.class, SUpdateExtraDataPacket::encode, SUpdateExtraDataPacket::decode, SUpdateExtraDataPacket::handle);
        PacketHandler.registerPacket(SOpenDevilSummoningScreenPacket.class, SOpenDevilSummoningScreenPacket::encode, SOpenDevilSummoningScreenPacket::decode, SOpenDevilSummoningScreenPacket::handle);
        PacketHandler.registerPacket(SummonDevilEntityPacket.class, SummonDevilEntityPacket::encode, SummonDevilEntityPacket::decode, SummonDevilEntityPacket::handle);
        PacketHandler.registerPacket(SOpenAttributeChoiceScreenPacket.class, SOpenAttributeChoiceScreenPacket::encode, SOpenAttributeChoiceScreenPacket::decode, SOpenAttributeChoiceScreenPacket::handle);


        //SYNC
        PacketHandler.registerPacket(ManaSync.class, ManaSync::encode, ManaSync::decode, ManaSync::handle);
        PacketHandler.registerPacket(CursedEnergyMaxSync.class, CursedEnergyMaxSync::encode, CursedEnergyMaxSync::decode, CursedEnergyMaxSync::handle);

    }
}
