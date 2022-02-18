package potionstudios.byg.mixin.common.world.surface;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.CommonLevelAccessor;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import potionstudios.byg.common.world.access.WorldGenerationContextAccess;
import potionstudios.byg.common.world.biome.BYGBiomes;
import potionstudios.byg.common.world.feature.overworld.DuneSurfaceExtension;
import potionstudios.byg.util.SeedGetter;

@Mixin(SurfaceSystem.class)
public abstract class MixinSurfaceSystem implements SeedGetter {

    @Shadow
    protected abstract void erodedBadlandsExtension(BlockColumn $$0, int $$1, int $$2, int $$3, LevelHeightAccessor $$4);

    private long worldSeed;
    private DuneSurfaceExtension duneSurfaceExtension;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void obtainSeed(Registry registry, BlockState state, int $$2, long worldSeed, WorldgenRandom.Algorithm algorithm, CallbackInfo ci) {
        this.worldSeed = worldSeed;
        this.duneSurfaceExtension = new DuneSurfaceExtension(worldSeed);
    }

    @Inject(method = "buildSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/ChunkAccess;getHeight(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I", ordinal = 1, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void beforeSurfaceUpdate(BiomeManager biomeManager, Registry<Biome> biomeRegistry, boolean $$2, WorldGenerationContext context, ChunkAccess chunk, NoiseChunk $$5, SurfaceRules.RuleSource $$6, CallbackInfo ci, BlockPos.MutableBlockPos worldPos, ChunkPos $$8, int $$9, int $$10, BlockColumn blockColumn, SurfaceRules.Context surfaceContext, SurfaceRules.SurfaceRule $$13, BlockPos.MutableBlockPos worldPos2, int $$15, int $$16, int $$17, int $$18, int $$19, Biome $$20, ResourceKey<Biome> biomeResourceKey) {
        if (biomeResourceKey == BYGBiomes.SHATTERED_GLACIER) {
            this.erodedBadlandsExtension(blockColumn, $$17, $$18, $$19, chunk);
        }
        if (biomeResourceKey == BYGBiomes.WINDSWEPT_DUNES) {
            WorldGenerationContextAccess worldGenerationContextAccess = (WorldGenerationContextAccess) context;
            ChunkGenerator chunkGenerator = worldGenerationContextAccess.getGenerator();
            LevelHeightAccessor heightAccess = worldGenerationContextAccess.getHeightAccess();
            this.duneSurfaceExtension.generate(biomeManager, biomeRegistry, chunk, worldPos, chunkGenerator, heightAccess instanceof CommonLevelAccessor ? (CommonLevelAccessor) heightAccess : null, surfaceContext);
        }
    }

    @Inject(method = "buildSurface", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/biome/Biomes;FROZEN_OCEAN:Lnet/minecraft/resources/ResourceKey;", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void afterSurfaceUpdate(BiomeManager biomeManager, Registry<Biome> biomeRegistry, boolean $$2, WorldGenerationContext context, ChunkAccess chunk, NoiseChunk $$5, SurfaceRules.RuleSource $$6, CallbackInfo ci, BlockPos.MutableBlockPos worldPos, ChunkPos $$8, int $$9, int $$10, BlockColumn blockColumn, SurfaceRules.Context surfaceContext, SurfaceRules.SurfaceRule $$13, BlockPos.MutableBlockPos worldPos2, int $$15, int $$16, int $$17, int $$18, int $$19, Biome $$20, ResourceKey<Biome> biomeResourceKey) {

    }

    @Override
    public long getAsLong() {
        return this.worldSeed;
    }
}
