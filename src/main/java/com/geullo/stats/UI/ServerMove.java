package com.geullo.stats.UI;

import com.geullo.stats.Render;
import com.geullo.stats.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class ServerMove extends GuiConnecting {
    public ServerMove(GuiScreen parent, Minecraft mcIn, ServerData serverDataIn) {
        super(parent, mcIn, serverDataIn);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        Render.bindTexture(new ResourceLocation(Reference.MOD_ID,"move.png"));
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(0,0,width,height);
    }

}
