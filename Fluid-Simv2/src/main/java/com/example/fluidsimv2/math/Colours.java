package com.example.fluidsimv2.math;

import javafx.scene.paint.Color;

public class Colours {
    public static Color gencolors(float time){
        float r = (float) Math.abs(Math.sin(time));
        float g = (float) Math.abs(Math.sin(time + 0.33 * 2.0f * Math.PI));
        float b = (float) Math.abs(Math.sin(time + 0.66 * 2.0f * Math.PI));
        return Color.color(r,g,b);
    }
}
