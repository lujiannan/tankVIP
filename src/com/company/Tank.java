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

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
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
        if (!moving) return;
        switch (dir) {
            case LEFT:
                if (x <= 0) return;
                x -= SPEED;
                break;
            case RIGHT:
                if (x >= TankFrame.GAME_WIDTH - WIDTH) return;
                x += SPEED;
                break;
            case UP:
                if (y <= 0) return;
                y -= SPEED;
                break;
            case DOWN:
                if (y >= TankFrame.GAME_HEIGHT - HEIGHT) return;
                y += SPEED;
                break;
        }

        if (random.nextInt(10) > 8) this.fire();
    }

    public void fire() {
        int bulletX = this.x + WIDTH/2 - Bullet.getWIDTH()/2;
        int bulletY = this.y + HEIGHT/2 - Bullet.getHEIGHT()/2;
        tf.bullets.add(new Bullet(bulletX, bulletY, this.dir, tf, group));
    }

    public void die() {
        this.live = false;
    }
}
