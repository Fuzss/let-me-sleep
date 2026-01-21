package fuzs.letmesleep.fabric.client;

import fuzs.letmesleep.LetMeSleep;
import fuzs.letmesleep.client.LetMeSleepClient;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class LetMeSleepFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(LetMeSleep.MOD_ID, LetMeSleepClient::new);
    }
}
