package fuzs.letmesleep.common.data.tags;

import fuzs.letmesleep.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v3.tags.AbstractTagAppender;
import fuzs.puzzleslib.common.api.data.v3.tags.AbstractTagsProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModMobEffectTagsProvider extends AbstractTagsProvider<MobEffect> {

    public ModMobEffectTagsProvider(DataProviderContext context) {
        super(Registries.MOB_EFFECT, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        AbstractTagAppender<MobEffect> tag = this.tag(ModRegistry.CLEARED_WHEN_WAKING_UP_MOB_EFFECT_TAG);
        provider.lookupOrThrow(Registries.MOB_EFFECT).listElements().filter((Holder.Reference<MobEffect> mobEffect) -> {
            return mobEffect.value().getCategory() == MobEffectCategory.HARMFUL;
        }).forEach(tag::add);
    }
}
