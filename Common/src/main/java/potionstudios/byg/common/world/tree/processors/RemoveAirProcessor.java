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

public class RemoveAirProcessor extends StructureProcessor {
    public static final Codec<RemoveAirProcessor> CODEC = Codec.unit(new RemoveAirProcessor());

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos blockPos, BlockPos blockPos1, StructureTemplate.StructureBlockInfo structureBlockInfo, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlaceSettings) {
        return structureBlockInfoWorld.state.isAir() ? null : structureBlockInfoWorld;

    }

    @Override
    protected StructureProcessorType<?> getType() {
        return BYGStructureProcessorTypes.LEAVES_DISTANCE;
    }
}
