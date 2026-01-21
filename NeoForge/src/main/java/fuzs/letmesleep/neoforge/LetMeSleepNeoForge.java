package fuzs.letmesleep.neoforge;

import fuzs.letmesleep.LetMeSleep;
import fuzs.letmesleep.data.tags.ModDamageTypeTagsProvider;
import fuzs.letmesleep.data.tags.ModMobEffectTagsProvider;
import fuzs.letmesleep.handler.WellRestedHandler;
import fuzs.letmesleep.neoforge.data.ModDataMapProvider;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;

@Mod(LetMeSleep.MOD_ID)
public class LetMeSleepNeoForge {

    public LetMeSleepNeoForge() {
        ModConstructor.construct(LetMeSleep.MOD_ID, LetMeSleep::new);
        registerEventHandlers(NeoForge.EVENT_BUS);
        DataProviderHelper.registerDataProviders(LetMeSleep.MOD_ID,
                ModDamageTypeTagsProvider::new,
                ModMobEffectTagsProvider::new,
                ModDataMapProvider::new);
    }

    private static void registerEventHandlers(IEventBus eventBus) {
        eventBus.addListener((final PlayerWakeUpEvent event) -> {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                WellRestedHandler.onPlayerWakeUp(serverPlayer, event.wakeImmediately(), event.updateLevel());
            }
        });
    }
}
