package com.example.lenovo.patho;

/**
 * Created by Lenovo on 29-11-2016.
 */

public class PixelData {
    int x;
    int y;
    int color;
    int joinWithX;
    int joinWithY;

    public PixelData(int x, int y, int color, int joinWithX, int joinWithY) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.joinWithX = joinWithX;
        this.joinWithY = joinWithY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getJoinWithX() {
        return joinWithX;
    }

    public void setJoinWithX(int joinWithX) {
        this.joinWithX = joinWithX;
    }

    public int getJoinWithY() {
        return joinWithY;
    }

    public void setJoinWithY(int joinWithY) {
        this.joinWithY = joinWithY;
    }
}
