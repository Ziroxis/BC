package com.yuanno.block_clover.data.world;

import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.guild.Guild;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.*;

public class ExtendedWorldData extends WorldSavedData {

    private static final String IDENTIFIER = "block_clover";
    private HashMap<String, Guild> guilds = new HashMap<String, Guild>();
    public static Map<String, ExtendedWorldData> loadedExtWorlds = new HashMap<>();
    private List<int[][]> abilityProtections = new ArrayList<int[][]>();
    private static final ExtendedWorldData CLIENT_DATA = new ExtendedWorldData();

    public ExtendedWorldData()
    {
        super(IDENTIFIER);
    }


    public ExtendedWorldData(String identifier)
    {
        super(identifier);
    }
    public static ExtendedWorldData get(IWorld world)
    {
        if (world == null)
            return null;

        ExtendedWorldData worldExt = null;

        if(world instanceof ServerWorld)
        {
            ServerWorld serverWorld = ((ServerWorld)world).getServer().getLevel(World.OVERWORLD);
            worldExt = serverWorld.getDataStorage().computeIfAbsent(ExtendedWorldData::new, IDENTIFIER);
        }
        else
            worldExt = CLIENT_DATA;

        return worldExt;
    }

    public boolean isInsideRestrictedArea(int posX, int posY, int posZ) {
        if (this.abilityProtections.size() <= 0)
            return false;

        for (int[][] area : this.abilityProtections) {
            int[] minPos = area[0];
            int[] maxPos = area[1];

            if (minPos.length <= 0 || maxPos.length <= 0)
                continue;

            if (posX > minPos[0] && posX < maxPos[0] && posY > minPos[1] && posY < maxPos[1] && posZ > minPos[2] && posZ < maxPos[2]) {
                return true;
            }
        }

        return false;
    }

    public static ExtendedWorldData get(World world) {
        if (world == null)
            return null;

        ExtendedWorldData worldExt = null;
        String worldType = "overworld";
        if (loadedExtWorlds.containsKey(worldType)) {
            worldExt = loadedExtWorlds.get(worldType);
            return worldExt;
        }

        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = world.getServer().getLevel(World.OVERWORLD);
            worldExt = serverWorld.getDataStorage().computeIfAbsent(ExtendedWorldData::new, IDENTIFIER);
        } else
            worldExt = new ExtendedWorldData();

        loadedExtWorlds.put(worldType, worldExt);
        return worldExt;
    }

    @Override
    public void load(CompoundNBT nbt) {
        CompoundNBT protectedAreas = nbt.getCompound("protectedAreas");
        this.abilityProtections.clear();
        for (int i = 0; i <= protectedAreas.getAllKeys().size(); i++) {
            int[] minPos = protectedAreas.getIntArray("minPos_" + i);
            int[] maxPos = protectedAreas.getIntArray("maxPos_" + i);

            if (minPos.length == 3 && maxPos.length == 3) {
                this.abilityProtections.add(new int[][]
                        {
                                minPos, maxPos
                        });
            }
        }


        ListNBT guilds = nbt.getList("guilds", Constants.NBT.TAG_COMPOUND);
        this.guilds.clear();
        for (int i = 0; i < guilds.size(); i++)
        {
            CompoundNBT compoundNBT = guilds.getCompound(i);
            Guild guild = Guild.from(compoundNBT);
            this.guilds.put(BeJavapi.getResourceName(guild.getName()), guild);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        CompoundNBT protectedAreas = new CompoundNBT();
        if (this.abilityProtections.size() > 0) {
            int i = 0;
            for (int[][] area : this.abilityProtections) {
                protectedAreas.putIntArray("minPos_" + i, area[0]);
                protectedAreas.putIntArray("maxPos_" + i, area[1]);
                i++;
            }
        }
        nbt.put("protectedAreas", protectedAreas);

        ListNBT guilds = new ListNBT();
        for (Guild guild : this.guilds.values())
        {
            guilds.add(guild.write());
        }
        nbt.put("guilds", guilds);
        return nbt;
    }

    public List<Guild> getGuilds()
    {
        return new ArrayList<>(this.guilds.values());
    }

    @Nullable
    public Guild getGuildWithMember(UUID memID)
    {
        for (Guild guild : this.guilds.values())
        {
            for (Guild.Member member : guild.getMembers())
            {
                if (member.getUUID().equals(memID))
                    return guild;
            }
        }
        return null;
    }

    @Nullable
    public Guild getCrewWithCaptain(UUID capId)
    {
        return this.guilds.values().stream().filter(crew -> crew.getCaptain() != null && crew.getCaptain().getUUID().equals(capId)).findFirst().orElse(null);
    }

    public void removeCrew(Guild crew)
    {
        String key = BeJavapi.getResourceName(crew.getName());
        if(this.guilds.containsKey(key))
            this.guilds.remove(key);
        this.setDirty();
    }

    public void addCrew(Guild crew)
    {
        String key = BeJavapi.getResourceName(crew.getName());
        if(!this.guilds.containsKey(key))
            this.guilds.put(key, crew);
        this.setDirty();
    }

    public void removeCrewMember(Guild crew, UUID uuid)
    {
        crew.removeMember(uuid);
        this.setDirty();
    }

    public void addCrewMember(Guild guild, LivingEntity entity)
    {
        guild.addMember(entity);
        this.setDirty();
    }
}

