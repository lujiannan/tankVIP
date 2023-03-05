package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.DOWN, this);
    List<Bullet> bullets = new ArrayList<>();
    Bullet b = new Bullet(200,200, Dir.DOWN, this);
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame() throws HeadlessException {
        // 设置窗口属性
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
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

    // 解决子弹闪烁 - 双缓冲
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).paint(g);
        }
    }

    class MyKeyListener extends KeyAdapter {

        boolean bl = false;     // 左键被按
        boolean br = false;     // 右键被按
        boolean bu = false;     // 上键被按
        boolean bd = false;     // 下键被按

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            myTank.setMoving(true);

            if (bl) myTank.setDir(Dir.LEFT);
            if (br) myTank.setDir(Dir.RIGHT);
            if (bu) myTank.setDir(Dir.UP);
            if (bd) myTank.setDir(Dir.DOWN);

            if (!bl && !br && !bu && !bd) myTank.setMoving(false);
        }
    }
}
