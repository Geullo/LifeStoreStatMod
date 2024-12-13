package com.geullo.stats.Packet.StatPluginPacket;

import com.geullo.stats.Stat;
import com.geullo.stats.StatList;
import com.geullo.stats.UI.StatUI;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class StatMessage {

	private static StatMessage instance;
	private Minecraft mc = Minecraft.getMinecraft();

	public static StatMessage getInstance() {
		if (instance == null) {
			instance = new StatMessage();
		}
		return instance;
	}

	private StatMessage() {
	}

	/*
	 * {Stat=name:싼손,point:2},{Stat=name:축지법,point:3},{Stat=name:밤의 등불,point:1},{Stat=name:추가 인벤,point:0}
	 * {name:0,point:2},{name:1,point:3},{name:2,point:1},{name:3,point:0}
	 * */
	public void handle(StatPacket message) {
		String code = message.data.substring(0, 2);
		if (code.equals(StatPacketList.OPEN_STAT_UI.recogCode))
		{
			try {
				List<Stat> stats = new ArrayList<>();
				String[] recog = message.data.split("/");

				stats.add(new Stat(StatList.QUICK_HAND.engNM, Integer.parseInt(recog[2]),Integer.parseInt(recog[6])));
				stats.add(new Stat(StatList.SPEED.engNM, Integer.parseInt(recog[3]),Integer.parseInt(recog[7])));
				stats.add(new Stat(StatList.NIGHT_VISION.engNM, Integer.parseInt(recog[4]),Integer.parseInt(recog[8])));
				stats.add(new Stat(StatList.ADD_INVENTORY.engNM, Integer.parseInt(recog[5]),Integer.parseInt(recog[9])));

				StatUI statUI = new StatUI(stats);
				if (statUI.playerPoint!= Integer.parseInt(recog[1])) statUI.playerPoint = Integer.parseInt(recog[1]);
				mc.displayGuiScreen(statUI);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (code.equals(StatPacketList.STAT_UPGRADE.recogCode))
		{
			if (mc.currentScreen instanceof StatUI) {
				StatUI ui = (StatUI) mc.currentScreen;
				String[] recog = message.data.split("/");
				String[] statSplit = recog[1].split("=");
				int idx = Integer.parseInt(statSplit[1]);
				if (ui.playerPoint != Integer.parseInt(statSplit[2])) ui.playerPoint = Integer.parseInt(statSplit[2]);
				Stat stat = ui.stats.get(StatList.change(statSplit[0]));
				stat.setLevel(idx);
				stat.setPercent(Integer.parseInt(statSplit[3]));
			}
		}
		else if (code.equals(StatPacketList.ENHANCE.recogCode))
		{
			System.out.println(message.data);
			if (mc.currentScreen instanceof StatUI) {
				String[] id = message.data.split("/");
				StatUI statUI = (StatUI) mc.currentScreen;
				statUI.success = "1".equals(id[1]);
				if (!statUI.success)
					statUI.stats.stream().filter(a->a.getStat().recogCode.equals(id[2])).forEach(a->a.setPercent(Integer.parseInt(id[3])));
				statUI.alert = true;
				statUI.btnClicked = false;
			}
		}
	}
	private String convertStat(Integer idx){
		return  idx==0?StatList.QUICK_HAND.engNM:
				idx ==1?StatList.SPEED.engNM :
				idx ==1?StatList.NIGHT_VISION.engNM :
				idx ==1?StatList.ADD_INVENTORY.engNM : null;
	}
}
