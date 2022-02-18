package potionstudios.byg.mixin.access;

import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SurfaceRules.Context.class)
public interface SurfaceRuleContextAccess {

    @Accessor
    SurfaceSystem getSystem();

    @Accessor
    ChunkAccess getChunk();

    @Accessor
    SurfaceRules.Condition getAbovePreliminarySurface();

}
