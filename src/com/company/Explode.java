package com.company;

import java.awt.*;

public class Explode {

    private static final int WIDTH = ResourceMgr.explodes[0].getWidth(), HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;
    private TankFrame tf = null;

    private boolean live = true;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }


    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            step = 0;
            tf.explodes.remove(this);
        }
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
