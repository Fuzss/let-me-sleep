package fuzs.letmesleep.data.tags;

import fuzs.letmesleep.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

public class ModDamageTypeTagsProvider extends AbstractTagProvider<DamageType> {

    public ModDamageTypeTagsProvider(DataProviderContext context) {
        super(Registries.DAMAGE_TYPE, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(ModRegistry.NO_SLEEPING_INTERRUPTION_DAMAGE_TYPE_TAG)
                .addKey(DamageTypes.MAGIC)
                .addKey(DamageTypes.INDIRECT_MAGIC);
    }
}
