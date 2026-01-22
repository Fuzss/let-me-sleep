package fuzs.letmesleep.handler;

import fuzs.letmesleep.LetMeSleep;
import fuzs.letmesleep.config.ServerConfig;
import fuzs.letmesleep.init.ModRegistry;
import fuzs.letmesleep.world.effect.MobEffectTemplate;
import fuzs.neoforgedatapackextensions.api.v2.DataMapLookup;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;

import java.util.Map;

public class WellRestedHandler {

    public static void onPlayerWakeUp(ServerPlayer serverPlayer, boolean wakeImmediately, boolean updateLevelForSleepingPlayers) {
        if (!wakeImmediately && !updateLevelForSleepingPlayers) {
            int restoredHealth = LetMeSleep.CONFIG.get(ServerConfig.class).wakingUp.healthRestored;
            if (restoredHealth > 0) {
                serverPlayer.heal(restoredHealth);
            } else if (restoredHealth < 0) {
                serverPlayer.hurtServer(serverPlayer.level(), serverPlayer.damageSources().magic(), restoredHealth);
            }

            int consumedHunger = LetMeSleep.CONFIG.get(ServerConfig.class).wakingUp.hungerConsumed;
            if (consumedHunger > 0) {
                serverPlayer.causeFoodExhaustion(consumedHunger * 4.0F);
            } else if (consumedHunger < 0) {
                serverPlayer.getFoodData().eat(-consumedHunger, 0.0F);
            }

            Registry<MobEffect> registry = serverPlayer.registryAccess().lookupOrThrow(Registries.MOB_EFFECT);
            registry.getTagOrEmpty(ModRegistry.CLEARED_WHEN_WAKING_UP_MOB_EFFECT_TAG)
                    .forEach(serverPlayer::removeEffect);
            for (Map.Entry<ResourceKey<MobEffect>, MobEffectTemplate> entry : DataMapLookup.getDataMap(registry,
                    ModRegistry.WAKE_UP_EFFECTS_DATA_MAP_TYPE).entrySet()) {
                registry.get(entry.getKey()).ifPresent((Holder.Reference<MobEffect> holder) -> {
                    serverPlayer.addEffect(entry.getValue().createInstance(holder));
                });
            }
        }
    }
}
