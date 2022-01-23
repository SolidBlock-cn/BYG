package potionstudios.byg.common.world.tree;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Random;

public class TreeStructureFeature extends Feature<BYGStructureTreeConfig> {
    public TreeStructureFeature(Codec<BYGStructureTreeConfig> $$0) {
        super($$0);
    }

    @Override
    public boolean place(FeaturePlaceContext<BYGStructureTreeConfig> featurePlaceContext) {
        BYGStructureTreeConfig config = featurePlaceContext.config();
        WorldGenLevel level = featurePlaceContext.level();
        BlockPos pos = featurePlaceContext.origin();
        level.setBlock(pos, Blocks.DIAMOND_BLOCK.defaultBlockState(), 2);
        Random random = featurePlaceContext.random();
        BYGStructureTreeConfig.CanopyContext canopyContext = config.getCanopyContext();

        StructureTemplate canopy = config.getCanopy(level);
        BlockPos canopyPos = pos.offset(canopyContext.xOffset(), config.getHeight(random), canopyContext.zOffset());

        StructurePlaceSettings placeSettings = new StructurePlaceSettings().setRandom(random);
        for (StructureProcessor processor : canopyContext.processors()) {
            placeSettings.addProcessor(processor);
        }

//        placeSettings.setRotation(Rotation.getRandom(random));
//        placeSettings.setMirror(Mirror.values()[random.nextInt(Mirror.values().length)]);

        return canopy.placeInWorld(level, canopyPos, canopyPos, placeSettings, random, 4);
    }
}
