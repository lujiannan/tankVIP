package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 200, y = 200;

    public TankFrame() throws HeadlessException {
        // 设置窗口属性
        this.setSize(800,600);
        this.setResizable(false);
        this.setTitle("Tank War");
        this.setVisible(true);

        // 添加 点击×时，关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        // 键盘监听 - 刷新界面
        this.addKeyListener(new MyKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("paint");
        g.fillRect(x, y, 50, 50);

        // 让方块动起来
        x += 10;
        y += 10;
    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            System.out.println("key pressed");
        }
 
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            System.out.println("key released");
        }
    }
}
