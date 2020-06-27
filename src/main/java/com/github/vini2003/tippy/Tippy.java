package com.github.vini2003.tippy;

import com.github.vini2003.tippy.registry.ResourceRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tippy implements ModInitializer {
	public static final String LOG_ID = "Tippy";
	public static final Identifier MOD_ID = new Identifier(LOG_ID.toLowerCase());
	public static Logger logger = LogManager.getLogger(LOG_ID);

	@Override
	public void onInitialize() {
		ResourceRegistry.initialize();
    }
}
