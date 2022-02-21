package potionstudios.byg.common.world.feature.gen.overworld.mushrooms;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import potionstudios.byg.common.block.BYGBlocks;
import potionstudios.byg.common.world.feature.config.BYGMushroomConfig;
import potionstudios.byg.common.world.feature.gen.overworld.mushrooms.util.BYGAbstractMushroomFeature;

import java.util.Random;

public class SoulShroomTree3 extends BYGAbstractMushroomFeature<BYGMushroomConfig> {

    public SoulShroomTree3(Codec<BYGMushroomConfig> configIn) {
        super(configIn);
    }

    protected boolean placeMushroom(WorldGenLevel worldIn, Random rand, BlockPos pos, boolean isMushroom, BYGMushroomConfig config) {
        BlockState STEM = config.getStemProvider().getState(rand, pos);
        BlockState MUSHROOM = config.getMushroomProvider().getState(rand, pos);
        BlockState MUSHROOM2 = config.getMushroom2Provider().getState(rand, pos);
        BlockState MUSHROOM3 = config.getMushroom3Provider().getState(rand, pos);
        BlockState POLLEN = config.getPollenProvider().getState(rand, pos);
        int randTreeHeight = 12 + rand.nextInt(5);
        BlockPos.MutableBlockPos mainmutable = new BlockPos.MutableBlockPos().set(pos);

        if (pos.getY() + randTreeHeight + 1 < worldIn.getMaxBuildHeight()) {
            if (!isDesiredGroundwDirtTag(config, worldIn, pos.below(), Blocks.GRASS_BLOCK)) {
                return false;
            } else if (!this.isAnotherMushroomLikeThisNearby(worldIn, pos, randTreeHeight, 0, STEM.getBlock(), MUSHROOM.getBlock(), isMushroom)) {
                return false;
            } else if (!this.doesMushroomHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isMushroom)) {
                return false;
            } else {
                placeStem(STEM, worldIn, mainmutable.set(pos).move(0, 0, 0));
                placeStem(STEM, worldIn, mainmutable.set(pos).move(0, 1, 0));
                placeStem(STEM, worldIn, mainmutable.set(pos).move(0, 2, 0));
                placeStem(STEM, worldIn, mainmutable.set(pos).move(0, 3, 0));
                placeStem(STEM, worldIn, mainmutable.set(pos).move(0, 4, 0));
                placeStem(STEM, worldIn, mainmutable.set(pos).move(0, 5, 0));
                placeStem(STEM, worldIn, mainmutable.set(pos).move(0, 6, 0));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(1, 5, -1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-1, 6, -1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-1, 6, 1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(1, 6, -1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(1, 6, 1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-2, 7, -2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-1, 7, -1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-1, 7, 1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(1, 7, -1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(1, 7, 1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(2, 7, -2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(2, 7, 2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-3, 8, -3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-2, 8, -2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-2, 8, 2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-1, 8, 1));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(2, 8, -2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(2, 8, 2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-3, 9, -3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-3, 9, 3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-2, 9, 2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(2, 9, -2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(2, 9, 2));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(3, 9, -3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(3, 9, 3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-3, 10, -3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-3, 10, 3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(3, 10, -3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(3, 10, 3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(4, 10, -3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(-3, 11, 3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(4, 11, -3));
                placeStemBranch(STEM, worldIn, mainmutable.set(pos).move(4, 12, -3));
                this.soulShroomSporeEnd(worldIn, mainmutable.set(pos).move(5, 8, -5));
                this.soulShroomSporeEnd(worldIn, mainmutable.set(pos).move(-5, 9, -4));
                this.soulShroomSporeEnd(worldIn, mainmutable.set(pos).move(-4, 9, -2));
                this.soulShroomSporeEnd(worldIn, mainmutable.set(pos).move(3, 9, -4));
                this.soulShroomSporeEnd(worldIn, mainmutable.set(pos).move(4, 9, -1));
                this.soulShroomSpore(worldIn, mainmutable.set(pos).move(5, 9, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 10, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 10, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 10, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 10, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 10, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 10, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 10, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 10, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 10, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 10, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 10, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 10, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 10, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 10, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 10, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 10, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 10, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 10, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 10, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 10, -2));
                this.soulShroomSporeEnd(worldIn, mainmutable.set(pos).move(-1, 10, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(1, 10, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(1, 10, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(1, 10, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 10, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 10, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 10, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 10, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 10, 5));
                this.soulShroomSpore(worldIn, mainmutable.set(pos).move(3, 10, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 10, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 10, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 10, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 10, 5));
                this.soulShroomSpore(worldIn, mainmutable.set(pos).move(4, 10, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 10, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 10, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 10, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 10, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 10, 5));
                this.soulShroomSpore(worldIn, mainmutable.set(pos).move(5, 10, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 10, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 10, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 10, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 11, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 11, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 11, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 11, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 11, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 11, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 11, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 11, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 11, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 11, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 11, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 11, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 11, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 11, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 11, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 11, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 11, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 11, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 11, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 11, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 11, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 11, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 11, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 11, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(1, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 11, 4));
                this.soulShroomSpore(worldIn, mainmutable.set(pos).move(3, 11, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 11, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 11, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 11, 5));
                this.soulShroomSpore(worldIn, mainmutable.set(pos).move(4, 11, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 11, 4));
                this.soulShroomSpore(worldIn, mainmutable.set(pos).move(5, 11, -5));
                this.soulShroomSporeEnd(worldIn, mainmutable.set(pos).move(5, 11, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 11, 3));
                this.soulShroomSporeEnd(worldIn, mainmutable.set(pos).move(6, 11, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 12, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 12, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 12, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 12, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 12, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 12, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 12, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 12, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 12, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 12, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 12, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 12, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 12, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(1, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 12, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 12, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 12, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 12, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 12, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 12, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 12, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 12, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 12, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 12, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 12, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 12, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 12, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 12, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 12, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 12, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 12, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 12, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(6, 12, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(6, 12, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(6, 12, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-5, 13, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 13, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 13, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 13, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 13, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 13, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 13, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 13, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 13, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 13, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 13, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 13, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 13, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 13, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 13, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 13, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 13, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 13, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 13, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 13, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 13, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-1, 13, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 13, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 13, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 13, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 13, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 13, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 13, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 13, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 13, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 13, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 13, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 13, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 13, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 13, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 13, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 13, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 13, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 13, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 13, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 13, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 13, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 13, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(6, 13, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 14, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 14, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-4, 14, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 14, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 14, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 14, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 14, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 14, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 14, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-2, 14, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(2, 14, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 14, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 14, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 14, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 14, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 14, -5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 14, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 14, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 14, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 14, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 14, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 14, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 14, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(6, 14, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 15, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 15, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 15, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(3, 15, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 15, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 15, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 15, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 15, -4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 15, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(5, 15, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(-3, 16, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 16, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.set(pos).move(4, 17, -3));
            }
        }
        return true;
    }

    private void soulShroomSporeEnd(WorldGenLevel reader, BlockPos pos) {
        if (isAir(reader, pos)) {
            this.setFinalBlockState(reader, pos, BYGBlocks.SOUL_SHROOM_SPORE_END.defaultBlockState());
        }
    }

    //Leaves Placement
    private void soulShroomSpore(WorldGenLevel reader, BlockPos pos) {
        if (isAir(reader, pos)) {
            this.setFinalBlockState(reader, pos, BYGBlocks.SOUL_SHROOM_SPORE.defaultBlockState());
        }
    }
}