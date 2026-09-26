package fuzs.letmesleep.fabric;

import fuzs.letmesleep.common.LetMeSleep;
import fuzs.letmesleep.common.handler.BedRuleHandler;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.dimension.v1.DimensionEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.level.dimension.DimensionType;

public class LetMeSleepFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(LetMeSleep.MOD_ID, LetMeSleep::new);
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        DimensionEvents.MODIFY_ATTRIBUTES.register((Holder<DimensionType> dimension, EnvironmentAttributeMap.Builder attributes, HolderLookup.Provider lookupProvider) -> {
            BedRuleHandler.modifyDimensionAttributes(dimension, attributes);
        });
    }
}
