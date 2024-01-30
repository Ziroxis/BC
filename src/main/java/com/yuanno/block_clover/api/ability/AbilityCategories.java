package com.yuanno.block_clover.api.ability;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
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

    private static final Function<PlayerEntity, ResourceLocation> GET_SECOND_ATTRIBUTE_ICON = (player) ->
    {
        IEntityStats props = EntityStatsCapability.get(player);
        String iconName = props.getSecondAttribute();
        ResourceLocation icon = null;
        icon = new ResourceLocation(Main.MODID, "textures/gui/attributes/" + Beapi.getResourceName(iconName) + ".png");

        return icon;
    };

    private static final Function<PlayerEntity, ResourceLocation> GET_MISCELLANEOUS_ICON = (player) ->
    {
        IEntityStats props = EntityStatsCapability.get(player);
        String iconName = props.getAttribute();
        ResourceLocation icon = null;
        icon = new ResourceLocation(Main.MODID, "textures/gui/attributes/misc.png");

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
        ATTRIBUTE(GET_ATTRIBUTE_ICON, GET_SECOND_ATTRIBUTE_ICON),
        MISCELLANEOUS(GET_MISCELLANEOUS_ICON),
        SPIRIT,
        DEVIL(GET_DEVIL_ICON),
        ALL;

        private Function<PlayerEntity, ResourceLocation> iconFunction;
        private Function<PlayerEntity, ResourceLocation> secondIconFunction;

        private AbilityCategory()
        {
            this.iconFunction = null;
            this.secondIconFunction = null;
        }

        private AbilityCategory(Function<PlayerEntity, ResourceLocation> function)
        {
            this.iconFunction = function;
        }


        private AbilityCategory(Function<PlayerEntity, ResourceLocation> getAttributeIcon, Function<PlayerEntity, ResourceLocation> getSecondAttributeIcon)
        {
            this.iconFunction = getAttributeIcon;
            this.secondIconFunction = getSecondAttributeIcon;
        }



        @Nullable
        public ResourceLocation getIcon(PlayerEntity player)
        {
            if(this.iconFunction == null)
                return null;
            return this.iconFunction.apply(player);
        }
        @Nullable
        public ResourceLocation getSecondIcon(PlayerEntity player)
        {

            if(this.secondIconFunction == null)
                return null;
            return this.secondIconFunction.apply(player);
        }

        public static AbilityCategory create(String name, Function<PlayerEntity, ResourceLocation> function)
        {
            throw new IllegalStateException("Enum not extended");
        }
    }


}
