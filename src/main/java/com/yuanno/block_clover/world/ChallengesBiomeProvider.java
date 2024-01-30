package com.yuanno.block_clover.world;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.List;

public class ChallengesBiomeProvider extends BiomeProvider
{
	private static final List<Biome> BIOME_KEYS = Lists.newArrayList(BiomeRegistry.THE_VOID);
	
	public static final Codec<ChallengesBiomeProvider> CODEC = RecordCodecBuilder.create((builder) ->
	{
		return builder.group(
			Codec.LONG.fieldOf("seed").stable().forGetter((provider) ->
			{
				return provider.seed;
			}),
			RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((provider) ->
			{
				return provider.lookupRegistry;
			})
		).apply(builder, builder.stable(ChallengesBiomeProvider::new));
	});
	private final long seed;
	private Registry<Biome> lookupRegistry;
	
	public ChallengesBiomeProvider(long seed, Registry<Biome> lookupRegistry)
	{
		super(BIOME_KEYS);
		this.seed = seed;
		this.lookupRegistry = lookupRegistry;
	}

	@Override
	public Biome getNoiseBiome(int pX, int pY, int pZ)
	{
		return BiomeRegistry.THE_VOID;
	}

	@Override
	protected Codec<? extends BiomeProvider> codec()
	{
		return CODEC;
	}

	@Override
	public BiomeProvider withSeed(long seed)
	{
		return new ChallengesBiomeProvider(seed, this.lookupRegistry);
	}
}
