package com.geullo.stats.Keybind;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
@SideOnly(Side.CLIENT)
public class RegisterKeybind {
    public static final String KeyCategory = "§f스탯";
    public static KeyBinding bagOpen = new KeyBinding("§6가방", Keyboard.KEY_P,KeyCategory);
    public static KeyBinding statOpen = new KeyBinding("§6스탯창", Keyboard.KEY_U,KeyCategory);
}
