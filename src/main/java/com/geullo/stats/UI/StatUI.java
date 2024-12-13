package com.geullo.stats.UI;

import com.geullo.stats.Packet.LifeStorePacket.LifePacket;
import com.geullo.stats.Packet.LifeStorePacket.LifePacketList;
import com.geullo.stats.Packet.StatPluginPacket.StatPacket;
import com.geullo.stats.Packet.StatPluginPacket.StatPacketList;
import com.geullo.stats.Render;
import com.geullo.stats.Stat;
import com.geullo.stats.StatList;
import com.geullo.stats.util.Reference;
import com.geullo.stats.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StatUI extends GuiScreen {
    public List<Stat> stats;
    private double[] bgPos = new double[2],bgSize = new double[2],statSlotPosX=new double[4],statSlotPosY=new double[4],statSlotSize=new double[2],
    iconBgPosX = new double[4],iconBgPosY = new double[4],iconBgSize = new double[2], playerPos = new double[2], playerSize = new double[2],statNamePosX = new double[4], statNamePosY = new double[4],
    btnPosX = new double[4],btnPosY = new double[4],btnSize = new double[2],showPointPos=new double[2],statNameSize = new double[2],
    playerPointPos = new double[2],playerPointSize = new double[2],playerNamePos = new double[2],playerNameSize = new double[2],
    infoSize = new double[2],infoPos = new double[2],alertPos = new double[2],alertSize = new double[2]
    ;

    public BufferedImage skinHead = Render.getPlayerSkinHead();
    public int playerPoint = 0;
    public String playerName = "";
    public boolean btnClicked = false,success=false,alert=false;

    private double gap,gap2,alertTime = 0.0,currentTick;
    private boolean mouseGrabbed = true;

    public StatUI(List<Stat> stat) {
        if (stat==null) throw new IllegalArgumentException(getClass().getSimpleName()+" :: Empty Stat List");
        stats = stat;
        LifePacket.sendMessage(LifePacketList.GET_CUSTOM_NAME.recogCode);
    }

    @Override
    public void initGui() {
        initBackground();
        initStatSlot();
        initPlayerPointPos();
    }
    private void initBackground(){
        bgSize[0] =  (width/2.66);
        bgSize[1] =(height/1.35);
        bgPos[0] = (width/2) - (bgSize[0]/2);
        bgPos[1] = (height/2) - (bgSize[1]/2);
    }
    private void initIcon(int idx) {
        iconBgPosX[idx] = statSlotPosX[idx]+(gap*1.05);
        iconBgPosY[idx] = statSlotPosY[idx]+((gap2/1.20)/1.005);
    }
    private void initStatName(int idx){
        statNamePosX[idx] = (iconBgPosX[idx]+iconBgSize[0])+(gap/1.35);
        statNamePosY[idx] = iconBgPosY[idx];
    }
    private void initBtn(int idx) {
        btnPosX[idx] = ((statSlotPosX[idx] + statSlotSize[0]) - (gap/1.35)) - btnSize[0];
        btnPosY[idx] = statSlotPosY[idx]+(statSlotSize[1]/2-(btnSize[1]/2));
    }
    private void initPlayerPointPos(){
        showPointPos[0] = statSlotPosX[0];
        showPointPos[1] = statSlotPosY[0] - (gap2*1.6);
    }
    private void initStatSlot() {
        statSlotSize[0] = (bgSize[0] - (bgSize[0] / 5 / 1.55 ))/1.095;
        statSlotSize[1] = bgSize[1] / 6.34/1.112;
        gap = (bgSize[0] / 5 / 3 / 2);
        gap2 = (bgSize[1] / 4 / 4 / 2);

        statSlotPosX[0] = bgPos[0] + (bgSize[0] / 2) - (statSlotSize[0] / 2);
        statSlotPosY[0] = bgPos[1] + (bgSize[1]/2) - (statSlotSize[1]*1.333);

        iconBgSize[0] = statSlotSize[0] / 3 / 2.95/1.15;
        iconBgSize[1] = (statSlotSize[1] - ((gap2 / 2.23)))/1.35/1.15;

        btnSize[0] = iconBgSize[0]*3.55;
        btnSize[1] = iconBgSize[1]*1.17;

        statNameSize[0] = iconBgSize[0]*1.25;
        statNameSize[1] = iconBgSize[1]*1.15;

        for (int i = 0; i < stats.size(); i++) {
            if (i != 0) {
                statSlotPosX[i] = statSlotPosX[i - 1];
                statSlotPosY[i] = (statSlotPosY[i - 1] + statSlotSize[1]) + gap2/3;
            }
            initIcon(i);
            initStatName(i);
            initBtn(i);
        }
        playerSize[0] = statNameSize[0]*1.66;
        playerSize[1] = statNameSize[1]*1.565;
        playerPos[0] = statSlotPosX[0]+gap*1.1;
        playerPos[1] = (statSlotPosY[0]-gap2*1.42)-playerSize[1];

        playerPointSize[0] = statNameSize[0];
        playerPointSize[1] = statNameSize[1];

        playerPointPos[0] = (playerPos[0]+playerSize[0])+(gap*1.95);
        playerPointPos[1] = (playerPos[1]+playerSize[1])-(playerPointSize[1]/2.6);

        playerNameSize[0] = statNameSize[0]*1.5;
        playerNameSize[1] = statNameSize[1]*1.5;
        playerNamePos[0] = playerPointPos[0];
        playerNamePos[1] = playerPos[1];

        infoSize[0] = bgSize[0]/2.35;
        infoSize[1] = bgSize[1]/3;
        infoPos[0] = (bgPos[0]+bgSize[0])+(gap/2.5);
        infoPos[1] = (bgPos[1]);

        alertSize[0] = statSlotSize[0]*1.15;
        alertSize[1] = statSlotSize[1]*1.72;
        alertPos[0] = bgPos[0]+((bgSize[0]/2)-(alertSize[0]/2));
        alertPos[1] = bgPos[1]+((bgSize[1]/2)-(alertSize[1]/2));
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (alert) {
            currentTick++;
            if (currentTick>=28) {
                alert = false;
                currentTick = 0;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (mouseGrabbed) {
            if (Minecraft.getMinecraft().mouseHelper!=null)
                Minecraft.getMinecraft().mouseHelper.ungrabMouseCursor();
            mouseGrabbed = false;
        }

        Render.bindTexture(new ResourceLocation(Reference.MOD_ID, "stat_background.png"));
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(bgPos[0], bgPos[1], bgSize[0], bgSize[1]);
        for (int i = 0; i < stats.size(); i++) {
            Render.bindTexture(new ResourceLocation(Reference.MOD_ID, "stat_slot.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(statSlotPosX[i], statSlotPosY[i], statSlotSize[0], statSlotSize[1]);
            Render.bindTexture(new ResourceLocation(Reference.MOD_ID, "icon/" + stats.get(i).getName() + ".png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(iconBgPosX[i], iconBgPosY[i], iconBgSize[0], iconBgSize[1]);

            Render.drawString(stats.get(i).getStat().krNm+" "+stats.get(i).getLevelToStr()+"LV", (float) statNamePosX[i], (float) statNamePosY[i], (int) ((int) statNameSize[0]/1.44), (int) (statNameSize[1]/1.44), 0, 0xffffff);
            int ne = stats.get(i).getNecessPoint();
            if (ne<101)
                Render.drawString("필요 점수 : "+stats.get(i).getNecessPoint(), (float) statNamePosX[i], (float) (statNamePosY[i]+(statNameSize[1]/2.05)), (int) (statNameSize[0]/1.44), (int) (statNameSize[1]/1.44), 0, 0xffffff);

            Render.bindTexture(new ResourceLocation(Reference.MOD_ID, "enhance_button.png"));
            if (buttonEnabled(stats.get(i))) {
                Render.setColor(0xffffffff);
                Render.drawTexturedRect(btnPosX[i], btnPosY[i], btnSize[0], btnSize[1]);
                if (Utils.mouseBetweenIcon(mouseX, mouseY, btnPosX[i], btnPosY[i], btnSize[0], btnSize[1])&&!alert) {
                    Render.setColor(0x80000000);
                    Render.drawTexturedRect(btnPosX[i], btnPosY[i], btnSize[0], btnSize[1]);
                }
            }
            else {
                Render.setColor(0xff555555);
                Render.drawTexturedRect(btnPosX[i], btnPosY[i], btnSize[0], btnSize[1]);
            }
        }
        Render.drawString("" + playerPoint, (float) playerPointPos[0], (float) playerPointPos[1], (int) playerPointSize[0], (int) playerPointSize[1], 0, 0xffffff);
        Render.bindTexture(Minecraft.getMinecraft().player.getLocationSkin());
        Render.drawTexturedRect(playerPos[0], playerPos[1],playerSize[0],playerSize[1],0.125,0.125,0.25,0.25);
        Render.drawTexturedRect(playerPos[0], playerPos[1],playerSize[0],playerSize[1],0.625,0.125,0.75,0.25);
        Render.drawString("§l" + playerName, (float) playerNamePos[0], (float) playerNamePos[1], (int) playerNameSize[0], (int) playerNameSize[1], 0, 0x000000);

        Render.bindTexture(new ResourceLocation(Reference.MOD_ID, "info.png"));
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(infoPos[0], infoPos[1], infoSize[0], infoSize[1]);
        for (int i = 0; i < stats.size(); i++) {
            if (!alert && Utils.mouseBetweenIcon(mouseX, mouseY, iconBgPosX[i], iconBgPosY[i], iconBgSize[0], iconBgSize[1]))
                Render.drawTooltip(getStatTooltip(i), mouseX, mouseY);
            if (!alert && Utils.mouseBetweenIcon(mouseX, mouseY, btnPosX[i], btnPosY[i], btnSize[0], btnSize[1]) && !stats.get(i).isMaxLevel())
                Render.drawTooltip(getStatPercent(i), mouseX, mouseY);
        }
        if (alert) {
            if (success)
                Render.bindTexture(new ResourceLocation(Reference.MOD_ID, "enhance_success.png"));
            else
                Render.bindTexture(new ResourceLocation(Reference.MOD_ID, "enhance_fail.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(alertPos[0], alertPos[1], alertSize[0], alertSize[1]);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (alert) return;
        for (int i=0;i<stats.size();i++) {
            if (Utils.mouseBetweenIcon(mouseX, mouseY, btnPosX[i], btnPosY[i], btnSize[0], btnSize[1])) {
                if (stats.get(i).getLevel() >= 3) return;
                if (!buttonEnabled(stats.get(i))) return;
                btnClicked = true;
                LifePacket.sendMessage(LifePacketList.GET_CUSTOM_NAME.recogCode);
                StatPacket.sendMessage(StatPacketList.STAT_UPGRADE.recogCode + stats.get(i).getStat().recogCode);
            }
        }
    }

    private List<String> getStatPercent(int idx) {
        List<String> tooltip = new ArrayList<>();
        Stat stat = stats.get(idx);
        tooltip.add("§a" + (stat.getPercent()+(playerHasPotion("stat_upgrade")?20:0)) + "%§f 확률로 성공 합니다.");
        if (playerHasPotion("stat_upgrade")) tooltip.add("§f현재 §a§l20퍼 확률 증가§f 적용중");
        return tooltip;
    }
    private boolean playerHasPotion(String potionName) {
        return Arrays.asList(Minecraft.getMinecraft().player.getActivePotionEffects().toArray()).toString().contains("effect."+potionName);
    }

    private List<String> getStatTooltip(int idx){
        List<String> tooltip = new ArrayList<>();
        switch (idx) {
            case 0:
                tooltip.add("손이 빨라져 광물,나무 등을 더 빠르게 캘 수 있습니다.");
                break;
            case 1:
                tooltip.add("이전보다 더 빠른 속도로 움직일 수 있습니다.");
                break;
            case 2:
                tooltip.add("밤에 밝게 볼수 있습니다.");
                break;
            case 3:
                tooltip.add("더 많은 물건을 보관할 수 있습니다.");
                break;
        }
        return tooltip;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Keyboard.KEY_ESCAPE == keyCode || Keyboard.KEY_U == keyCode) {
            if (!alert) {
                mc.displayGuiScreen(null);
                stats = null;
            }
            else {
                alert = false;
            }
        }
    }

    public boolean buttonEnabled(Stat stat){
        switch (stat.getLevel()){
            case 0:
                return (stat.getStat().equals(StatList.QUICK_HAND)||stat.getStat().equals(StatList.SPEED)||stat.getStat().equals(StatList.NIGHT_VISION)||stat.getStat().equals(StatList.ADD_INVENTORY));
            case 1:
            case 2:
                return (stat.getStat().equals(StatList.QUICK_HAND)||stat.getStat().equals(StatList.SPEED)||stat.getStat().equals(StatList.ADD_INVENTORY));

        }
        return false;
    }
}
