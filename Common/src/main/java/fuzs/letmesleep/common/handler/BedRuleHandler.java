package fuzs.letmesleep.common.handler;

import fuzs.letmesleep.common.LetMeSleep;
import fuzs.letmesleep.common.config.ServerConfig;
import fuzs.letmesleep.common.init.ModRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.dimension.DimensionType;

public final class BedRuleHandler {

    private BedRuleHandler() {
        // NO-OP
    }

    public static void modifyDimensionAttributes(Holder<DimensionType> dimensionType, EnvironmentAttributeMap.Builder attributes) {
        if (!dimensionType.is(ModRegistry.OVERRIDES_BED_RULE_DIMENSION_TYPE_TAG)) {
            return;
        }

        BedRule bedRule = dimensionType.value()
                .attributes()
                .applyModifier(EnvironmentAttributes.BED_RULE, EnvironmentAttributes.BED_RULE.defaultValue());
        attributes.set(EnvironmentAttributes.BED_RULE, modifyNormalBedRule(bedRule));
        BedRule strawBedRule = dimensionType.value()
                .attributes()
                .applyModifier(EnvironmentAttributes.STRAW_BED_RULE,
                        EnvironmentAttributes.STRAW_BED_RULE.defaultValue());
        attributes.set(EnvironmentAttributes.STRAW_BED_RULE, modifyStrawBedRule(strawBedRule));
    }

    public static BedRule modifyNormalBedRule(BedRule baseRule) {
        ServerConfig.BedRules rules = LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.normalBedRules;
        return new BedRule(rules.canSleep,
                rules.canSetSpawn,
                baseRule.destroyOnUse(),
                baseRule.destroyOnLeave(),
                baseRule.errorMessage());
    }

    public static BedRule modifyStrawBedRule(BedRule baseRule) {
        ServerConfig.StrawBedRules rules = LetMeSleep.CONFIG.get(ServerConfig.class).goingToSleep.strawBedRules;
        return new BedRule(rules.canSleep,
                rules.canSetSpawn,
                baseRule.destroyOnUse(),
                rules.destroyOnLeave,
                baseRule.errorMessage());
    }
}
