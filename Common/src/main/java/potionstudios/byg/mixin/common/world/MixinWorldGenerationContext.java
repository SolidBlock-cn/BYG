package potionstudios.byg.mixin.common.world;

import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import potionstudios.byg.common.world.access.WorldGenerationContextAccess;

@Mixin(WorldGenerationContext.class)
public class MixinWorldGenerationContext implements WorldGenerationContextAccess {

    private ChunkGenerator chunkGenerator;
    private LevelHeightAccessor heightAccessor;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void setHeightAccessor(ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor, CallbackInfo ci) {
        this.chunkGenerator = chunkGenerator;
        this.heightAccessor = heightAccessor;
    }

    @Override
    public ChunkGenerator getGenerator() {
        return chunkGenerator;
    }

    @Override
    public LevelHeightAccessor getHeightAccess() {
        return this.heightAccessor;
    }
}
