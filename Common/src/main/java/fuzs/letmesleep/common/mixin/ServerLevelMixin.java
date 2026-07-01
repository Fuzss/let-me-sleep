package fuzs.letmesleep.common.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import fuzs.letmesleep.common.LetMeSleep;
import fuzs.letmesleep.common.config.ServerConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ServerLevel.class)
abstract class ServerLevelMixin extends Level {

    protected ServerLevelMixin(WritableLevelData levelData, ResourceKey<Level> dimension, RegistryAccess registryAccess, Holder<DimensionType> dimensionTypeRegistration, Supplier<ProfilerFiller> profiler, boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
        super(levelData,
                dimension,
                registryAccess,
                dimensionTypeRegistration,
                profiler,
                isClientSide,
                isDebug,
                biomeZoomSeed,
                maxChainedNeighborUpdates);
    }

    @Inject(method = "tick",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/server/level/ServerLevel;setDayTime(J)V",
                     shift = At.Shift.AFTER))
    public void tick(CallbackInfo callback) {
        this.setDayTime(this.getDayTime() + LetMeSleep.CONFIG.get(ServerConfig.class).wakingUp.wakeUpTime);
    }

    @Shadow
    public abstract void setDayTime(long time);

    @ModifyExpressionValue(method = "tick",
                           at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;isRaining()Z"))
    public boolean tick(boolean isRaining) {
        if (!LetMeSleep.CONFIG.get(ServerConfig.class).wakingUp.alwaysResetWeatherCycle) {
            return isRaining;
        }

        return true;
    }
}
