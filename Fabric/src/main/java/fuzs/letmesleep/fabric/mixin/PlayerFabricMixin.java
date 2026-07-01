package fuzs.letmesleep.fabric.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import fuzs.letmesleep.common.handler.LetMeSleepHandler;
import fuzs.puzzleslib.api.event.v1.core.EventResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
abstract class PlayerFabricMixin extends LivingEntity {

    protected PlayerFabricMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyExpressionValue(method = "tick",
                           at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isDay()Z"))
    public boolean tick(boolean isDay) {
        EventResult result = LetMeSleepHandler.onLivingStopSleeping(this);
        return result.isInterrupt() ? result.getAsBoolean() : isDay;
    }
}
