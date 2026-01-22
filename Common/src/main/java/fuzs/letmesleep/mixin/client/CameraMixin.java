package fuzs.letmesleep.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import fuzs.letmesleep.LetMeSleep;
import fuzs.letmesleep.config.ClientConfig;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Camera.class)
abstract class CameraMixin {

    @ModifyArg(method = "setup",
               at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setRotation(FF)V"),
               index = 1,
               slice = @Slice(from = @At(value = "INVOKE",
                                         target = "Lnet/minecraft/world/entity/LivingEntity;getBedOrientation()Lnet/minecraft/core/Direction;")))
    public float setup(float xRot, @Local(argsOnly = true) Entity entity, @Local(argsOnly = true,
                                                                                 ordinal = 0) float partialTick) {
        if (!LetMeSleep.CONFIG.get(ClientConfig.class).fallingAsleepAnimation) {
            return xRot;
        }

        if (entity instanceof Player player) {
            float sleepTimerScale = Math.clamp((player.getSleepTimer() + partialTick) / (Player.SLEEP_DURATION * 0.65F),
                    0.0F,
                    1.0F);
            return xRot - (float) Math.pow(1.0 - sleepTimerScale, 4.0) * 45.0F;
        } else {
            return xRot;
        }
    }
}
