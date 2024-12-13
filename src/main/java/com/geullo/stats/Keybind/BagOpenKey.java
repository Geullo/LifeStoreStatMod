package com.geullo.stats.Keybind;


import com.geullo.stats.Packet.LifeStorePacket.LifeMessage;
import com.geullo.stats.Packet.StatPluginPacket.StatPacket;
import com.geullo.stats.Packet.StatPluginPacket.StatPacketList;
import com.geullo.stats.Stat;
import com.geullo.stats.StatList;
import com.geullo.stats.UI.ServerMove;
import com.geullo.stats.UI.StatUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerDetector;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class BagOpenKey {
    public boolean KEY_PRESSED = false,KEY_PRESSED2 = false;
    public BagOpenKey(){
    }
    @SubscribeEvent
    public void guiOpen(GuiOpenEvent e) {
        if (e.getGui() instanceof GuiDisconnected) {
            if (!LifeMessage.a) return;
            e.setGui(new ServerMove(new GuiMainMenu(),Minecraft.getMinecraft(),new ServerData(LifeMessage.server,LifeMessage.server,false)));
//            e.setGui(new GuiConnecting(new GuiMainMenu(),Minecraft.getMinecraft(),LifeMessage.server,25510));
            LifeMessage.a = false;
        }
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void  onKeyPress(InputEvent.KeyInputEvent e) throws Exception {
        if (RegisterKeybind.bagOpen.isKeyDown()){
            if (!KEY_PRESSED) { // only one recognition at keyPressed
                KEY_PRESSED = true;
                onOpenBagKeyDown();
            }
        }
        else if (!RegisterKeybind.bagOpen.isKeyDown()){
            if (KEY_PRESSED) {
                KEY_PRESSED = false;
                onOpenBagKeyUp();
            }
        }
        if (RegisterKeybind.statOpen.isKeyDown()){
            if (!KEY_PRESSED2) { // only one recognition at keyPressed
                KEY_PRESSED2 = true;
                StatPacket.sendMessage(StatPacketList.OPEN_STAT_UI.recogCode);
            }
        }else if (!RegisterKeybind.statOpen.isKeyDown()){
            if (KEY_PRESSED2) {
                KEY_PRESSED2 = false;
            }
        }

    }

    public void onOpenBagKeyDown(){
        StatPacket.sendMessage(StatPacketList.BAG_OPEN.recogCode);
    }

    public void onOpenBagKeyUp(){

    }

}
