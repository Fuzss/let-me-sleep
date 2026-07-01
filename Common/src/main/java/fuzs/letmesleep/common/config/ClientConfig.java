package fuzs.letmesleep.common.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;

public class ClientConfig implements ConfigCore {
    @Config(description = "Fall into your bed slowly and smoothly when beginning to sleep.")
    public boolean fallingAsleepAnimation = true;
}
