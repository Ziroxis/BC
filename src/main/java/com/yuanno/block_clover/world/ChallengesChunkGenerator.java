package com.yuanno.block_clover.world;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;

public class ChallengesChunkGenerator extends ChunkGenerator
{
	public static final Codec<ChallengesChunkGenerator> CODEC = RegistryLookupCodec.create(Registry.BIOME_REGISTRY).xmap(ChallengesChunkGenerator::new, ChallengesChunkGenerator::biomes).stable().codec();

	private final Registry<Biome> registry;

	public ChallengesChunkGenerator(Registry<Biome> registry)
	{
		super(new SingleBiomeProvider(registry.getOrThrow(Biomes.THE_VOID)), new DimensionStructuresSettings(false));
		this.registry = registry;
	}

	public Registry<Biome> biomes()
	{
		return this.registry;
	}

	@Override
	protected Codec<? extends ChunkGenerator> codec()
	{
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed)
	{
		return this;
	}

	@Override
	public void buildSurfaceAndBedrock(WorldGenRegion pLevel, IChunk pChunk)
	{

	}

	@Override
	public void fillFromNoise(IWorld p_230352_1_, StructureManager p_230352_2_, IChunk p_230352_3_)
	{

	}

	@Override
	public int getBaseHeight(int p_222529_1_, int p_222529_2_, Type p_222529_3_)
	{
		return 0;
	}

	@Override
	public IBlockReader getBaseColumn(int x, int z)
	{
		return new Blockreader(new BlockState[0]);
	}
}
/*
 * public ChallengesChunkGenerator(IWorld world, BiomeProvider biomeProvider, DebugGenerationSettings settings)
 * {
 * super(world, biomeProvider, settings);
 * }
 * @Override
 * public void decorate(WorldGenRegion region)
 * {
 * BlockPos.Mutable blockPos = new BlockPos.Mutable();
 * int i = region.getCenterX();
 * int j = region.getCenterZ();
 * for(ArenaData arena : ModArenas.ARENAS)
 * {
 * int barrierStartX = arena.startBarrierPos.x;
 * int barrierEndX = arena.endBarrierPos.x;
 * int barrierStartZ = arena.startBarrierPos.z;
 * int barrierEndZ = arena.endBarrierPos.z;
 * if(i > barrierStartX && i < barrierEndX && j > barrierStartZ && j < barrierEndZ)
 * {
 * arena.preGen(region);
 * }
 * if(i > barrierStartX && i < barrierEndX && (j == barrierStartZ + 1 || j == barrierEndZ))
 * {
 * for (int x = 0; x < 16; ++x)
 * {
 * int x1 = (i << 4) + x;
 * int z1 = (j << 4);
 * for (int y = 0; y < region.getHeight(); ++y)
 * region.setBlockAndUpdate(blockPos.setPos(x1, y, z1), Blocks.BARRIER.defaultBlockState(), 3);
 * }
 * }
 * if(j > barrierStartZ && j < barrierEndZ && (i == barrierStartX + 1 || i == barrierEndX))
 * {
 * for (int z = 0; z < 16; ++z)
 * {
 * int x1 = (i << 4);
 * int z1 = (j << 4) + z;
 * for (int y = 0; y < region.getHeight(); ++y)
 * region.setBlockAndUpdate(blockPos.setPos(x1, y, z1), Blocks.BARRIER.defaultBlockState(), 3);
 * }
 * }
 * }
 * }
 * @Override
 * public int getGroundHeight()
 * {
 * return 1;
 * }
 * @Override
 * public void makeBase(IWorld worldIn, IChunk chunkIn)
 * {
 * }
 * @Override
 * public void generateSurface(WorldGenRegion region, IChunk chunk)
 * {
 * }
 * @Override
 * public int getHeight(int x, int z, Heightmap.Type heightmapType)
 * {
 * return 0;
 * }
 */
