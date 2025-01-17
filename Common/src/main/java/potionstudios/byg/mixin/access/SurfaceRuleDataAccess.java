package potionstudios.byg.mixin.access;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SurfaceRuleData.class)
public interface SurfaceRuleDataAccess {

    @Invoker
    static SurfaceRules.ConditionSource invokeSurfaceNoiseAbove(double d) {
        throw new Error("Mixin did not apply!");
    }
}
