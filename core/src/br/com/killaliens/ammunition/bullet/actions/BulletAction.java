package br.com.killaliens.ammunition.bullet.actions;

import br.com.killaliens.ammunition.bullet.Bullet;

public interface BulletAction {
    public void act();
    
    public void setBullet(Bullet bullet);
}
