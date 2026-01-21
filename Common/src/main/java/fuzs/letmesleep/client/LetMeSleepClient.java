package fuzs.letmesleep.client;

import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;

public class LetMeSleepClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
    }
}
