package com.geullo.stats.util;

public class Utils {
    public static boolean mouseBetweenIcon(double mouseX,double mouseY,double x, double y, double width, double height){
        return (mouseX >= x&&mouseY >= y&&mouseX <= x + width&&mouseY <= y + height);
    }
}
