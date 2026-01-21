package fuzs.letmesleep.neoforge.mixin;

import com.mojang.authlib.GameProfile;
import fuzs.letmesleep.handler.LetMeSleepHandler;
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

    public ServerPlayerNeoForgeMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @ModifyVariable(method = "lambda$startSleepInBed$15", at = @At("STORE"))
    public List<Monster> startSleepInBed(List<Monster> list) {
        LetMeSleepHandler.onHandleNearbyMonsters(list, this.getRandom());
        return list;
    }
}
