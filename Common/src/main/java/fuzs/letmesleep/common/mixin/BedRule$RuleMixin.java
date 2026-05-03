package fuzs.letmesleep.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import fuzs.letmesleep.common.LetMeSleep;
import fuzs.letmesleep.common.config.ServerConfig;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BedRule.Rule.class)
abstract class BedRule$RuleMixin {
    @Shadow
    @Final
    private static BedRule.Rule WHEN_DARK;

    @ModifyReturnValue(method = "test", at = @At("RETURN"))
    public boolean test(boolean testResult, Level level) {
        if (!LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.allowSleepingDuringRain) {
            return testResult;
        }

        return testResult || BedRule.Rule.class.cast(this) == WHEN_DARK && level.isRaining();
    }
}
