package com.example.block_clover.api.ability;

import com.example.block_clover.Main;
import com.example.block_clover.api.Beapi;
import com.example.block_clover.data.entity.EntityStatsCapability;
import com.example.block_clover.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.IExtensibleEnum;

import javax.annotation.Nullable;
import java.util.function.Function;

public class AbilityCategories {


    private static final Function<PlayerEntity, ResourceLocation> GET_ATTRIBUTE_ICON = (player) ->
    {
        IEntityStats props = EntityStatsCapability.get(player);
        String iconName = props.getAttribute();
        ResourceLocation icon = null;
        icon = new ResourceLocation(Main.MODID, "textures/gui/attributes/" + Beapi.getResourceName(iconName) + ".png");

        return icon;
    };

    private static final Function<PlayerEntity, ResourceLocation> GET_DEVIL_ICON = (player) ->
    {
        IEntityStats props = EntityStatsCapability.get(player);
        String iconName = props.getAttribute();
        ResourceLocation icon = null;
        icon = new ResourceLocation(Main.MODID, "textures/gui/attributes/devil.png");

        return icon;
    };

    private static final Function<PlayerEntity, ResourceLocation> GET_SPIRIT_ICON = (player) ->
    {
        IEntityStats props = EntityStatsCapability.get(player);
        String iconName = props.getAttribute();
        ResourceLocation icon = null;
        icon = new ResourceLocation(Main.MODID, "textures/gui/attributes/spirit.png");

        return icon;
    };





    public static enum AbilityCategory implements IExtensibleEnum
    {
        ATTRIBUTE(GET_ATTRIBUTE_ICON),
        SPIRIT,
        DEVIL,
        ALL;

        private Function<PlayerEntity, ResourceLocation> iconFunction;

        private AbilityCategory()
        {
            this.iconFunction = null;
        }

        private AbilityCategory(Function<PlayerEntity, ResourceLocation> function)
        {
            this.iconFunction = function;
        }

        @Nullable
        public ResourceLocation getIcon(PlayerEntity player)
        {
            if(this.iconFunction == null)
                return null;
            return this.iconFunction.apply(player);
        }

        public static AbilityCategory create(String name, Function<PlayerEntity, ResourceLocation> function)
        {
            throw new IllegalStateException("Enum not extended");
        }
    }


}
