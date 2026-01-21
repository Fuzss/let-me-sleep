package fuzs.letmesleep.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import fuzs.letmesleep.LetMeSleep;
import fuzs.letmesleep.config.ServerConfig;
import fuzs.letmesleep.handler.LetMeSleepHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Monster.class)
abstract class MonsterMixin extends PathfinderMob {

    protected MonsterMixin(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyReturnValue(method = "isPreventingPlayerRest", at = @At("RETURN"))
    public boolean isPreventingPlayerRest(boolean isPreventingPlayerRest, ServerLevel serverLevel, Player player) {
        if (!isPreventingPlayerRest) {
            return false;
        }

        if (LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.ignorePersistentMonsters
                && this.isPersistenceRequired()) {
            return false;
        }

        if (LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.ignoreMonstersOutsideOfReach
                && !LetMeSleepHandler.canReachTarget(this, player)) {
            return false;
        }

        if (LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.ignoreMonstersWhenNotAngry
                && this instanceof NeutralMob neutralMob && !neutralMob.isAngryAt(player, serverLevel)) {
            return false;
        }

        return true;
    }
}
