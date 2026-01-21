package fuzs.letmesleep.neoforge.client;

import fuzs.letmesleep.LetMeSleep;
import fuzs.letmesleep.client.LetMeSleepClient;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = LetMeSleep.MOD_ID, dist = Dist.CLIENT)
public class LetMeSleepNeoForgeClient {

    public LetMeSleepNeoForgeClient() {
        ClientModConstructor.construct(LetMeSleep.MOD_ID, LetMeSleepClient::new);
    }
}
