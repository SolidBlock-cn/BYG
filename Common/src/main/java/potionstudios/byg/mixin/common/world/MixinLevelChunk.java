package potionstudios.byg.mixin.common.world;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.ProtoChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import potionstudios.byg.util.ChunkNoiseCaveData;

@Mixin(LevelChunk.class)
public class MixinLevelChunk {


    @Inject(method = "<init>(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ProtoChunk;Lnet/minecraft/world/level/chunk/LevelChunk$PostLoadProcessor;)V", at = @At("RETURN"))
    private void updateFromProto(ServerLevel $$0, ProtoChunk protoChunk, LevelChunk.PostLoadProcessor $$2, CallbackInfo ci) {
        int[] protoCaveHeights = ((ChunkNoiseCaveData) protoChunk).getCaveHeights();
        System.arraycopy(protoCaveHeights, 0, ((ChunkNoiseCaveData) this).getCaveHeights(), 0, protoCaveHeights.length);
    }
}
