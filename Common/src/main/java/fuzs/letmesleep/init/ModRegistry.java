package fuzs.letmesleep.init;

import fuzs.letmesleep.LetMeSleep;
import fuzs.letmesleep.world.effect.MobEffectTemplate;
import fuzs.multiloaderdataextensions.common.api.v2.DataMapRegistrar;
import fuzs.multiloaderdataextensions.common.api.v2.DataMapToken;
import fuzs.puzzleslib.common.api.init.v3.tags.TagFactory;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;

public class ModRegistry {
    static final TagFactory TAGS = TagFactory.make(LetMeSleep.MOD_ID);
    public static final TagKey<EntityType<?>> NEVER_PREVENTS_PLAYER_REST_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "never_prevents_player_rest");
    public static final TagKey<DamageType> NO_SLEEPING_INTERRUPTION_DAMAGE_TYPE_TAG = TAGS.registerDamageTypeTag(
            "no_sleeping_interruption");
    public static final TagKey<MobEffect> CLEARED_WHEN_WAKING_UP_MOB_EFFECT_TAG = TAGS.registerTagKey(Registries.MOB_EFFECT,
            "cleared_when_waking_up");

    public static final DataMapToken<MobEffect, MobEffectTemplate> WAKE_UP_EFFECTS_DATA_MAP_TYPE = DataMapRegistrar.register(
            LetMeSleep.id("wake_up_effects"),
            Registries.MOB_EFFECT,
            MobEffectTemplate.CODEC);

    public static void bootstrap() {
        // NO-OP
    }
}
