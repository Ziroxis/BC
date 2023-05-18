package com.yuanno.block_clover.blocks.tileentities.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.blocks.tileentities.DevilAltarTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BookModel;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.EnchantingTableTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class DevilAltarTileEntityRenderer extends TileEntityRenderer<DevilAltarTileEntity> {
    public static final RenderMaterial BOOK_LOCATION = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation("entity/enchanting_table_book"));
    private final BookModel bookModel = new BookModel();

    public DevilAltarTileEntityRenderer(TileEntityRendererDispatcher p_i226010_1_) {
        super(p_i226010_1_);
    }

    public void render(DevilAltarTileEntity entity, float time, MatrixStack p_225616_3_, IRenderTypeBuffer buffer, int var1, int var2) {
        p_225616_3_.pushPose();
        p_225616_3_.translate(0.5D, 0.75D, 0.5D);
        float f = (float)entity.time + time;
        p_225616_3_.translate(0.0D, (double)(0.1F + MathHelper.sin(f * 0.1F) * 0.01F), 0.0D);

        float f1;
        for(f1 = entity.rot - entity.oRot; f1 >= (float)Math.PI; f1 -= ((float)Math.PI * 2F)) {
        }

        while(f1 < -(float)Math.PI) {
            f1 += ((float)Math.PI * 2F);
        }

        float f2 = entity.oRot + f1 * time;
        p_225616_3_.mulPose(Vector3f.YP.rotation(-f2));
        p_225616_3_.mulPose(Vector3f.ZP.rotationDegrees(80.0F));
        float f3 = MathHelper.lerp(time, entity.oFlip, entity.flip);
        float f4 = MathHelper.frac(f3 + 0.25F) * 1.6F - 0.3F;
        float f5 = MathHelper.frac(f3 + 0.75F) * 1.6F - 0.3F;
        float f6 = MathHelper.lerp(time, entity.oOpen, entity.open);
        this.bookModel.setupAnim(f, MathHelper.clamp(f4, 0.0F, 1.0F), MathHelper.clamp(f5, 0.0F, 1.0F), f6);
        IVertexBuilder ivertexbuilder = BOOK_LOCATION.buffer(buffer, RenderType::entitySolid);
        this.bookModel.render(p_225616_3_, ivertexbuilder, var1, var2, 1.0F, 1.0F, 1.0F, 1.0F);
        p_225616_3_.popPose();
    }
}