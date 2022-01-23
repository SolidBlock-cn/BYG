package potionstudios.byg.common.world.tree.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;
import potionstudios.byg.util.CodecUtil;

import java.util.Random;

public class TrunkFillingProcessor extends StructureProcessor {

    public static final Codec<TrunkFillingProcessor> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(trunkFillingProcessor -> trunkFillingProcessor.stateProvider)
        ).apply(builder, TrunkFillingProcessor::new);
    });


    private BlockStateProvider stateProvider;

    public TrunkFillingProcessor(BlockStateProvider stateProvider) {

        this.stateProvider = stateProvider;
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos blockPos, BlockPos blockPos1, StructureTemplate.StructureBlockInfo structureBlockInfo, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlaceSettings) {
        if(structureBlockInfoWorld.state.getBlock() == Blocks.RED_WOOL) {
            BlockPos pos = structureBlockInfoWorld.pos;
            ChunkAccess chunk = levelReader.getChunk(pos);
            chunk.setBlockState(pos, Blocks.OAK_LOG.defaultBlockState(), false);
            Random random = structurePlaceSettings.getRandom(null);

            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(pos);
            int trunksPlaced = -1;
            while (mutable.getY() > levelReader.getMinBuildHeight() && trunksPlaced < 32) {
                BlockState mutableState = chunk.getBlockState(mutable);
                if (mutableState.is(BlockTags.LEAVES) || mutableState.isAir() || mutableState.getBlock() == Blocks.WATER) {
                    chunk.setBlockState(mutable, this.stateProvider.getState(random, mutable), false);
                }
                mutable.setY(pos.getY() - trunksPlaced++);
            }
            return new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos, this.stateProvider.getState(random, pos), structureBlockInfoWorld.nbt);
        }

        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return BYGStructureProcessorTypes.TRUNK_FILLING;
    }
}
