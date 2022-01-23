package potionstudios.byg.common.world.tree;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class BYGStructureTreeConfig implements FeatureConfiguration {

    public static final Codec<BYGStructureTreeConfig> CODEC = RecordCodecBuilder.create((codecRecorder) -> {
        return codecRecorder.group(CanopyContext.CODEC.fieldOf("canopy_context").forGetter(config -> {
            return config.canopyContext;
        }), BlockStateProvider.CODEC.fieldOf("trunk_provider").orElse(SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState())).forGetter((config) -> {
            return config.trunkProvider;
        }), BlockStateProvider.CODEC.fieldOf("leaves_provider").orElse(SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState())).forGetter((config) -> {
            return config.leavesProvider;
        }), BlockStateProvider.CODEC.fieldOf("disk_provider").orElse(SimpleStateProvider.simple(Blocks.PODZOL.defaultBlockState())).forGetter((config) -> {
            return config.diskProvider;
        }), Codec.INT.fieldOf("trunk_min_height").orElse(15).forGetter((config) -> {
            return config.minHeight;
        }), Codec.INT.fieldOf("trunk_max_height").orElse(15).forGetter((config) -> {
            return config.maxHeight;
        }), Codec.INT.fieldOf("disk_radius").orElse(0).forGetter((config) -> {
            return config.diskRadius;
        }), BlockState.CODEC.listOf().fieldOf("whitelist").forGetter((config) -> {
            return config.whitelist.stream().map(Block::defaultBlockState).collect(Collectors.toList());
        })).apply(codecRecorder, BYGStructureTreeConfig::new);
    });

    private final CanopyContext canopyContext;
    private final BlockStateProvider trunkProvider;
    private final BlockStateProvider leavesProvider;
    private final BlockStateProvider diskProvider;
    private final int minHeight;
    private final int maxHeight;
    private final int diskRadius;
    private final Set<Block> whitelist;
    @Nullable
    private StructureTemplate canopyTemplate;
    private final StructurePlaceSettings placeSettings = new StructurePlaceSettings();

    BYGStructureTreeConfig(CanopyContext canopyContext, BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, BlockStateProvider diskProvider, int minHeight, int maxHeight, int diskRadius, List<BlockState> whitelist) {
        this.canopyContext = canopyContext;
        this.trunkProvider = trunkProvider;
        this.leavesProvider = leavesProvider;
        this.diskProvider = diskProvider;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.diskRadius = diskRadius;
        this.whitelist = whitelist.stream().map(BlockBehaviour.BlockStateBase::getBlock).collect(Collectors.toSet());
    }

    public BlockStateProvider getTrunkProvider() {
        return trunkProvider;
    }

    public BlockStateProvider getLeavesProvider() {
        return leavesProvider;
    }

    public BlockStateProvider getDiskProvider() {
        return diskProvider;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getHeight(Random random) {
        return random.nextInt(Math.max(1, this.maxHeight - this.minHeight)) + this.minHeight;
    }

    public int getDiskRadius() {
        return diskRadius;
    }

    public Set<Block> getWhitelist() {
        return whitelist;
    }

    public CanopyContext getCanopyContext() {
        return canopyContext;
    }

    public StructurePlaceSettings getPlaceSettings() {
        return placeSettings;
    }

    public StructureTemplate getCanopy(WorldGenLevel worldGenLevel) {
        if (this.canopyTemplate == null) {
            this.canopyTemplate = worldGenLevel.getLevel().getStructureManager().getOrCreate(this.canopyContext.canopyLocation());
            for (StructureProcessor processor : this.canopyContext.processors()) {
                this.placeSettings.addProcessor(processor);
            }
        }
        return this.canopyTemplate;
    }

    public record CanopyContext(ResourceLocation canopyLocation, List<StructureProcessor> processors, int xOffset, int zOffset) {
        public static final Codec<CanopyContext> CODEC = RecordCodecBuilder.create(builder -> {
           return builder.group(
                   ResourceLocation.CODEC.fieldOf("canopyLocation").forGetter(canopyContext -> canopyContext.canopyLocation),
                   StructureProcessorType.SINGLE_CODEC.listOf().fieldOf("processors").forGetter(canopyContext -> canopyContext.processors),
                   Codec.INT.fieldOf("xOffset").forGetter(canopyContext -> canopyContext.xOffset),
                   Codec.INT.fieldOf("zOffset").forGetter(canopyContext -> canopyContext.zOffset)).apply(builder, CanopyContext::new);
        });
    }

    public static class Builder {
        private BlockStateProvider trunkProvider = SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState());
        private BlockStateProvider leavesProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());
        private BlockStateProvider diskProvider = SimpleStateProvider.simple(Blocks.PODZOL.defaultBlockState());
        private List<Block> whitelist = ImmutableList.of(Blocks.GRASS_BLOCK);
        private int minHeight = 15;
        private int maxPossibleHeight = 1;
        private int diskRadius = 0;

        public BYGStructureTreeConfig.Builder setTrunkBlock(Block block) {
            if (block != null)
                trunkProvider = SimpleStateProvider.simple(block.defaultBlockState());
            else
                trunkProvider = SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState());

            return this;
        }

        public BYGStructureTreeConfig.Builder setTrunkBlock(BlockState state) {
            if (state != null)
                trunkProvider = SimpleStateProvider.simple(state);
            else
                trunkProvider = SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState());

            return this;
        }

        public BYGStructureTreeConfig.Builder setTrunkBlock(BlockStateProvider stateProvider) {
            if (stateProvider != null)
                trunkProvider = stateProvider;
            else
                trunkProvider = SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState());

            return this;
        }

        public BYGStructureTreeConfig.Builder setLeavesBlock(Block block) {
            if (block != null)
                leavesProvider = SimpleStateProvider.simple(block.defaultBlockState());
            else
                leavesProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());

            return this;
        }

        public BYGStructureTreeConfig.Builder setLeavesBlock(BlockState state) {
            if (state != null)
                leavesProvider = SimpleStateProvider.simple(state);
            else
                leavesProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());

            return this;
        }

        public BYGStructureTreeConfig.Builder setLeavesBlock(BlockStateProvider stateProvider) {
            if (stateProvider != null)
                leavesProvider = stateProvider;
            else
                leavesProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());

            return this;
        }

        public BYGStructureTreeConfig.Builder setDiskBlock(Block block) {
            if (block != null)
                diskProvider = SimpleStateProvider.simple(block.defaultBlockState());
            else
                diskProvider = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

            return this;
        }

        public BYGStructureTreeConfig.Builder setDiskBlock(BlockState state) {
            if (state != null)
                diskProvider = SimpleStateProvider.simple(state);
            else
                diskProvider = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

            return this;
        }

        public BYGStructureTreeConfig.Builder setDiskBlock(BlockStateProvider stateProvider) {
            if (stateProvider != null)
                diskProvider = stateProvider;
            else
                diskProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());

            return this;
        }

        public BYGStructureTreeConfig.Builder setMinHeight(int minHeight) {
            this.minHeight = minHeight;
            return this;
        }

        public BYGStructureTreeConfig.Builder setMaxHeight(int maxPossibleHeight) {
            if (maxPossibleHeight != 0)
                this.maxPossibleHeight = maxPossibleHeight + 1;
            else
                this.maxPossibleHeight = 1;
            return this;
        }

        public BYGStructureTreeConfig.Builder setDiskRadius(int diskRadius) {
            this.diskRadius = Math.abs(diskRadius);
            return this;
        }

        public BYGStructureTreeConfig.Builder setWhitelist(ImmutableList<Block> whitelist) {
            this.whitelist = whitelist;
            return this;
        }

        public BYGStructureTreeConfig.Builder copy(BYGStructureTreeConfig config) {
            this.trunkProvider = config.trunkProvider;
            this.leavesProvider = config.leavesProvider;
            this.diskProvider = config.diskProvider;
            this.maxPossibleHeight = config.maxHeight;
            this.minHeight = config.minHeight;
            this.diskRadius = config.diskRadius;
            this.whitelist = ImmutableList.copyOf(config.whitelist);
            return this;
        }

        public BYGStructureTreeConfig build(CanopyContext canopyTarget) {
            return new BYGStructureTreeConfig(canopyTarget, this.trunkProvider, this.leavesProvider, this.diskProvider, this.minHeight, this.maxPossibleHeight, this.diskRadius, this.whitelist.stream().map(Block::defaultBlockState).collect(Collectors.toList()));
        }
    }

}
