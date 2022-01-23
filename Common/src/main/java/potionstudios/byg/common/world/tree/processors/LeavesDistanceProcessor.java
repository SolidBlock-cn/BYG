package potionstudios.byg.common.world.tree.processors;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;
import potionstudios.byg.mixin.access.LeavesBlockAccess;

public class LeavesDistanceProcessor extends StructureProcessor {
    public static final Codec<LeavesDistanceProcessor> CODEC = Codec.unit(new LeavesDistanceProcessor());

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos blockPos, BlockPos blockPos1, StructureTemplate.StructureBlockInfo structureBlockInfo, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlaceSettings) {
        BlockState state = structureBlockInfoWorld.state;
        if (state.is(BlockTags.LEAVES)) {
            BlockState blockState = LeavesBlockAccess.invokeUpdateDistance(state, (LevelAccessor) levelReader, structureBlockInfoWorld.pos);
            blockState.setValue(LeavesBlock.PERSISTENT, false);
            new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos, blockState, structureBlockInfoWorld.nbt);
        }

        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return BYGStructureProcessorTypes.LEAVES_DISTANCE;
    }
}
