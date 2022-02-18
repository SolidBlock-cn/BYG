package potionstudios.byg.common.world.feature.overworld;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.CommonLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.SurfaceRules;
import potionstudios.byg.common.block.BYGBlocks;
import potionstudios.byg.common.world.biome.BYGBiomes;
import potionstudios.byg.common.world.math.noise.fastnoise.FastNoise;
import potionstudios.byg.util.BYGUtil;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutionException;
import java.util.function.BiPredicate;

public class DuneSurfaceExtension {
    private final FastNoise dunePeakNoise1;
    private final FastNoise dunePeakNoise2;
    private final Cache<Long, int[]> cache;

    public DuneSurfaceExtension(long seed) {
        this.dunePeakNoise1 = new FastNoise((int) seed);
        this.dunePeakNoise2 = new FastNoise((int) seed + 76457567);


        this.cache = CacheBuilder.newBuilder().maximumSize(10000L).build();
    }

    private long lastAccessedChunk;
    private int[] lastAccessedData;

    public void generate(BiomeManager biomeManager, Registry<Biome> biomeRegistry, ChunkAccess chunk, BlockPos worldPos, ChunkGenerator chunkGenerator, @Nullable CommonLevelAccessor levelAccessor, SurfaceRules.Context context) {
        int step = 1;


        BiPredicate<BlockPos.MutableBlockPos, Double> densityIncrementTest = (movingPos, aDouble) -> {
            int height1 = 0;
            int chunkX = SectionPos.blockToSectionCoord(movingPos.getX());
            int chunkZ = SectionPos.blockToSectionCoord(movingPos.getZ());
            long chunkLong = ChunkPos.asLong(chunkX, chunkZ);

            try {
                int movingPosLocalX = movingPos.getX() & 15;
                int movingPosLocalZ = movingPos.getZ() & 15;
                int stepBits = Mth.log2(step);
                int movingPosIndex = (movingPosLocalX >> stepBits) + (movingPosLocalZ >> stepBits) * (16 / step);
                if (chunkLong == lastAccessedChunk && lastAccessedData != null) {
                    height1 = lastAccessedData[movingPosIndex];
                } else {
                    int[] cache = this.cache.get(chunkLong, (() -> {
                        ChunkAccess movingChunk = levelAccessor == null ? null : levelAccessor.getChunk(chunkX, chunkZ);
                        boolean afterNoise = movingChunk != null && movingChunk.getStatus().isOrAfter(ChunkStatus.NOISE);

                        int[] heights = new int[(16 / step) * (16 / step)];
                        if (afterNoise) {
                            for (int zLocal = 0; zLocal < 16; zLocal += step) {
                                for (int xLocal = 0; xLocal < 16; xLocal += step) {
                                    int index = (xLocal >> stepBits) + (zLocal >> stepBits) * (16 / step);
                                    heights[index] = movingChunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, xLocal, zLocal);
                                }
                            }
                        } else {
                            for (int zLocal = 0; zLocal < 16; zLocal += step) {
                                for (int xLocal = 0; xLocal < 16; xLocal += step) {
                                    int worldX = SectionPos.sectionToBlockCoord(chunkX) + xLocal;
                                    int worldZ = SectionPos.sectionToBlockCoord(chunkZ) + zLocal;
                                    int index = (xLocal >> stepBits) + (zLocal >> stepBits) * (16 / step);

                                    heights[index] = chunkGenerator.getBaseHeight(worldX, worldZ, Heightmap.Types.OCEAN_FLOOR_WG, chunk);
                                }
                            }
                        }

                        return heights;
                    }));
                    lastAccessedData = cache;
                    lastAccessedChunk = chunkLong;
                    height1 = cache[movingPosIndex];
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            movingPos.setY(height1);
            ResourceKey<Biome> movingPosBiome = biomeRegistry.getResourceKey(biomeManager.getBiome(movingPos)).get();
            return (height1 < chunkGenerator.getSeaLevel() || movingPosBiome != BYGBiomes.WINDSWEPT_DUNES) && height1 < aDouble;
        };

        int localX = worldPos.getX() & 15;
        int localZ = worldPos.getZ() & 15;
        int oceanFloor = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, localX, localZ);


        int duneHeight = DuneSurfaceExtension.getDuneHeight(densityIncrementTest, worldPos, oceanFloor, 6, step, this.dunePeakNoise1, this.dunePeakNoise2);
        BYGUtil.fill(duneHeight, oceanFloor, localX, localZ, (fillPos -> chunk.setBlockState(fillPos,
                fillPos.getY() == oceanFloor ? BYGBlocks.WINDSWEPT_SANDSTONE.defaultBlockState() : BYGBlocks.WINDSWEPT_SAND.defaultBlockState(),
                false)));

    }


    public static int getDuneHeight(BiPredicate<BlockPos.MutableBlockPos, Double> densityIncreaseTest, BlockPos pos, int oceanFloor, int blendRange, int step, FastNoise dunePeakNoise1, FastNoise dunePeakNoise2) {
        float duneHeight = peakNoise(dunePeakNoise1, pos);
        duneHeight *= Mth.lerp(5, 15, 0.3F);

        float duneHeight1 = peakNoise(dunePeakNoise2, pos);
        duneHeight1 *= Mth.lerp(5, 15, 0.3F);

        double height = 180 + Math.max(duneHeight, duneHeight1);

        double density = getDuneBlendDensity(densityIncreaseTest, pos, height, blendRange, step);

        return (int) Mth.clampedLerp(oceanFloor - 3, height, 1.0 - density);
    }

    private static double getDuneBlendDensity(BiPredicate<BlockPos.MutableBlockPos, Double> densityIncreaseTest, BlockPos origin, double height, int blendRange, int blendStep) {
        BlockPos.MutableBlockPos blendingPos = new BlockPos.MutableBlockPos().set(origin);

        double density = 0;
        double densityIncrement = (1.0 / (blendRange * blendRange) * 2) * (blendStep * blendStep);
        for (int x = -blendRange; x <= blendRange; x += blendStep) {
            for (int z = -blendRange; z <= blendRange; z += blendStep) {
                blendingPos.set(origin).move(x, 0, z);
                if (densityIncreaseTest.test(blendingPos, height)) {
                    density += densityIncrement;
                }
            }
        }
        return density;
    }

    private static float peakNoise(FastNoise noise, BlockPos mutableBlockPos) {
        noise.SetFrequency(0.023F);
        noise.SetNoiseType(FastNoise.NoiseType.Cellular);
        noise.SetCellularDistanceFunction(FastNoise.CellularDistanceFunction.Euclidean);
        noise.SetCellularReturnType(FastNoise.CellularReturnType.Distance2Mul);
        noise.SetGradientPerturbAmp(1.5F);

        return 1 - noise.GetNoise((float) (mutableBlockPos.getX()), 0, (float) (mutableBlockPos.getZ()));
    }
}
