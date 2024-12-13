package com.geullo.stats;

public enum StatList {
    QUICK_HAND("00","잰손","QUICK_HAND",0),
    SPEED("01","축지법","SPEED",1),
    NIGHT_VISION("02","밤의 등불","NIGHT_VISION",2),
    ADD_INVENTORY("03", "넓은 가방","ADD_INVENTORY",3);

    public String recogCode;
    public String krNm,engNM;
    public int id;
    StatList(String code,String krNm,String engNM,int id){
        this.recogCode = code;
        this.krNm = krNm;
        this.engNM = engNM;
    }

    public static int change(String recogCode){
        switch (recogCode){
            case "00":
                return 0;
            case "01":
                return 1;
            case "02":
                return 2;
            case "03":
                return 3;
        }
        return 4;
    }
    public static int changeEng(String engNM){
        switch (engNM){
            case "QUICK_HAND":
                return 0;
            case "SPEED":
                return 1;
            case "NIGHT_VISION":
                return 2;
            case "ADD_INVENTORY":
                return 3;
        }
        return 4;
    }


}
