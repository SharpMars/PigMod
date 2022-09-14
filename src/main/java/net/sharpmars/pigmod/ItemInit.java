package net.sharpmars.pigmod;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sharpmars.pigmod.items.FallBootsItem;
import net.sharpmars.pigmod.items.GliderItem;
import net.sharpmars.pigmod.items.PigBoosterItem;
import net.sharpmars.pigmod.items.SwimmingWheelItem;

public class ItemInit {
    public static final PigBoosterItem PIG_BOOSTER = new PigBoosterItem(new FabricItemSettings().maxCount(1).group(ItemGroup.TRANSPORTATION));
    public static final FallBootsItem FALL_BOOTS = new FallBootsItem(new FabricItemSettings().maxCount(1).group(ItemGroup.TRANSPORTATION));
    public static final GliderItem GLIDER = new GliderItem(new FabricItemSettings().maxCount(1).group(ItemGroup.TRANSPORTATION));
    public static final SwimmingWheelItem SWIMMING_WHEEL = new SwimmingWheelItem(new FabricItemSettings().maxCount(1).group(ItemGroup.TRANSPORTATION));

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(PigMod.MOD_ID, name), item);
    }

    public static void register()
    {
        registerItem("pig_booster", PIG_BOOSTER);
        registerItem("fall_boots", FALL_BOOTS);
        registerItem("glider", GLIDER);
        registerItem("swimming_wheel", SWIMMING_WHEEL);
    }
}
