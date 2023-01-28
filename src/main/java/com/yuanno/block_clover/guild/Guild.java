package com.yuanno.block_clover.guild;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Guild {

    private String name;
    private boolean isTemporary;
    private List<Member> members = new ArrayList<>();

    private Guild()
    {

    }

    public Guild(String name, LivingEntity livingEntity)
    {
        this(name, livingEntity.getUUID(), livingEntity.getDisplayName().getString());
    }
    public Guild(String name, UUID capId, String username)
    {
        this.name = name;
        this.isTemporary = true;
        this.addMember(capId, username).setIsCaptain(true);
    }
    public static Guild from(CompoundNBT nbt) {
        Guild guild = new Guild();
        guild.read(nbt);
        return guild;
    }
    public CompoundNBT write()
    {
        CompoundNBT crewNBT = new CompoundNBT();
        crewNBT.putString("name", this.getName());

        ListNBT members = new ListNBT();
        for (Guild.Member member : this.getMembers())
        {
            CompoundNBT memberNBT = new CompoundNBT();
            memberNBT.putUUID("id", member.getUUID());
            memberNBT.putString("username", member.getUsername());
            memberNBT.putBoolean("isCaptain", member.isCaptain());
            memberNBT.putBoolean("isViceCaptain", member.isViceCaptain());
            members.add(memberNBT);
        }
        crewNBT.put("members", members);
        return crewNBT;
    }
    public void read(CompoundNBT nbt)
    {
        String name = nbt.getString("name");
        this.setName(name);

        ListNBT members = nbt.getList("members", Constants.NBT.TAG_COMPOUND);
        for (int j = 0; j < members.size(); j++)
        {
            CompoundNBT memberNBT = members.getCompound(j);
            Guild.Member member = this.addMember(memberNBT.getUUID("id"), memberNBT.getString("username"));
            member.setIsCaptain(memberNBT.getBoolean("isCaptain"));
            member.setIsViceCaptain(memberNBT.getBoolean("isViceCaptain"));
        }

    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
    public boolean isTemporary()
    {
        return this.isTemporary;
    }

    public Member addMember(LivingEntity entity)
    {
        return this.addMember(entity.getUUID(), entity.getDisplayName().getString());
    }
    public Member getMember(UUID id)
    {
        return this.members.stream().filter(member -> member.getUUID().equals(id)).findFirst().orElse(null);
    }

    public boolean hasMember(UUID id)
    {
        return this.getMember(id) != null;
    }

    public void create(World world)
    {
        this.isTemporary = false;
    }
    @Nullable
    public Member getCaptain()
    {
        return this.members.stream().filter(member -> member.isCaptain()).findFirst().orElse(null);
    }

    @Nullable
    public Member getViceCaptain()
    {
        return this.members.stream().filter(member -> member.isViceCaptain()).findFirst().orElse(null);
    }

    public List<Member> getMembers()
    {
        return this.members;
    }
    public void removeMember(UUID id)
    {
        Member member = this.getMember(id);
        if(member == null)
            return;
        if(member.isCaptain())
        {
            Optional<Member> nextCaptain = this.getMembers().stream().filter((mem) -> !mem.isCaptain()).findFirst();
            if(nextCaptain.isPresent())
            {
                member.setIsCaptain(false);
                nextCaptain.get().setIsCaptain(true);
                this.members.remove(member);
            }
            else
            {
                this.members.removeAll(this.members);
            }
        }
        else
            this.members.remove(member);
    }
    public Member addMember(UUID uuid, String username)
    {
        Member member = new Member(uuid, username);
        this.members.add(member);
        return member;
    }
    public static class Member
    {
        private UUID uuid;
        private String username;
        private boolean isCaptain;
        private boolean isViceCaptain;

        public Member(LivingEntity entity)
        {
            this(entity.getUUID(), entity.getDisplayName().getString());
        }

        public Member(UUID uuid, String username)
        {
            this.uuid = uuid;
            this.username = username;
        }

        public Member setIsCaptain(boolean flag)
        {
            this.isCaptain = flag;
            return this;
        }

        public Member setIsViceCaptain(boolean flag)
        {
            this.isViceCaptain = flag;
            return this;
        }

        public boolean isViceCaptain()
        {
            return this.isViceCaptain;
        }

        public boolean isCaptain()
        {
            return this.isCaptain;
        }

        public UUID getUUID()
        {
            return this.uuid;
        }

        public String getUsername()
        {
            return this.username;
        }
    }
}
