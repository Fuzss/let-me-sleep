package fuzs.letmesleep.fabric;

import fuzs.letmesleep.LetMeSleep;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class LetMeSleepFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(LetMeSleep.MOD_ID, LetMeSleep::new);
    }
}
