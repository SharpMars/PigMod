package net.sharpmars.pigmod;

import net.fabricmc.api.ModInitializer;

public class PigMod implements ModInitializer {
    public static final String MOD_ID = "pigmod";
    @Override
    public void onInitialize() {
        ItemInit.register();
    }
}
