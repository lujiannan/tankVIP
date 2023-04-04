package com.company;

public class FireStrategyDefault implements FireStrategy {

    @Override
    public void fire(Tank t) {
        int bulletX = t.getX() + Tank.getWIDTH()/2 - Bullet.getWIDTH()/2;
        int bulletY = t.getY() + Tank.getHEIGHT()/2 - Bullet.getHEIGHT()/2;
        t.getTf().bullets.add(new Bullet(bulletX, bulletY, t.getDir(), t.getTf(), t.getGroup()));
    }
}
