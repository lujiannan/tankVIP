package com.company;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++){
            tf.tanks.add(new Tank(50 + i * 80, 120, Dir.DOWN, tf, Group.BAD));
        }

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}