package com.geullo.stats;

public class Stat {
    private String name;
    private int level;
    private int percent;
    public Stat(String name, int level,int percent){
        this.name = name;
        this.level = level;
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public int setPercent(int percent) {
        this.percent = percent;
        return percent;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
    public String getLevelToStr() {
        switch (StatList.changeEng(name)){
            case 0:
            case 1:
            case 3:
            case 2:
        }
        return level+" ";
    }
    public void removeLevel(){
        removeLevel(1);
    }
    public void removeLevel(int num){
        if (level-num<=0) {
            setLevel(0);
            return;
        }
        setLevel(level-num);
    }
    public void addLevel(){
        addLevel(1);
    }
    public void addLevel(int num) {
        if (level + num >= 3) {
            level = 3;
            setLevel(level);
            return;
        }
        setLevel(level + num);
    }

    public void setLevel(int level) {
        this.level = level;
    }

   public StatList getStat(){
        return StatList.valueOf(name);
   }

   public boolean isMaxLevel() {
        if (StatList.NIGHT_VISION.engNM.equals(name)&&level==1) return true;
        if (level==3) return true;
        return false;
   }

   public int getNecessPoint() {
        switch (level) {
            case 0:
                return StatList.QUICK_HAND.engNM.equals(name)||StatList.SPEED.engNM.equals(name)?
                        10:
                        StatList.NIGHT_VISION.engNM.equals(name)||StatList.ADD_INVENTORY.engNM.equals(name)?
                                30: Integer.MAX_VALUE;
            case 1:
                return StatList.QUICK_HAND.engNM.equals(name)||StatList.SPEED.engNM.equals(name)?
                        30:
                        StatList.ADD_INVENTORY.engNM.equals(name)?
                                50: Integer.MAX_VALUE;
            case 2:
                return StatList.QUICK_HAND.engNM.equals(name)||StatList.SPEED.engNM.equals(name)?
                        50:
                        StatList.NIGHT_VISION.engNM.equals(name)||StatList.ADD_INVENTORY.engNM.equals(name)?
                                100: Integer.MAX_VALUE;
        }
        return Integer.MAX_VALUE;
   }


    @Override
    public String toString() {
        return "Stat{" +
                "name=" + name +
                ", level=" + level +
                '}';
    }
}
