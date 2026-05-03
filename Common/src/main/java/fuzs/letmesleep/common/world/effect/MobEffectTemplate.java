package fuzs.letmesleep.common.world.effect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.SharedConstants;
import net.minecraft.core.Holder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

public record MobEffectTemplate(int durationInSeconds, int amplifier, boolean ambient) {
    private static final int DEFAULT_DURATION = 30;
    public static final Codec<MobEffectTemplate> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                    ExtraCodecs.POSITIVE_INT.optionalFieldOf("duration_in_seconds", DEFAULT_DURATION)
                            .forGetter(MobEffectTemplate::durationInSeconds),
                    ExtraCodecs.UNSIGNED_BYTE.optionalFieldOf("amplifier", MobEffectInstance.MIN_AMPLIFIER)
                            .forGetter(MobEffectTemplate::amplifier),
                    Codec.BOOL.optionalFieldOf("ambient", false).forGetter(MobEffectTemplate::ambient))
            .apply(instance, MobEffectTemplate::new));

    public MobEffectTemplate() {
        this(DEFAULT_DURATION);
    }

    public MobEffectTemplate(int durationInSeconds) {
        this(durationInSeconds, MobEffectInstance.MIN_AMPLIFIER);
    }

    public MobEffectTemplate(int durationInSeconds, int amplifier) {
        this(durationInSeconds, amplifier, false);
    }

    public MobEffectInstance createInstance(Holder<MobEffect> holder) {
        return new MobEffectInstance(holder, this.durationInTicks(), this.amplifier, this.ambient, true);
    }

    public int durationInTicks() {
        return this.durationInSeconds * SharedConstants.TICKS_PER_SECOND;
    }
}
