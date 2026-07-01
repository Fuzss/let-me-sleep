package fuzs.letmesleep.neoforge.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import fuzs.letmesleep.common.LetMeSleep;
import fuzs.letmesleep.common.config.ServerConfig;
import fuzs.letmesleep.common.handler.LetMeSleepHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(ServerPlayer.class)
abstract class ServerPlayerNeoForgeMixin extends Player {

    public ServerPlayerNeoForgeMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

    @ModifyExpressionValue(method = "lambda$startSleepInBed$13",
                           at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isDay()Z"))
    public boolean startSleepInBed(boolean isDay) {
        if (!LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.allowSleepingDuringRain) {
            return isDay;
        }

        return isDay && !this.level().isRaining();
    }

    @ModifyVariable(method = "lambda$startSleepInBed$13", at = @At("STORE"))
    public List<Monster> startSleepInBed(List<Monster> monsters) {
        LetMeSleepHandler.onHandleNearbyMonsters(monsters, this.getRandom());
        return monsters;
    }
}
