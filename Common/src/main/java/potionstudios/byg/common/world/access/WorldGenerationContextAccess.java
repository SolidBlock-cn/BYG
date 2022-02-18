package potionstudios.byg.common.world.access;

import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;

public interface WorldGenerationContextAccess {

    ChunkGenerator getGenerator();

    LevelHeightAccessor getHeightAccess();
}


