package com.geullo.stats.Packet.LifeStorePacket;

public enum LifePacketList {
    GET_MONEY( "00"),
    GET_PLAYER_JOB("01"),
    GET_PLAYER_JOB_POINT("02"),
    GET_TOWN_CONTRIBUTE("03"),
    OPEN_BANK_UI("04"),
    SEND_MONEY("05"),
    SEND_POINT("06"),
    OPEN_SHOP_UI("07"),
    SELL_ITEM("08"),
    BUY_ITEM("09"),
    ITEM_ADD("10"),
    OPEN_COIN_CHANGE_UI("11"),
    COIN_CHANGE("12"),
    GET_CUSTOM_NAME("13"),
    GET_CONTRIBUTION("14"),
    ADD_PLAYER_LIST("15"),
    ADD_JOB_POINT("16"),
    SERVER_MOVE("32");
    public String recogCode;
    LifePacketList(String recogCode){
        this.recogCode = recogCode;
    }
}
