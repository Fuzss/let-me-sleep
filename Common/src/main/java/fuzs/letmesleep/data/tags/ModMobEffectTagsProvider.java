package fuzs.letmesleep.data.tags;

import fuzs.letmesleep.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModMobEffectTagsProvider extends AbstractTagProvider<MobEffect> {

    public ModMobEffectTagsProvider(DataProviderContext context) {
        super(Registries.MOB_EFFECT, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(ModRegistry.CLEARED_WHEN_WAKING_UP_MOB_EFFECT_TAG)
                .addAll(provider.lookupOrThrow(Registries.MOB_EFFECT)
                        .listElements()
                        .map(Holder.Reference::value)
                        .filter((MobEffect mobEffect) -> {
                            return mobEffect.getCategory() == MobEffectCategory.HARMFUL;
                        }));
    }
}
