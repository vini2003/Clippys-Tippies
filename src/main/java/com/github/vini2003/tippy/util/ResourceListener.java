package com.github.vini2003.tippy.util;

import com.github.vini2003.tippy.registry.ResourceRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ResourceListener implements SimpleSynchronousResourceReloadListener {
	private static final Identifier ID = new Identifier("tippy", "reload_listener");

	@Override
	public void apply(ResourceManager resourceManager) {
		ResourceRegistry.reload();
	}

	@Override
	public Identifier getFabricId() {
		return ID;
	}
}