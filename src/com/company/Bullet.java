package com.company;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;
    private static final int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();

    private int x, y;
    private Dir dir;
    private TankFrame tf;
    private Rectangle bulletArea = new Rectangle();

    private boolean live = true;
    private Group group = Group.BAD;

    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
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

    public void paint(Graphics g) {
        if (!live) {
            tf.bullets.remove(this);
        }
//        System.out.println("paint");
//        Color c = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(c);
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public Rectangle getBulletArea(){
        bulletArea.setBounds(this.x, this.y, WIDTH, HEIGHT);
        return bulletArea;
    }

    private void move() {
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

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;
    }

    public void collideWith(Tank tank) {
        if (this.group.equals(tank.getGroup())) return;

//        Rectangle rect_b = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect_t = new Rectangle(tank.getX(), tank.getY(), Tank.getWIDTH(), tank.getHEIGHT());

        if (this.getBulletArea().intersects(tank.getTankArea())){
            this.die();
            tank.die();
            int explodeX = tank.getX() + Tank.getWIDTH()/2 - Explode.getWIDTH()/2;
            int explodeY = tank.getY() + Tank.getHEIGHT()/2 - Explode.getHEIGHT()/2;
            tf.explodes.add(new Explode(explodeX, explodeY, tf));
        }
    }

    private void die() {
        live = false;
    }
}
