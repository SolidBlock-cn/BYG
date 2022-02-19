package potionstudios.byg.mixin.common.world;

import net.minecraft.Util;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.RandomSupport;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import potionstudios.byg.util.ChunkNoiseCaveData;
import potionstudios.byg.util.ChunkRandom;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Random;

@Mixin(ChunkAccess.class)
public class MixinChunkAccess implements ChunkRandom, ChunkNoiseCaveData {

    int[] caveHeights = Util.make(new int[16 * 16], (array) -> {
        Arrays.fill(array, Integer.MAX_VALUE);
    });

    @Shadow
    @Final
    protected ChunkPos chunkPos;

    @Nullable
    private Random random;

    @Override
    public Random getRandom(long worldSeed) {
        if (random == null) {
            WorldgenRandom worldgenrandom = new WorldgenRandom(new XoroshiroRandomSource(RandomSupport.seedUniquifier()));
            worldgenrandom.setDecorationSeed(worldSeed, this.chunkPos.x, this.chunkPos.z);
            this.random = worldgenrandom;
        }

        return this.random;
    }

    @Override
    public int[] getCaveHeights() {
        return caveHeights;
    }
}
