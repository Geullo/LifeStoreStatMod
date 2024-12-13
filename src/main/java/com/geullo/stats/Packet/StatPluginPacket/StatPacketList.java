package com.geullo.stats.Packet.StatPluginPacket;

public enum StatPacketList {
    STAT_UPGRADE( "00",0),
    OPEN_STAT_UI("01",1),
    PLAYER_POINT("02",2),
    BAG_OPEN("03",3),
    ENHANCE("04",4),
    GET_ENHANCE_PERCENT("05",5)
    ;
    public String recogCode;
    public int id;
    StatPacketList(String recogCode,int id){
        this.recogCode = recogCode;
        this.id = id;
    }
}
