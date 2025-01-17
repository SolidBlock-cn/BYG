package potionstudios.byg.mixin.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import potionstudios.byg.client.gui.screen.HypogealImperiumScreen;
import potionstudios.byg.common.container.BYGMenuTypes;

@SuppressWarnings("UnresolvedMixinReference")
@Mixin(MenuScreens.class)
public abstract class MixinScreenManager {

    @Shadow
    private static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void register(MenuType<? extends M> p_216911_0_, MenuScreens.ScreenConstructor<M, U> p_216911_1_) {
        throw new Error("Mixin did not apply!");
    }

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void registerScreen(CallbackInfo ci) {
        register(BYGMenuTypes.HYPOGEAL_CONTAINER, HypogealImperiumScreen::new);
    }
}
