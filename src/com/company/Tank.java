package com.company;

import java.awt.*;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private static final int WIDTH = 50, HEIGHT = 50;

    private boolean moving = false;

    private TankFrame tf;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
//        System.out.println("paint");
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
    }

    public void fire() {
        tf.bullets.add(new Bullet(this.x + WIDTH/2 - Bullet.getWIDTH()/2, this.y + HEIGHT/2 - Bullet.getHEIGHT()/2, this.dir, tf));
    }
}
