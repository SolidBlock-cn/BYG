package potionstudios.byg.common.entity.manowar;

//import com.google.common.collect.Maps;
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.math.Vector3f;
//import potionstudios.byg.BYG;
//import net.minecraft.Util;
//import net.minecraft.client.model.geom.ModelLayerLocation;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.Mth;
//import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
//
//import java.util.Map;
//
//public class ManOWarRenderer<T extends ManOWar> extends GeoEntityRenderer<T> {
//
//    public static final Map<ManOWar.Colors, ResourceLocation> TEXTURES = Util.make(Maps.newEnumMap(ManOWar.Colors.class), (map) -> {
//        map.put(ManOWar.Colors.MAGENTA, BYG.createLocation("textures/entity/manowar/magenta.png"));
//        map.put(ManOWar.Colors.RAINBOW, BYG.createLocation("textures/entity/manowar/rainbow.png"));
//        map.put(ManOWar.Colors.PURPLE, BYG.createLocation("textures/entity/manowar/purple.png"));
//        map.put(ManOWar.Colors.BLUE, BYG.createLocation("textures/entity/manowar/blue.png"));
//
//    });
//
//    public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(BYG.createLocation("man_o_war"), "main");
//
//    public ManOWarRenderer(EntityRendererProvider.Context context) {
//        super(context, new ManOWarModel());
//    }
//
//    /**
//     * Returns the location of an entity's texture.
//     */
//    @Override
//    public ResourceLocation getTextureLocation(T entity) {
//        return TEXTURES.getGenerator(entity.getColor());
//    }
//
//    protected void applyRotations(T squid, PoseStack poseStack, float f, float g, float h) {
//        float i = Mth.lerp(h, squid.xBodyRotO, squid.xBodyRot);
//        float j = Mth.lerp(h, squid.zBodyRotO, squid.zBodyRot);
//        poseStack.translate(0.0D, 0.5D, 0.0D);
//        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - g));
//        poseStack.mulPose(Vector3f.XP.rotationDegrees(i));
//        poseStack.mulPose(Vector3f.YP.rotationDegrees(j));
//        poseStack.translate(0.0D, -1.2000000476837158D, 0.0D);
//    }
//}
