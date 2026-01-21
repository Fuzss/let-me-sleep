package fuzs.letmesleep.mixin;

import com.mojang.datafixers.util.Either;
import fuzs.letmesleep.LetMeSleep;
import fuzs.letmesleep.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
abstract class PlayerMixin extends LivingEntity {
    @Shadow
    private int sleepCounter;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "startSleepInBed", at = @At("TAIL"))
    public void startSleepInBed(BlockPos bedPos, CallbackInfoReturnable<Either<Player.BedSleepingProblem, Unit>> callback) {
        if (!LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.instantSleeping) {
            return;
        }

        this.sleepCounter = Player.SLEEP_DURATION;
    }
}
