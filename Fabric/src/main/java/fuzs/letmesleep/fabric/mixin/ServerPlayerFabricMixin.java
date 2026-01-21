package fuzs.letmesleep.fabric.mixin;

import com.mojang.authlib.GameProfile;
import fuzs.letmesleep.handler.LetMeSleepHandler;
import fuzs.letmesleep.handler.WellRestedHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerPlayer.class)
abstract class ServerPlayerFabricMixin extends Player {

    public ServerPlayerFabricMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @ModifyVariable(method = "startSleepInBed", at = @At("STORE"))
    public List<Monster> startSleepInBed(List<Monster> list) {
        LetMeSleepHandler.onHandleNearbyMonsters(list, this.getRandom());
        return list;
    }

    @Inject(method = "stopSleepInBed", at = @At("HEAD"))
    public void stopSleepInBed(boolean wakeImmediately, boolean updateLevelForSleepingPlayers, CallbackInfo callback) {
        WellRestedHandler.onPlayerWakeUp(ServerPlayer.class.cast(this), wakeImmediately, updateLevelForSleepingPlayers);
    }
}
