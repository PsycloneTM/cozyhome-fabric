package net.luckystudio.cozyhome.util;

import net.luckystudio.cozyhome.CozyHome;
import net.luckystudio.cozyhome.block.custom.counters.StorageCounterScreenHandler;
import net.luckystudio.cozyhome.block.custom.drawers.DrawerScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;

/**
 * Screen handler types for the drawer and the storage counter.
 *
 * These MUST live in common code and be registered from {@link CozyHome#onInitialize()}.
 * They used to be static fields of {@code CozyHomeClient}, which is client-only: a dedicated
 * server never registered them, and {@code DrawerScreenHandler}/{@code StorageCounterScreenHandler}
 * referencing that class made opening a drawer or storage counter throw
 * "Cannot load class net.luckystudio.cozyhome.CozyHomeClient in environment type SERVER".
 * The client still binds each type to its screen ({@code HandledScreens.register}) in
 * {@code CozyHomeClient#onInitializeClient()}.
 */
public class ModScreenHandlers {

    public static final ScreenHandlerType<StorageCounterScreenHandler> STORAGE_COUNTER_SCREEN_HANDLER = Registry.register(
            Registries.SCREEN_HANDLER, CozyHome.id("storage_counter"),
            new ScreenHandlerType<>(StorageCounterScreenHandler::new, FeatureSet.empty()));

    public static final ScreenHandlerType<DrawerScreenHandler> DRAWER_SCREEN_HANDLER = Registry.register(
            Registries.SCREEN_HANDLER, CozyHome.id("drawer"),
            new ScreenHandlerType<>(DrawerScreenHandler::new, FeatureSet.empty()));

    /** Touching this class runs the static initialisers above, which does the registering. */
    public static void registerScreenHandlers() {
        CozyHome.LOGGER.debug("Registering screen handlers for {}", CozyHome.MOD_ID);
    }
}
