package fuzs.letmesleep.neoforge.mixin;

import fuzs.letmesleep.common.handler.BedRuleHandler;
import fuzs.letmesleep.common.init.ModRegistry;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnvironmentAttributeSystem.class)
abstract class EnvironmentAttributeSystemNeoForgeMixin {

    @Inject(method = "addStaticLayers",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/world/attribute/EnvironmentAttributeSystem;addBiomeLayer(Lnet/minecraft/world/attribute/EnvironmentAttributeSystem$Builder;Lnet/minecraft/core/HolderLookup;Lnet/minecraft/world/level/biome/BiomeManager;)V"))
    private static void addStaticLayers(EnvironmentAttributeSystem.Builder builder, LevelAccessor level, CallbackInfo callback) {
        if (!(level instanceof Level)) {
            return;
        }

        if (!((Level) level).dimensionTypeRegistration()
                .is(ModRegistry.OVERRIDES_BED_RULE_DIMENSION_TYPE_TAG)) {
            return;
        }

        builder.addConstantLayer(EnvironmentAttributes.BED_RULE, BedRuleHandler::modifyNormalBedRule);
        builder.addConstantLayer(EnvironmentAttributes.STRAW_BED_RULE, BedRuleHandler::modifyStrawBedRule);
    }
}
