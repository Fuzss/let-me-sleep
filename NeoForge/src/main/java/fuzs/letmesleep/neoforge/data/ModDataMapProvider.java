package fuzs.letmesleep.neoforge.data;

import fuzs.letmesleep.common.init.ModRegistry;
import fuzs.letmesleep.common.world.effect.MobEffectTemplate;
import fuzs.neoforgedatapackextensions.neoforge.api.v1.NeoForgeDataMapToken;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.common.data.DataMapProvider;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {

    public ModDataMapProvider(DataProviderContext context) {
        this(context.getPackOutput(), context.getRegistries());
    }

    public ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider registries) {
        this.builder(NeoForgeDataMapToken.unwrap(ModRegistry.WAKE_UP_EFFECTS_DATA_MAP_TYPE))
                .add(MobEffects.MOVEMENT_SPEED, new MobEffectTemplate(15), false);
    }
}
