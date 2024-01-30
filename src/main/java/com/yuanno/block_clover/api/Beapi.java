package com.yuanno.block_clover.api;


import com.yuanno.block_clover.BlockProtectionRule;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.Quest.QuestId;
import com.yuanno.block_clover.init.ModRegistry;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuanno.block_clover.init.ModValues;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
import net.minecraft.network.play.server.SSpawnParticlePacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.template.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.yuanno.block_clover.api.ability.AbilityHelper.placeBlockIfAllowed;

public class Beapi
{



    public static void sendApplyEffectToAllNearby(LivingEntity player, Vector3d pos, int distance, EffectInstance effect) {
        player.getServer().getPlayerList().broadcast(null, pos.x, pos.y, pos.z, distance, player.getCommandSenderWorld().dimension(), new SPlayEntityEffectPacket(player.getId(), effect));
    }

    public static void sendRemoveEffectToAllNearby(LivingEntity player, Vector3d pos, int distance, Effect effect) {
        player.getServer().getPlayerList().broadcast(null, pos.x, pos.y, pos.z, distance, player.getCommandSenderWorld().dimension(), new SRemoveEntityEffectPacket(player.getId(), effect));
    }

    public static boolean isInChallengeDimension(World world) {
        return isInChallengeDimension(world.dimension());
    }

    public static boolean isInChallengeDimension(RegistryKey<World> world) {
        return world.location().toString().contains("challenges_");
    }
    public static String formatTimeMMSS(long time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }

    public static List<QuestId> randomQuestsFromList(List<QuestId> questList, int numberQuests)
    {
        Collections.shuffle(questList);

        return questList.subList(0, numberQuests);
    }
    public static void drawIcon(ResourceLocation rs, int x, int y, int z, int u, int v, float red, float green, float blue)
    {
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        Minecraft.getInstance().getTextureManager().bind(rs);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
        bufferbuilder.vertex(x, y + v, z).color(red, green, blue, 1f).uv(0.0f, 1.0f).endVertex();
        bufferbuilder.vertex(x + u, y + v, z).color(red, green, blue, 1f).uv(1.0f, 1.0f).endVertex();
        bufferbuilder.vertex(x + u, y, z).color(red, green, blue, 1f).uv(1.0f, 0.0f).endVertex();
        bufferbuilder.vertex(x, y, z).color(red, green, blue, 1f).uv(0f, 0f).endVertex();
        Tessellator.getInstance().end();
    }

    public static boolean loadNBTStructure(ServerWorld world, String name, BlockPos pos, PlacementSettings settings)
    {
        if (!world.isClientSide)
        {
            TemplateManager templatemanager = world.getStructureManager();
            ResourceLocation res = new ResourceLocation(Main.MODID, name);

            Template template;
            try
            {
                template = templatemanager.get(res);
            }
            catch (ResourceLocationException ex)
            {
                ex.printStackTrace();
                return false;
            }

            if (template == null)
            {
                return false;
            }
            else
            {
                BlockState blockstate = world.getBlockState(pos);
                world.sendBlockUpdated(pos, blockstate, blockstate, 3);
            }

            //placementsettings.clearProcessors().addProcessor(new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.SAND)));

            template.placeInWorldChunk(world, pos, settings, new Random(Util.getMillis()));
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Vector3d Propulsion(LivingEntity entity, double extraVelX, double extraVelY, double extraVelZ)
    {
        return entity.getLookAngle().multiply(extraVelX, extraVelY, extraVelZ);
    }

    public static String randomAttributeString()
    {
        String attribute = "";

        List<List<String>> elementalAttributes = new ArrayList<>();

        List<String> elemental = Arrays.asList(ModValues.DARKNESS, ModValues.EARTH, ModValues.FIRE, ModValues.LIGHT, ModValues.WATER, ModValues.WIND);
        elementalAttributes.add(elemental);
        List<String> subElemental = Arrays.asList(ModValues.LIGHTNING, ModValues.MERCURY);
        elementalAttributes.add(subElemental);

        List<List<String>> arcaneAttributes = new ArrayList<>();
        List<String> normalArcane = Arrays.asList(ModValues.BEAST, ModValues.SEALING, ModValues.SLASH);
        arcaneAttributes.add(normalArcane);
        List<String> specialArcane = Arrays.asList(ModValues.COPY, ModValues.SWORD, ModValues.TIME);
        arcaneAttributes.add(specialArcane);

        List<String> specialCase = Arrays.asList(ModValues.ANTIMAGIC);

        List<List<String>> allMixedAttributes = new ArrayList<>();
        allMixedAttributes.addAll(elementalAttributes);
        allMixedAttributes.addAll(arcaneAttributes);

        double groupElementalWeight = 0.5;
        double groupArcaneWeight = 0.5;

        // randomly select between group A and group B based on their weights
        List<List<String>> chosenGroup = Math.random() < groupElementalWeight ? elementalAttributes : arcaneAttributes;

        // set weights for each subgroup
        double subgroupNormalArcaneWeight = 0.7;
        double subgroupSpecialArcaneWeight = 0.3;
        double subgroupElementalWeight = 0.6;
        double subgroupSubElementalWeight = 0.4;

        // randomly select between the two subgroups within the chosen group based on their weights
        List<String> chosenSubgroup;
        if (chosenGroup == arcaneAttributes) {
            chosenSubgroup = Math.random() < subgroupNormalArcaneWeight ? normalArcane : specialArcane;
        } else {
            chosenSubgroup = Math.random() < subgroupElementalWeight ? elemental : subElemental;
        }

        // randomly select an attribute from the chosen subgroup
        String chosenAttribute = chosenSubgroup.get((int) (Math.random() * chosenSubgroup.size()));

        // randomly select an attribute from the specialCase list with a 5% chance
        if (Math.random() < 0.05) {
            chosenAttribute = specialCase.get((int) (Math.random() * specialCase.size()));
        }

        attribute = chosenAttribute;
        return attribute;
    }

    public static ArrayList<String> randomAttributes()
    {
        ArrayList<String> chosenAttributes = new ArrayList<String>();
        String firstAttribute = randomAttributeString();
        chosenAttributes.add(firstAttribute);

        String secondAttribute;
        do {secondAttribute = randomAttributeString();}
        while (chosenAttributes.contains(secondAttribute));
        chosenAttributes.add(secondAttribute);
        String thirdAttribute;
        do {thirdAttribute = randomAttributeString();}
        while (chosenAttributes.contains(thirdAttribute));
        chosenAttributes.add(thirdAttribute);
        return chosenAttributes;
    }
    public static BlockPos findOnGroundSpawnLocation(World world, EntityType type, BlockPos spawnLocation, int radius)
    {
        return findOnGroundSpawnLocation(world, type, spawnLocation, radius, 0);
    }

    @Nullable
    public static BlockPos findOnGroundSpawnLocation(World world, EntityType type, BlockPos spawnLocation, int radius, int offset)
    {
        BlockPos blockpos = null;
        for (int i = 0; i < 10; ++i)
        {
            int x = (int) BeJavapi.randomWithRange((spawnLocation.getX() - offset) - radius, (spawnLocation.getX() + offset) + radius);
            int z = (int) BeJavapi.randomWithRange((spawnLocation.getZ() - offset) - radius, (spawnLocation.getZ() + offset) + radius);
            int y = world.getHeight(Heightmap.Type.WORLD_SURFACE, x, z);
            BlockPos blockpos1 = new BlockPos(x, y, z);
            if (WorldEntitySpawner.canSpawnAtBody(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, world, blockpos1, type))
            {
                blockpos = blockpos1;
                break;
            }
        }

        return blockpos;
    }



    public static <T extends Entity> List<T> getEntitiesAround(BlockPos pos, World world, double diameter)
    {
        return (List<T>) getEntitiesAround(pos, world, diameter, LivingEntity.class);
    }

    public static <T extends Entity> List<T> getEntitiesAround(BlockPos pos, World world, double diameter, Class<? extends T>... classEntities)
    {
        return getEntitiesAround(pos, world, diameter, null, classEntities);
    }

    public static <T extends Entity> List<T> getEntitiesAround(BlockPos pos, World world, double diameter, Predicate<Entity> predicate, Class<? extends T>... classEntities)
    {
        if(predicate != null)
            predicate = predicate.and(EntityPredicates.NO_SPECTATORS);
        else
            predicate = EntityPredicates.NO_SPECTATORS;

        AxisAlignedBB aabb = new AxisAlignedBB(pos.getX() - (diameter / 2d), pos.getY() - (diameter / 2d), pos.getZ() - (diameter / 2d), pos.getX() + (diameter / 2d), pos.getY() + (diameter / 2d), pos.getZ() + (diameter / 2d));
        List<T> list = new ArrayList<T>();
        for (Class<? extends T> clzz : classEntities)
        {
            //list.addAll(world.getEntitiesOfClass(clzz, aabb, predicate));
            list.addAll(world
                    .getEntitiesOfClass(clzz, aabb, predicate) .stream().sorted(new Object() {
                        Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                            return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                        }
                    }.compareDistOf(pos.getX(), pos.getY(), pos.getZ())).collect(Collectors.toList()));
        }

        return list;
    }

    public static double randomDouble()
    {
        return new Random().nextDouble() * 2 - 1;
    }

    public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ)
    {
        IPacket<?> ipacket = new SSpawnParticlePacket(data, true, (float) posX, (float) posY, (float) posZ, 0, 0, 0, 0, 1);

        for (int j = 0; j < world.players().size(); ++j)
        {
            ServerPlayerEntity player = world.players().get(j);
            BlockPos blockpos = new BlockPos(player.getX(), player.getY(), player.getZ());
            if (blockpos.closerThan(new Vector3d(posX, posY, posZ), 512))
            {
                player.connection.send(ipacket);
            }
        }
    }
    public static void drawColourOnScreen(int colour, int alpha, double posX, double posY, double width, double height, double zLevel)
    {
        int r = (colour >> 16 & 0xff);
        int g = (colour >> 8 & 0xff);
        int b = (colour & 0xff);
        drawColourOnScreen(r, g, b, alpha, posX, posY, width, height, zLevel);
    }

    public static void drawColourOnScreen(int red, int green, int blue, int alpha, double posX, double posY, double width, double height, double zLevel)
    {
        if (width <= 0 || height <= 0)
            return;
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.vertex(posX, posY + height, zLevel).color(red, green, blue, alpha).endVertex();
        bufferbuilder.vertex(posX + width, posY + height, zLevel).color(red, green, blue, alpha).endVertex();
        bufferbuilder.vertex(posX + width, posY, zLevel).color(red, green, blue, alpha).endVertex();
        bufferbuilder.vertex(posX, posY, zLevel).color(red, green, blue, alpha).endVertex();
        Tessellator.getInstance().end();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }


    public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius, Predicate<Entity> predicate, Class<? extends T>... classEntities)
    {
        if(predicate != null)
            predicate = predicate.and(EntityPredicates.NO_SPECTATORS);
        else
            predicate = EntityPredicates.NO_SPECTATORS;

        AxisAlignedBB aabb = new AxisAlignedBB(pos).expandTowards(radius, radius, radius);
        List<T> list = new ArrayList<T>();
        for (Class<? extends T> clzz : classEntities)
        {
            list.addAll(world.getEntitiesOfClass(clzz, aabb, predicate));
        }
        return list;
    }


    public static boolean isPosClearForPlayer(World world, BlockPos pos)
    {
        return (world.isEmptyBlock(pos) || world.getBlockState(pos).getCollisionShape(world, pos).isEmpty())
                && (world.isEmptyBlock(pos.above()) || world.getBlockState(pos.above()).getCollisionShape(world, pos.above()).isEmpty());
    }

    public static BlockPos rayTraceBlockSafe(PlayerEntity player, float range)
    {
        World world = player.level;
        Vector3d startVec = player.position().add(0.0, player.getEyeHeight(), 0.0);
        Vector3d endVec = startVec.add(player.getLookAngle().scale(range));
        BlockRayTraceResult result = world.clip(new RayTraceContext(startVec, endVec,  RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, player));
        BlockPos dashPos = result.getDirection().equals(Direction.DOWN) ? result.getBlockPos().below(2) : result.getBlockPos().offset(result.getDirection().getNormal());

        boolean posIsFree = Beapi.isPosClearForPlayer(world, dashPos);
        boolean tryUp = true;

        while (!posIsFree)
        {
            if(tryUp)
            {
                dashPos = dashPos.above();
                BlockPos bpb = dashPos.below();
                Vector3d v3d = new Vector3d(bpb.getX(), bpb.getY(), bpb.getZ());
                posIsFree = Beapi.isPosClearForPlayer(world, dashPos) && world.clip(new RayTraceContext(startVec, v3d,
                        RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, player)).getType().equals(RayTraceResult.Type.MISS);
                if(world.getMaxBuildHeight() >= dashPos.getY())
                    tryUp = false;
            } else
            {
                dashPos = dashPos.below();
                BlockPos bpa = dashPos.above();
                Vector3d v3d = new Vector3d(bpa.getX(), bpa.getY(), bpa.getZ());
                posIsFree = Beapi.isPosClearForPlayer(world, dashPos) && world.clip(new RayTraceContext(startVec, v3d,
                        RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, player)).getType().equals(RayTraceResult.Type.MISS);
                if(dashPos.getY() <= 0)
                    break;
            }
        }

        return (posIsFree) ? dashPos : null;
    }

    public static Vector3d propulsion(LivingEntity entity, double extraVelX, double extraVelZ)
    {
        return propulsion(entity, extraVelX, 0, extraVelZ);
    }


    public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius, Class<? extends T>... classEntities)
    {
        AxisAlignedBB aabb = new AxisAlignedBB(pos).expandTowards(radius, radius, radius);
        List<T> list = new ArrayList<T>();
        for (Class<? extends T> clzz : classEntities)
        {
            list.addAll(world.getEntitiesOfClass(clzz, aabb));
        }
        return list;
    }


    public static Vector3d propulsion(LivingEntity entity, double extraVelX, double extraVelY, double extraVelZ)
    {
        return entity.getLookAngle().multiply(extraVelX, extraVelY, extraVelZ);
    }

    public static final int getIndexOfItemStack(Item item, IInventory inven)
    {
        for (int i = 0; i < inven.getContainerSize(); i++)
        {
            if (inven.getItem(i).getItem() == item)
            {
                return i;
            }
        }
        return -1;
    }


    public static boolean isDebug()
    {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    }

    private static HashMap<String, String> langMap = new HashMap<String, String>();

    public static HashMap<String, String> getLangMap()
    {
        return langMap;
    }

    public static List<String> splitString(FontRenderer font, String text, int posX, int wrapWidth)
    {
        while (text != null && text.endsWith("\n"))
        {
            text = text.substring(0, text.length() - 1);
        }

        List<String> newText = new ArrayList<String>();

        //for (IReorderingProcessor proc : font.split(new StringTextComponent(text), wrapWidth))
        //{
        //}

		/*for (String s : font.listFormattedStringToWidth(text, wrapWidth))
		{
			if (font.isBidirectional())
			{
				int i = font.width(font.bidirectionalShaping(s));
				posX += wrapWidth - i;
			}

			newText.add(s);
		}*/

        return newText;
    }

    public static void drawStringWithBorder(FontRenderer font, MatrixStack matrixStack, IReorderingProcessor text, int posX, int posY, int color)
    {
        matrixStack.pushPose();
        font.drawShadow(matrixStack, text, posX, posY - 0.7f, 1);
        font.drawShadow(matrixStack, text, posX, posY + 0.7f, 1);
        font.drawShadow(matrixStack, text, posX + 0.7f, posY, 1);
        font.drawShadow(matrixStack, text, posX - 0.7f, posY, 1);
        matrixStack.translate(0, 0, 1);
        font.draw(matrixStack, text, posX, posY, color);
        matrixStack.popPose();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
    }

    public static void drawIcon(Matrix4f matrix, ResourceLocation rs, int x, int y, int u, int v)
    {
        Minecraft.getInstance().getTextureManager().bind(rs);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(matrix, x, y + v, 1).uv(0.0f, 1.0f).endVertex();
        bufferbuilder.vertex(matrix, x + u, y + v, 1).uv(1.0f, 1.0f).endVertex();
        bufferbuilder.vertex(matrix, x + u, y, 1).uv(1.0f, 0.0f).endVertex();
        bufferbuilder.vertex(matrix, x, y, 1).uv(0.0f, 0.0f).endVertex();
        Tessellator.getInstance().end();
    }

    public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v)
    {
        Minecraft.getInstance().getTextureManager().bind(rs);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(x, y + v, 1).uv(0.0f, 1.0f).endVertex();
        bufferbuilder.vertex(x + u, y + v, 1).uv(1.0f, 1.0f).endVertex();
        bufferbuilder.vertex(x + u, y, 1).uv(1.0f, 0.0f).endVertex();
        bufferbuilder.vertex(x, y, 1).uv(0.0f, 0.0f).endVertex();
        Tessellator.getInstance().end();
    }

    public static Color hexToRGB(String hexColor)
    {
        if (hexColor.startsWith("#"))
            hexColor = hexColor.substring(1);

        if (hexColor.length() == 8)
        {
            int r = Integer.parseInt(hexColor.substring(0, 2), 16);
            int g = Integer.parseInt(hexColor.substring(2, 4), 16);
            int b = Integer.parseInt(hexColor.substring(4, 6), 16);
            int a = Integer.parseInt(hexColor.substring(6, 8), 16);
            return new Color(r, g, b, a);
        }
        else
            return Color.decode("#" + hexColor);
    }

    public static RayTraceResult rayTraceBlocksAndEntities(Entity entity) {
        return Beapi.rayTraceBlocksAndEntities(entity, 1024, 0.4f);
    }

    public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance) {
        return Beapi.rayTraceBlocksAndEntities(entity, distance, 0.2f);
    }

    public static void drawStringWithBorder(FontRenderer font, MatrixStack matrixStack, String text, int posX, int posY, int color)
    {
        matrixStack.pushPose();
        String unformattedText = escapeTextFormattingChars(text);
        font.drawShadow(matrixStack, unformattedText, posX, posY - 0.7f, 0);
        font.drawShadow(matrixStack, unformattedText, posX, posY + 0.7f, 0);
        font.drawShadow(matrixStack, unformattedText, posX + 0.7f, posY, 0);
        font.drawShadow(matrixStack, unformattedText, posX + 0.7f, posY, 0);
        font.drawShadow(matrixStack, unformattedText, posX - 0.7f, posY, 0);
        matrixStack.translate(0, 0, 1);
        font.draw(matrixStack, unformattedText, posX, posY, -1);
        matrixStack.popPose();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
    }

    public static void drawStringWithBorderLingering(FontRenderer font, MatrixStack matrixStack, String text, int posX, int posY, int color)
    {
        matrixStack.pushPose();
        String unformattedText = escapeTextFormattingChars(text);
        font.drawShadow(matrixStack, unformattedText, posX, posY - 0.7f, 0);
        font.drawShadow(matrixStack, unformattedText, posX, posY + 0.7f, 0);
        font.drawShadow(matrixStack, unformattedText, posX + 0.7f, posY, 0);
        font.drawShadow(matrixStack, unformattedText, posX - 0.7f, posY, 0);
        matrixStack.translate(0, 0, 1);
        font.draw(matrixStack, unformattedText, posX, posY, 10);
        matrixStack.last();
        matrixStack.popPose();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
    }

    public static String escapeTextFormattingChars(String text)
    {
        return text.replaceAll("§[0-9a-f]", "");
    }

    public static EntityRayTraceResult rayTraceEntities(Entity entity, double distance, float entityBoxRange) {
        Vector3d lookVec = entity.getLookAngle();
        Vector3d startVec = entity.position().add(0, entity.getEyeHeight(), 0);
        Vector3d endVec = startVec.add(entity.getLookAngle().scale(distance));
        EntityRayTraceResult entityResult = null;

        for (int i = 0; i < distance * 2; i++)
        {
            if (entityResult != null)
                break;

            float scale = i / 2F;
            Vector3d pos = startVec.add(lookVec.scale(scale));

            Vector3d min = pos.add(entityBoxRange, entityBoxRange, entityBoxRange);
            Vector3d max = pos.add(-entityBoxRange, -entityBoxRange, -entityBoxRange);
            List<Entity> list = entity.level.getEntities(entity, new AxisAlignedBB(min.x, min.y, min.z, max.x, max.y, max.z));
            list.remove(entity);
            for (Entity e : list) {
                entityResult = new EntityRayTraceResult(e, pos);
                break;
            }
        }

        return entityResult;

    }

    public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, boolean hollow, final Block block, int flags, BlockProtectionRule rule)
    {
        return Beapi.createSphere(world, center, radiusXZ, radiusXZ, hollow, block, flags, rule);
    }

    public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, int radiusY, boolean hollow, final Block block, int flags, BlockProtectionRule rule)
    {
        return Beapi.createSphere(world, center, radiusXZ, radiusY, hollow, block, null, flags, rule);
    }

    public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, int radiusY, boolean hollow, final Block block, @Nullable BlockProtectionRule.IReplaceBlockRule replaceTest, int flags, BlockProtectionRule rule)
    {
        int x0 = center.getX();
        int y0 = center.getY();
        int z0 = center.getZ();

        List<BlockPos> blockPositions = new ArrayList<BlockPos>();
        for (int y = y0 - radiusY; y <= y0 + radiusY; y++)
        {
            for (int x = x0 - radiusXZ; x <= x0 + radiusXZ; x++)
            {
                for (int z = z0 - radiusXZ; z <= z0 + radiusXZ; z++)
                {
                    double distance = ((x0 - x) * (x0 - x) + ((z0 - z) * (z0 - z)) + ((y0 - y) * (y0 - y)));

                    if (distance < radiusXZ * radiusY && !(hollow && distance < ((radiusXZ - 1) * (radiusXZ - 1))))
                    {
                        BlockPos pos = new BlockPos(x, y, z);
                        BlockState state = world.getBlockState(pos);

//						BlockRayTraceResult result = WyHelper.rayTraceBlocks(world, new Vector3d(center), new Vector3d(pos));
//						if(result.getType() == RayTraceResult.Type.BLOCK)
//						{
//							
//						}

                        if(replaceTest != null && !replaceTest.replace(world, pos, state))
                            continue;

                        if(placeBlockIfAllowed(world, pos.getX(), pos.getY(), pos.getZ(), block, flags))
                            blockPositions.add(pos);
                    }
                }
            }
        }

        return blockPositions;
    }



    @Deprecated
    public static List<BlockPos> createEmptySphere(World world, int posX, int posY, int posZ, int size, final Block block, BlockProtectionRule rule)
    {
        return Beapi.createSphere(world, new BlockPos(posX, posY, posZ), size, true, block, 2, rule);
    }

    @Deprecated
    public static List<BlockPos> createFilledSphere(World world, int posX, int posY, int posZ, int size, final Block block, BlockProtectionRule rule)
    {
        return Beapi.createSphere(world, new BlockPos(posX, posY, posZ), size, false, block, 2, rule);
    }
    
    public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance, float entityBoxRange) {
        Vector3d lookVec = entity.getLookAngle();
        Vector3d startVec = entity.position().add(0, entity.getEyeHeight(), 0);
        Vector3d endVec = startVec.add(entity.getLookAngle().scale(distance));
        RayTraceResult blockResult = entity.level.clip(new RayTraceContext(startVec, endVec,  RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity));
        RayTraceResult entityResult = null;

        for (int i = 0; i < distance * 2; i++)
        {
            if (entityResult != null)
                break;

            float scale = i / 2F;
            Vector3d pos = startVec.add(lookVec.scale(scale));

            Vector3d min = pos.add(entityBoxRange, entityBoxRange, entityBoxRange);
            Vector3d max = pos.add(-entityBoxRange, -entityBoxRange, -entityBoxRange);
            List<Entity> list = entity.level.getEntities(entity, new AxisAlignedBB(min.x, min.y, min.z, max.x, max.y, max.z));
            list.remove(entity);
            for (Entity e : list) {


                entityResult = new EntityRayTraceResult(e, pos);
                break;
            }
        }

        if (entityResult != null && entityResult.getLocation().distanceTo(startVec) <= blockResult.getLocation().distanceTo(startVec)) {
            return entityResult;
        } else {
            return blockResult;
        }

    }

    public static boolean saveNBTStructure(ServerWorld world, String name, BlockPos pos, BlockPos size, List<Block> toIgnore)
    {
        if (!world.isClientSide)
        {
            ServerWorld serverworld = world;
            TemplateManager templatemanager = serverworld.getStructureManager();
            ResourceLocation res = new ResourceLocation(Main.MODID, name);

            Template template;
            try
            {
                template = templatemanager.getOrCreate(res);
            }
            catch (ResourceLocationException ex)
            {
                ex.printStackTrace();
                return false;
            }

            toIgnore.add(Blocks.STRUCTURE_VOID);
            //toIgnore.add(Blocks.BEDROCK);
            takeBlocksFromWorld(template, world, pos, size, toIgnore);
            template.fillFromWorld(serverworld, pos, size, true, Blocks.STRUCTURE_VOID);
            template.setAuthor("?");
            try
            {
                return templatemanager.save(res);
            }
            catch (ResourceLocationException var7)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public static boolean loadNBTStructure(ServerWorld world, String name, BlockPos pos)
    {
        if (!world.isClientSide)
        {
            BlockPos blockpos = pos;
            TemplateManager templatemanager = world.getStructureManager();
            ResourceLocation res = new ResourceLocation(Main.MODID, name);

            Template template;
            try
            {
                template = templatemanager.get(res);
            }
            catch (ResourceLocationException ex)
            {
                ex.printStackTrace();
                return false;
            }

            if (template == null)
            {
                return false;
            }
            else
            {
                BlockState blockstate = world.getBlockState(blockpos);
                world.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
            }

            PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true).setChunkPos((ChunkPos) null);
            placementsettings.clearProcessors()
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR)
                    .addProcessor(new IntegrityProcessor(1f)).setRandom(new Random(Util.getMillis()));
            //placementsettings.clearProcessors().addProcessor(new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.SAND)));

            template.placeInWorldChunk(world, pos, placementsettings, new Random(Util.getMillis()));
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void takeBlocksFromWorld(Template template, World world, BlockPos startPos, BlockPos size, @Nullable List<Block> toIgnore)
    {
        if (size.getX() >= 1 && size.getY() >= 1 && size.getZ() >= 1)
        {
            BlockPos blockpos = startPos.offset(size).offset(-1, -1, -1);
            List<Template.BlockInfo> list = Lists.newArrayList();
            List<Template.BlockInfo> list1 = Lists.newArrayList();
            List<Template.BlockInfo> list2 = Lists.newArrayList();
            BlockPos blockpos1 = new BlockPos(Math.min(startPos.getX(), blockpos.getX()), Math.min(startPos.getY(), blockpos.getY()), Math.min(startPos.getZ(), blockpos.getZ()));
            BlockPos blockpos2 = new BlockPos(Math.max(startPos.getX(), blockpos.getX()), Math.max(startPos.getY(), blockpos.getY()), Math.max(startPos.getZ(), blockpos.getZ()));
            //((TemplateMixin)template).setSize(size);

            for (BlockPos blockpos3 : BlockPos.betweenClosed(blockpos1, blockpos2))
            {
                BlockPos blockpos4 = blockpos3.subtract(blockpos1);
                BlockState blockstate = world.getBlockState(blockpos3);

                if (toIgnore != null && toIgnore.contains(blockstate.getBlock()))
                {
                    world.setBlockAndUpdate(blockpos3, Blocks.AIR.defaultBlockState());
                    blockstate = world.getBlockState(blockpos3);
                    //if(blockstate.has(BlockStateProperties.WATERLOGGED))
                    //	blockstate.with(BlockStateProperties.WATERLOGGED, false);
                }

                TileEntity tileentity = world.getBlockEntity(blockpos3);
                if (tileentity != null)
                {
                    CompoundNBT compoundnbt = tileentity.save(new CompoundNBT());
                    compoundnbt.remove("x");
                    compoundnbt.remove("y");
                    compoundnbt.remove("z");
                    list1.add(new Template.BlockInfo(blockpos4, blockstate, compoundnbt));
                }
                else if (!blockstate.propagatesSkylightDown(world, blockpos3) && !blockstate.isCollisionShapeFullBlock(world, blockpos3))
                {
                    list2.add(new Template.BlockInfo(blockpos4, blockstate, (CompoundNBT) null));
                }
                else
                {
                    list.add(new Template.BlockInfo(blockpos4, blockstate, (CompoundNBT) null));
                }
            }

            List<Template.BlockInfo> list3 = Lists.newArrayList();
            list3.addAll(list);
            list3.addAll(list1);
            list3.addAll(list2);
            //((TemplateMixin)template).getBlocks().clear();
            //((TemplateMixin)template).getBlocks().add(list3);
            //((TemplateMixin)template).getEntities().clear();
        }
    }

    public static boolean setBlockStateInChunk(World world, BlockPos pos, BlockState newState, int flags)
    {
        if (World.isOutsideBuildHeight(pos))
            return false;
        else if (!world.isClientSide && world.isDebug())
            return false;
        else
        {
            Chunk chunk = world.getChunkAt(pos);
            pos = pos.immutable();

            BlockState blockstate = chunk.setBlockState(pos, newState, (flags & 64) != 0);
            if (blockstate != null)
            {
                //world.markAndNotifyBlock(pos, chunk, blockstate, newState, flags);
                if ((flags & 2) != 0 && (!world.isClientSide || (flags & 4) == 0))
                    world.markAndNotifyBlock(pos, chunk, blockstate, newState, flags, 256);
            }
            return true;
        }
    }


    public static void drawAbilityIcon(String iconName, int x, int y, int z, int u, int v)
    {
        drawAbilityIcon(iconName, x, y, z, u, v, 1, 1, 1);
    }

    public static void drawAbilityIcon(String iconName, int x, int y, int z, int u, int v, float red, float green, float blue)
    {
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        Minecraft.getInstance().getTextureManager().bind(new ResourceLocation(Main.MODID, "textures/abilities/" + BeJavapi.getResourceName(iconName) + ".png"));
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
        bufferbuilder.vertex(x, y + v, z).color(red, green, blue, 1f).uv(0.0f, 1.0f).endVertex();
        bufferbuilder.vertex(x + u, y + v, z).color(red, green, blue, 1f).uv(1.0f, 1.0f).endVertex();
        bufferbuilder.vertex(x + u, y, z).color(red, green, blue, 1f).uv(1.0f, 0.0f).endVertex();
        bufferbuilder.vertex(x, y, z).color(red, green, blue, 1f).uv(0f, 0f).endVertex();
        Tessellator.getInstance().end();
    }

    public static void drawAbilityIcon(String iconName, int x, int y, int u, int v)
    {
        drawAbilityIcon(iconName, x, y, 1, u, v);
    }

    public static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(String localizedName, Supplier<EntityType<T>> supp)
    {
        String resourceName = BeJavapi.getResourceName(localizedName);
        Beapi.getLangMap().put("entity." + Main.MODID + "." + resourceName, localizedName);

        RegistryObject<EntityType<T>> reg = ModRegistry.ENTITY_TYPES.register(resourceName, supp);

        return reg;
    }


}
