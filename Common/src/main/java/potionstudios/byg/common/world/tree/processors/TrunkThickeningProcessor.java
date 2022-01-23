package potionstudios.byg.common.world.tree.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
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

import java.util.Map;
import java.util.Random;

public class TrunkThickeningProcessor extends StructureProcessor {

    public static final Codec<TrunkThickeningProcessor> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(Codec.unboundedMap(CodecUtil.BLOCK_CODEC, Data.CODEC).fieldOf("mapper").forGetter(trunkThickeningProcessor -> trunkThickeningProcessor.thickeningData)).apply(builder, TrunkThickeningProcessor::new);
    });


    private final Map<Block, Data> thickeningData;

    public TrunkThickeningProcessor(Map<Block, Data> thickeningData) {
        this.thickeningData = new Object2ObjectOpenHashMap<>(thickeningData);
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos blockPos, BlockPos blockPos1, StructureTemplate.StructureBlockInfo structureBlockInfo, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlaceSettings) {
        Block block = structureBlockInfoWorld.state.getBlock();
        if (this.thickeningData.containsKey(block)) {
            BlockPos pos = structureBlockInfoWorld.pos;
            ChunkAccess chunk = levelReader.getChunk(pos);

            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(pos);
            int trunksPlaced = 1;
            while (mutable.getY() > levelReader.getMinBuildHeight()) {
                BlockState mutableState = chunk.getBlockState(mutable);
                if (mutableState.canOcclude()) {
                    break;
                }
                mutable.setY(pos.getY() - trunksPlaced++);

                if (trunksPlaced > 32) {
                    return structureBlockInfoWorld;
                }
            }

            Data data = this.thickeningData.get(block);
            Random random = structurePlaceSettings.getRandom(null);
            int height = data.getHeight(random);
            for (int yMove = 0; yMove <= height; yMove++) {
                if (yMove == height) {
                    chunk.setBlockState(mutable, data.woodProvider.getState(random, mutable), false);
                } else {
                    chunk.setBlockState(mutable, data.logProvider.getState(random, mutable), false);
                }
                mutable.move(Direction.UP);
            }
            return new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos, Blocks.AIR.defaultBlockState(), structureBlockInfo.nbt);
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return BYGStructureProcessorTypes.TRUNK_THICKENER;
    }

    public record Data(int minHeight, int maxHeight, BlockStateProvider logProvider, BlockStateProvider woodProvider) {

        public static final Codec<Data> CODEC = RecordCodecBuilder.create(builder -> {
            return builder.group(
                    Codec.INT.fieldOf("min_height").orElse(15).forGetter((config) -> config.minHeight),
                    Codec.INT.fieldOf("max_height").orElse(15).forGetter((config) -> config.maxHeight),
                    BlockStateProvider.CODEC.fieldOf("log_provider").forGetter(data -> data.logProvider),
                    BlockStateProvider.CODEC.fieldOf("wood_provider").forGetter(data -> data.woodProvider)
            ).apply(builder, Data::new);
        });

        public int getHeight(Random random) {
            return random.nextInt(Math.max(1, this.maxHeight - this.minHeight)) + this.minHeight;
        }
    }
}
