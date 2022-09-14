package net.sharpmars.pigmod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.event.GameEvent;
import net.sharpmars.pigmod.PigEntityMixinInterface;

public class SwimmingWheelItem extends Item {

    public SwimmingWheelItem(Settings settings) { super(settings); }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity.getClass() == PigEntity.class && hand == Hand.MAIN_HAND)
        {
            if(!((PigEntityMixinInterface) entity).isSwimmingWheelInstalled() && ((PigEntity) entity).canBeSaddled())
            {
                ((PigEntityMixinInterface) entity).setSwimmingWheelInstalled(true);
                entity.world.emitGameEvent(entity, GameEvent.EQUIP, entity.getPos());
                entity.playSound(SoundEvents.BLOCK_FUNGUS_BREAK, 1, 6);
                stack.decrement(1);
                return ActionResult.success(user.world.isClient);
            }
        }

        return ActionResult.PASS;
    }
}
