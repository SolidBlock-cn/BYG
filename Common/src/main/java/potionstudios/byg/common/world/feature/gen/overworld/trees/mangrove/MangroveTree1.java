package potionstudios.byg.common.world.feature.gen.overworld.trees.mangrove;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import potionstudios.byg.common.world.feature.config.BYGTreeConfig;
import potionstudios.byg.common.world.feature.gen.overworld.trees.util.BYGAbstractTreeFeature;

import java.util.Random;
import java.util.Set;

public class MangroveTree1 extends BYGAbstractTreeFeature<BYGTreeConfig> {

    public MangroveTree1(Codec<BYGTreeConfig> configIn) {
        super(configIn);
    }

    protected boolean generate(Set<BlockPos> changedBlocks, WorldGenLevel worldIn, Random rand, BlockPos pos, BoundingBox boundsIn, boolean isSapling, BYGTreeConfig config) {

        int randTreeHeight = config.getMinHeight() + rand.nextInt(config.getMaxPossibleHeight());
        BlockPos.MutableBlockPos mainmutable = new BlockPos.MutableBlockPos().set(pos);

        if (pos.getY() + randTreeHeight + 1 < worldIn.getMaxBuildHeight()) {
            if (!isDesiredGroundwDirtTag(worldIn, pos.below(), config)) {
                return false;
            } else if (!this.isAnotherTreeNearby(worldIn, pos, randTreeHeight, 0, isSapling)) {
                return false;
            } else if (!this.doesSaplingHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isSapling)) {
                return false;
            } else {

                for (int buildTrunk = 3; buildTrunk <= randTreeHeight; buildTrunk++) {
                    if (buildTrunk == 3)
                        mainmutable.move(Direction.UP, 3);
                    placeTrunk(pos, config, rand, changedBlocks, worldIn, mainmutable, boundsIn);

                    mainmutable.move(Direction.UP);
                }
                mainmutable.set(pos);

                //Roots
                BlockPos.MutableBlockPos rootMutable = new BlockPos.MutableBlockPos().set(mainmutable.offset(0, 0, -2));
                BlockPos.MutableBlockPos rootMutable2 = new BlockPos.MutableBlockPos().set(mainmutable.offset(-1, 0, -1));
                BlockPos.MutableBlockPos rootMutable3 = new BlockPos.MutableBlockPos().set(mainmutable.offset(2, 0, -4));
                BlockPos.MutableBlockPos rootMutable4 = new BlockPos.MutableBlockPos().set(mainmutable.offset(-3, 0, 1));
                BlockPos.MutableBlockPos rootMutable5 = new BlockPos.MutableBlockPos().set(mainmutable.offset(1, 0, 1));
                BlockPos.MutableBlockPos rootMutable6 = new BlockPos.MutableBlockPos().set(mainmutable.offset(-2, 0, 2));

                //Stump
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, 0, -2), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, 0, -1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, 0, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, 0, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, 0, 1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, 0, 2), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, 1, -1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, 1, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, 1, 1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, 2, -1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, 2, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, 2, 1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, 3, -1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, 3, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, 3, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, 4, -1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, 4, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, 4, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, 5, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, 6, 0), boundsIn);


                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, -1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, -1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, 0), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight, 1), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight, 2), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight, 3), boundsIn);
                placeBranch(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight - 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight - 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight - 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight - 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight - 1, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight - 1, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight - 1, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight - 1, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight - 1, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight - 1, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight - 1, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight - 1, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight - 1, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight - 1, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight - 1, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight - 1, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, -4), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, -4), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight, -3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, -3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight, -3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, -3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, -3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(3, randTreeHeight, -3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-3, randTreeHeight, -2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight, -2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, -2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight, -2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, -2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, -2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(3, randTreeHeight, -2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(4, randTreeHeight, -2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-4, randTreeHeight, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-3, randTreeHeight, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(3, randTreeHeight, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(4, randTreeHeight, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(5, randTreeHeight, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-4, randTreeHeight, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-3, randTreeHeight, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(3, randTreeHeight, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(4, randTreeHeight, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(5, randTreeHeight, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-3, randTreeHeight, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(3, randTreeHeight, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-3, randTreeHeight, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(3, randTreeHeight, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-3, randTreeHeight, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(3, randTreeHeight, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(4, randTreeHeight, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight, 4), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, 4), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight, 4), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, 4), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, 4), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(3, randTreeHeight, 4), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight, 5), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight, 5), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, 5), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight, 5), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight, 6), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight, 6), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-2, randTreeHeight + 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight + 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight + 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight + 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight + 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(3, randTreeHeight + 1, -1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight + 1, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight + 1, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight + 1, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight + 1, 0), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(-1, randTreeHeight + 1, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight + 1, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight + 1, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(2, randTreeHeight + 1, 1), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight + 1, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight + 1, 2), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(0, randTreeHeight + 1, 3), boundsIn);
                placeLeaves(pos, config, rand, changedBlocks, worldIn, mainmutable.set(pos).move(1, randTreeHeight + 1, 3), boundsIn);
            }
        }
        return true;
    }
}