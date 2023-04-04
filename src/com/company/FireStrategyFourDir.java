package com.company;

public class FireStrategyFourDir implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bulletX = t.getX() + Tank.getWIDTH()/2 - Bullet.getWIDTH()/2;
        int bulletY = t.getY() + Tank.getHEIGHT()/2 - Bullet.getHEIGHT()/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            t.getTf().bullets.add(new Bullet(bulletX, bulletY, dir, t.getTf(), t.getGroup()));
        }
    }
}
