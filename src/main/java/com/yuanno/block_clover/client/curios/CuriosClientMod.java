package com.yuanno.block_clover.client.curios;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.curios.CuriosApi;
import com.yuanno.block_clover.api.curios.SlotTypePreset;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;


public class CuriosClientMod {

  private static final CuriosSpriteListener SPRITE_LISTENER = new CuriosSpriteListener();

  @SuppressWarnings("ConstantConditions")
  public static void init() {
    Minecraft mc = Minecraft.getInstance();

    if (mc != null) {
      IResourceManager manager = mc.getResourceManager();

      if (manager instanceof IReloadableResourceManager) {
        IReloadableResourceManager reloader = (IReloadableResourceManager) manager;
        reloader.registerReloadListener(SPRITE_LISTENER);
      }
    }
  }

  public static void stitch(final TextureStitchEvent.Pre evt) {

    if (evt.getMap().location() == PlayerContainer.BLOCK_ATLAS) {

      for (SlotTypePreset preset : SlotTypePreset.values()) {
        evt.addSprite(
            new ResourceLocation(CuriosApi.MODID, "item/empty_" + preset.getIdentifier() + "_slot"));
      }
      evt.addSprite(new ResourceLocation(Main.MODID, "item/empty_cosmetic_slot"));
      evt.addSprite(new ResourceLocation(Main.MODID, "item/empty_curio_slot"));

      for (ResourceLocation sprite : SPRITE_LISTENER.getSprites()) {
        evt.addSprite(sprite);
      }
    }
  }
}
