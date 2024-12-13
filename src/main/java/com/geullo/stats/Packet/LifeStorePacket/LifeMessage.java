package com.geullo.stats.Packet.LifeStorePacket;

import com.geullo.stats.UI.StatUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;

public class LifeMessage {
	
	private static LifeMessage instance;
	public static boolean a = false;
	public static String server;
	public static int port;
	private Minecraft mc = Minecraft.getMinecraft();
	public static LifeMessage getInstance() {
		if(instance == null) {
			instance = new LifeMessage();
		}
		return instance;
	}
	private LifeMessage() {
	}
	public void handle(LifePacket message) {
		String code = message.data.substring(0,2);
		System.out.println(message.data);
		if (code.equals(LifePacketList.GET_CUSTOM_NAME.recogCode)){
			if (mc.currentScreen instanceof StatUI){
				String[] splitSlash = message.data.split("/");
				StatUI statUI = (StatUI) mc.currentScreen;
				if (!statUI.playerName.equals(splitSlash[1])) statUI.playerName = splitSlash[1];
			}
		}
		else if (code.equals(LifePacketList.SERVER_MOVE.recogCode)) {
			a = true;
			server = message.data.substring(2);
			port = 25565;
			ServerList serverList = new ServerList(Minecraft.getMinecraft());
			serverList.set(0,new ServerData("인생상회 서버",LifeMessage.server,false));
			serverList.saveServerList();
		}
	}
}
