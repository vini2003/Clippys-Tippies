package com.github.vini2003.tippy.registry;

import com.github.vini2003.tippy.Tippy;
import com.github.vini2003.tippy.util.ResourceListener;
import com.github.vini2003.tippy.util.Tip;
import com.google.gson.Gson;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.TranslatableText;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Environment(EnvType.CLIENT)
public class ResourceRegistry {
	public static final ResourceListener RESOURCE_LISTENER = new ResourceListener();
	private static Map<Integer, Tip[]> tips = new HashMap<>();
	private static TranslatableText tipText = new TranslatableText("tippy.tip.text");

	ResourceRegistry() {
		// NO-OP
	}

	public static void initialize() {
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(RESOURCE_LISTENER);
	}

	public static Tip get() {
		if (tips.isEmpty()) {
			return Tip.EMPTY;
		}
		Tip[] tipArray = tips.get(new Random().nextInt(tips.size()));
		return tipArray[new Random().nextInt(tipArray.length)];
	}

	public static TranslatableText getText() {
		return tipText;
	}

	public static void register(InputStream inputStream) {
		Tip[] tipArray = new Gson().fromJson(new InputStreamReader(inputStream), Tip[].class);
		for (Tip tip : tipArray) {
			tip.build();
		}
		tips.put(tips.size(), tipArray);
	}

	public static void reload() {
		tips.clear();
		load();
	}

	public static void load() {
		File file = new File("./resources/tippy/tips");

		try {
			if (!file.exists()) {
				if (!file.mkdirs() || !file.createNewFile()) {
					throw new IOException("Could not create file(s): ./resources/tippy/tips");
				}
			}

			try {
				Arrays.asList(Objects.requireNonNull(new File("./resources/tippy/tips").listFiles())).forEach((themeFile) -> {
					try {
						register(new FileInputStream(themeFile));
					} catch (FileNotFoundException impossibleException) {
						impossibleException.printStackTrace();
					}

				});
			} catch (NullPointerException exception) {
				Tippy.logger.log(Level.INFO, "[Tippy] No custom tips found.");
			}
		} catch (IOException exception) {
			Tippy.logger.log(Level.INFO, "[Tippy] No custom tips found.");
		}
	}
}
