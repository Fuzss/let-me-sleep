package fuzs.letmesleep.common.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import fuzs.letmesleep.common.init.ModRegistry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @WrapWithCondition(method = "hurt",
                       at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;stopSleeping()V"))
    public boolean hurt(LivingEntity livingEntity, DamageSource source, float damage) {
        return !source.is(ModRegistry.NO_SLEEPING_INTERRUPTION_DAMAGE_TYPE_TAG);
    }
}
