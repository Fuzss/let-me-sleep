package fuzs.letmesleep.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;
import net.minecraft.SharedConstants;

public class ServerConfig implements ConfigCore {
    @Config
    public final GoingToSleep goingToSleep = new GoingToSleep();
    @Config
    public final WakingUp wakingUp = new WakingUp();

    public static class GoingToSleep implements ConfigCore {
        @Config
        public final BedChecks bedChecks = new BedChecks();
        @Config
        public final NearbyMonsters nearbyMonsters = new NearbyMonsters();
        @Config(description = "Allows the player to wake up instantly after going to bed. Only works on multiplayer servers when all other players are already asleep.")
        public boolean instantSleeping = false;
        @Config(description = "Allows the player to go to bed while it's raining. In vanilla sleeping is only possible at night or during thunderstorms.")
        public boolean allowSleepingDuringRain = true;
    }

    public static class BedChecks implements ConfigCore {
        @Config(description = "When trying to go to bed, remove the check if the player is close enough to the bed.")
        public boolean removeRangeCheck = true;
        @Config(description = "When trying to go to bed, remove the check if the bed has enough open space above it.")
        public boolean removeObstructionCheck = false;
        @Config(description = "When trying to go to bed, remove the check if monsters are nearby.")
        public boolean removeMonstersNearbyCheck = false;
    }

    public static class NearbyMonsters implements ConfigCore {
        @Config(description = "Should monsters preventing the player from sleeping glow for a short time and play an ambient sound.")
        public boolean revealNearbyMonsters = true;
        @Config(description = "Should sleeping be allowed when only persistent or named monsters are nearby.")
        public boolean ignorePersistentMonsters = true;
        @Config(description = "Should sleeping be allowed when only monsters unable to reach the player are nearby.")
        public boolean ignoreMonstersOutsideOfReach = true;
        @Config(description = "Should sleeping be allowed when only neutral monsters like enderman that are not angry at the player are nearby.")
        public boolean ignoreMonstersWhenNotAngry = true;
    }

    public static class WakingUp implements ConfigCore {
        @Config(description = {
                "The amount of health the player should regain when waking up from a bed after sleeping through the night.",
                "A value of zero will do nothing. Negative values instead cause damage to the player."
        })
        public int healthRestored = 20;
        @Config(description = {
                "The amount of food levels that should be taken from the player when waking up from a bed after sleeping through the night.",
                "A value of zero will do nothing. Negative values instead restore food levels damage to the player."
        })
        public int hungerConsumed = 1;
        @Config(description = "Reset the weather cycle to clear weather even when the current weather is already neither rain nor thunder.")
        public boolean alwaysResetWeatherCycle = true;
        @Config(description = "The time of day players wake up at after sleeping through the night. In vanilla this defaults to zero.")
        @Config.IntRange(min = 0, max = SharedConstants.TICKS_PER_GAME_DAY)
        public int wakeUpTime = 0;
    }
}
