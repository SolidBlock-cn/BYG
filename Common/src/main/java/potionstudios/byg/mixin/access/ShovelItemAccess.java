package potionstudios.byg.mixin.access;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(ShovelItem.class)
public interface ShovelItemAccess {

    @Accessor("FLATTENABLES")
    static Map<Block, BlockState> getFlattenables() {
        throw new Error("Mixin did not apply");
    }

    @Accessor("FLATTENABLES")
    @Mutable
    static void setFlattenables(Map<Block, BlockState> newMap) {
        throw new Error("Mixin did not apply");
    }
}
