package com.company;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
//        Frame f =  new Frame();
//
//        // 设置窗口属性
//        f.setSize(800,600);
//        f.setResizable(false);
//        f.setTitle("Tank War");
//        f.setVisible(true);
//
//        // 添加 点击×时，关闭窗口
//        f.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                System.exit(0);
//            }
//        });
        Frame f = new TankFrame();
    }
}