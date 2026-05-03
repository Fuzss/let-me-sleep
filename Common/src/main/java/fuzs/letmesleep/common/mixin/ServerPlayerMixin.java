package fuzs.letmesleep.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import fuzs.letmesleep.common.LetMeSleep;
import fuzs.letmesleep.common.config.ServerConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayer.class)
abstract class ServerPlayerMixin extends Player {

    public ServerPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @ModifyReturnValue(method = "isReachableBedBlock", at = @At("RETURN"))
    private boolean isReachableBedBlock(boolean isReachableBedBlock) {
        if (!LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.bedChecks.removeRangeCheck) {
            return isReachableBedBlock;
        }

        return true;
    }

    @ModifyReturnValue(method = "bedBlocked", at = @At("RETURN"))
    private boolean bedBlocked(boolean bedBlocked) {
        if (!LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.bedChecks.removeObstructionCheck) {
            return bedBlocked;
        }

        return false;
    }
}
