package potionstudios.byg.common.world.tree.processors;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import potionstudios.byg.BYG;

public class BYGStructureProcessorTypes {

    public static final StructureProcessorType<TrunkFillingProcessor> TRUNK_FILLING = Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(BYG.MOD_ID, "trunk_filling"), () -> TrunkFillingProcessor.CODEC);
    public static final StructureProcessorType<TrunkThickeningProcessor> TRUNK_THICKENER = Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(BYG.MOD_ID, "trunk_thickening"), () -> TrunkThickeningProcessor.CODEC);
    public static final StructureProcessorType<LeavesDistanceProcessor> LEAVES_DISTANCE = Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(BYG.MOD_ID, "leaves_distance"), () -> LeavesDistanceProcessor.CODEC);
    public static final StructureProcessorType<RemoveAirProcessor> REMOVE_AIR = Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(BYG.MOD_ID, "remove_air"), () -> RemoveAirProcessor.CODEC);



}
