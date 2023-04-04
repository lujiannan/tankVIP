package com.company;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private static final int WIDTH = ResourceMgr.tankD.getWidth(), HEIGHT = ResourceMgr.tankD.getHeight();
    private Rectangle tankArea = new Rectangle();

    private Random random = new Random();

//    private boolean moving = true;
    private boolean moving = false;
    private boolean live = true;
    private Group group = Group.BAD;

    private TankFrame tf;
    private FireStrategy fs;

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        if (this.group == Group.GOOD) {
            try {
                fs = (FireStrategy) Class.forName(PropertyMgr.get("goodFS")).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fs = (FireStrategy) Class.forName(PropertyMgr.get("badFS")).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public TankFrame getTf() {
        return tf;
    }

    public boolean isLive() {
        return live;
    }

    public Rectangle getTankArea(){
        tankArea.setBounds(this.x, this.y, WIDTH, HEIGHT);
        return tankArea;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
//        System.out.println("paint");
        if (!live) {
            tf.tanks.remove(this);
        }
        if (group == Group.BAD) {
            moving = true;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (random.nextInt(100) > 93 && this.group == Group.BAD) randomDir();

        if (!moving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (random.nextInt(100) > 95 && this.group == Group.BAD
                && x >= 0 && y >= 40 && x <= TankFrame.GAME_WIDTH - WIDTH && y <= TankFrame.GAME_HEIGHT - HEIGHT) {
            this.fire();
        }

        boundsCheck();
    }

    private void boundsCheck() {
        if (this.x < 0) x = 0;
        if (this.y < 40) y = 40;
        if (this.x > TankFrame.GAME_WIDTH - WIDTH) x = TankFrame.GAME_WIDTH - WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - HEIGHT) y = TankFrame.GAME_HEIGHT - HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public void die() {
        this.live = false;
    }
}
