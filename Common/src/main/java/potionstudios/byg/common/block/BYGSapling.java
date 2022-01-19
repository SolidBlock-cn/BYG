package potionstudios.byg.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import potionstudios.byg.common.world.feature.overworld.trees.util.TreeSpawner;

import java.util.Random;

public class BYGSapling extends SaplingBlock {
    private final Tag<Block> groundTag;
    private final TreeSpawner tree;

    public BYGSapling(Properties properties, Tag<Block> groundTag, TreeSpawner tree) {
        super(null, properties);
        this.groundTag = groundTag;
        this.tree = tree;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return this == BYGBlocks.PALM_SAPLING ? state.is(BlockTags.SAND) && state.is(this.groundTag) : state.is(this.groundTag);
    }

    @Override
    public void advanceTree(ServerLevel world, BlockPos pos, BlockState state, Random rand) {
        if (state.getValue(STAGE) == 0) {
            world.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            this.tree.spawn(world, world.getChunkSource().getGenerator(), pos, state, rand);
        }
    }
}