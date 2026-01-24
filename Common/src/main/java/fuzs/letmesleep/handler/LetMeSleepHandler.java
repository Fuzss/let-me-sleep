package fuzs.letmesleep.handler;

import fuzs.letmesleep.LetMeSleep;
import fuzs.letmesleep.config.ServerConfig;
import fuzs.letmesleep.init.ModRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;

import java.util.List;

public class LetMeSleepHandler {

    public static void onHandleNearbyMonsters(List<Monster> nearbyMonsters, RandomSource randomSource) {
        if (LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.bedChecks.removeMonstersNearbyCheck) {
            nearbyMonsters.clear();
        }

        if (LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.nearbyMonsters.revealNearbyMonsters) {
            if (!nearbyMonsters.isEmpty()) {
                Util.getRandom(nearbyMonsters, randomSource).playAmbientSound();
                for (Monster monster : nearbyMonsters) {
                    monster.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60));
                }
            }
        }
    }

    public static boolean isPreventingPlayerRest(Mob mob, ServerLevel serverLevel, Player player) {
        if (mob.getType().is(ModRegistry.NEVER_PREVENTS_PLAYER_REST_ENTITY_TYPE_TAG)) {
            return false;
        }

        if (LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.nearbyMonsters.ignorePersistentMonsters
                && mob.isPersistenceRequired()) {
            return false;
        }

        if (LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.nearbyMonsters.ignoreMonstersOutsideOfReach
                && !LetMeSleepHandler.canReachTarget(mob, player)) {
            return false;
        }

        if (LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.nearbyMonsters.ignoreMonstersWhenNotAngry
                && mob instanceof NeutralMob neutralMob && !neutralMob.isAngryAt(player, serverLevel)) {
            return false;
        }

        return true;
    }

    /**
     * @see net.minecraft.world.entity.ai.goal.target.TargetGoal#canReach(LivingEntity)
     */
    private static boolean canReachTarget(Mob mob, LivingEntity target) {
        Path path = mob.getNavigation().createPath(target, 0);
        if (path == null) {
            return false;
        } else {
            Node node = path.getEndNode();
            if (node == null) {
                return false;
            } else {
                int deltaX = node.x - target.getBlockX();
                int deltaZ = node.z - target.getBlockZ();
                return deltaX * deltaX + deltaZ * deltaZ <= 2.25;
            }
        }
    }
}
