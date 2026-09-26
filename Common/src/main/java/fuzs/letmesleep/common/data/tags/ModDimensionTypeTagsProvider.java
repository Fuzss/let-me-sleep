package fuzs.letmesleep.common.data.tags;

import fuzs.letmesleep.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v3.tags.AbstractTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensionTypeTagsProvider extends AbstractTagsProvider<DimensionType> {

    public ModDimensionTypeTagsProvider(DataProviderContext context) {
        super(Registries.DIMENSION_TYPE, context);
    }

    @Override
    public void addTags(HolderLookup.Provider context) {
        this.tag(ModRegistry.OVERRIDES_BED_RULE_DIMENSION_TYPE_TAG).add(BuiltinDimensionTypes.OVERWORLD);
    }
}
