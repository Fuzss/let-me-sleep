package fuzs.letmesleep.common.handler;

import fuzs.letmesleep.common.LetMeSleep;
import fuzs.letmesleep.common.config.ServerConfig;
import fuzs.letmesleep.common.init.ModRegistry;
import fuzs.letmesleep.common.world.effect.MobEffectTemplate;
import fuzs.neoforgedatapackextensions.api.v1.DataMapRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;

public class WellRestedHandler {

    public static void onPlayerWakeUp(ServerPlayer serverPlayer, boolean wakeImmediately, boolean updateLevelForSleepingPlayers) {
        if (!wakeImmediately && !updateLevelForSleepingPlayers) {
            int restoredHealth = LetMeSleep.CONFIG.get(ServerConfig.class).wakingUp.healthRestored;
            if (restoredHealth > 0) {
                serverPlayer.heal(restoredHealth);
            } else if (restoredHealth < 0) {
                serverPlayer.hurt(serverPlayer.damageSources().magic(), restoredHealth);
            }

            int consumedHunger = LetMeSleep.CONFIG.get(ServerConfig.class).wakingUp.hungerConsumed;
            if (consumedHunger > 0) {
                serverPlayer.causeFoodExhaustion(consumedHunger * 4.0F);
            } else if (consumedHunger < 0) {
                serverPlayer.getFoodData().eat(-consumedHunger, 0.0F);
            }

            Registry<MobEffect> registry = serverPlayer.registryAccess().registryOrThrow(Registries.MOB_EFFECT);
            registry.getTagOrEmpty(ModRegistry.CLEARED_WHEN_WAKING_UP_MOB_EFFECT_TAG)
                    .forEach(serverPlayer::removeEffect);
            registry.holders().forEach((Holder.Reference<MobEffect> holder) -> {
                MobEffectTemplate template = DataMapRegistry.INSTANCE.getData(ModRegistry.WAKE_UP_EFFECTS_DATA_MAP_TYPE,
                        holder);
                if (template != null) {
                    serverPlayer.addEffect(template.createInstance(holder));
                }
            });
        }
    }
}
