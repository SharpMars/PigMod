package net.sharpmars.pigmod;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PigEntity;

public interface PigEntityMixinInterface {
    TrackedData<Boolean> BOOSTER_INSTALLED = DataTracker.registerData(PigEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    TrackedData<Boolean> FALL_BOOTS_INSTALLED = DataTracker.registerData(PigEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    TrackedData<Boolean> GLIDER_INSTALLED = DataTracker.registerData(PigEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    TrackedData<Boolean> SWIMMING_WHEEL_INSTALLED = DataTracker.registerData(PigEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    default void setBoosterInstalled(boolean value) {
        ((PigEntity) this).getDataTracker().set(BOOSTER_INSTALLED, value);
    }

    default boolean isBoosterInstalled() {
        return ((PigEntity) this).getDataTracker().get(BOOSTER_INSTALLED);
    }

    default void setFallBootsInstalled(boolean value) {
        ((PigEntity) this).getDataTracker().set(FALL_BOOTS_INSTALLED, value);
    }

    default boolean areFallBootsInstalled() {
        return ((PigEntity) this).getDataTracker().get(FALL_BOOTS_INSTALLED);
    }

    default void setGliderInstalled(boolean value) {
        ((PigEntity) this).getDataTracker().set(GLIDER_INSTALLED, value);
    }

    default boolean isGliderInstalled() {return ((PigEntity) this).getDataTracker().get(GLIDER_INSTALLED);}

    default void setSwimmingWheelInstalled(boolean value) { ((PigEntity) this).getDataTracker().set(SWIMMING_WHEEL_INSTALLED, value); }

    default boolean isSwimmingWheelInstalled() { return ((PigEntity) this).getDataTracker().get(SWIMMING_WHEEL_INSTALLED); }
}
