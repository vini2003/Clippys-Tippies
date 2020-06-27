package com.github.vini2003.tippy.util;

import com.google.gson.annotations.SerializedName;
import net.minecraft.text.LiteralText;

public class Tip {
	transient public static final Tip EMPTY = new Tip("§cNo tips loaded!");

	Tip(String rawText) {
		this.rawText = rawText;
		build();
	}

	@SerializedName("text")
	private String rawText;

	transient private LiteralText text;

	public void build() {
		text = new LiteralText(rawText);
	}

	public LiteralText getText() {
		return text;
	}
}
