package net.sharpmars.pigmod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.sharpmars.pigmod.ItemInit;
import net.sharpmars.pigmod.PigEntityMixinInterface;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends AnimalEntity implements PigEntityMixinInterface {

    @Shadow @Nullable public abstract Entity getPrimaryPassenger();

    protected PigEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getSaddledSpeed", at = @At("HEAD"), cancellable = true)
    public void getSaddledSpeed(CallbackInfoReturnable<Float> cir) {
        if((this.getDataTracker().get(BOOSTER_INSTALLED))) {
            float result = (float) this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) * 2.5F;
            cir.setReturnValue(result);
        }
    }

    @Inject(method = "initDataTracker", at = @At("HEAD"))
    protected void initDataTracker(CallbackInfo ci)
    {
        this.getDataTracker().startTracking(BOOSTER_INSTALLED, false);
        this.getDataTracker().startTracking(FALL_BOOTS_INSTALLED, false);
        this.getDataTracker().startTracking(GLIDER_INSTALLED, false);
        this.getDataTracker().startTracking(SWIMMING_WHEEL_INSTALLED, false);
    }

    @Inject(method = "dropInventory", at = @At("TAIL"))
    protected void dropInventory(CallbackInfo ci) {
        if (this.isBoosterInstalled()) {
            this.dropItem(ItemInit.PIG_BOOSTER);
        }
        if (this.areFallBootsInstalled()) {
            this.dropItem(ItemInit.FALL_BOOTS);
        }
        if (this.isGliderInstalled()) {
            this.dropItem(ItemInit.GLIDER);
        }
        if (this.isSwimmingWheelInstalled()) {
            this.dropItem(ItemInit.SWIMMING_WHEEL);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    protected void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        setBoosterInstalled(nbt.getBoolean("isBoosterInstalled"));
        setFallBootsInstalled(nbt.getBoolean("areFallBootsInstalled"));
        setGliderInstalled(nbt.getBoolean("isGliderInstalled"));
        setSwimmingWheelInstalled(nbt.getBoolean("isSwimmingWheelInstalled"));
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    protected void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("isBoosterInstalled", isBoosterInstalled());
        nbt.putBoolean("areFallBootsInstalled", areFallBootsInstalled());
        nbt.putBoolean("isGliderInstalled", isGliderInstalled());
        nbt.putBoolean("isSwimmingWheelInstalled", isSwimmingWheelInstalled());
    }

    @Inject(method = "consumeOnAStickItem", at = @At("HEAD"), cancellable = true)
    protected void consumeOnAStickItem(CallbackInfoReturnable<Boolean> cir) {
        if(isBoosterInstalled()) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        if(areFallBootsInstalled()){
            return false;
        }
        return super.handleFallDamage(fallDistance, damageMultiplier, damageSource);
    }

    @Override
    public void tick() {
        if(isGliderInstalled()) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 4, 50, false, true ));
        }

        if(isSwimmingWheelInstalled() && isSubmergedInWater()) {
            this.jump();
        }

        super.tick();
    }
}