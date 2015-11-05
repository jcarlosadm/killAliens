package br.com.killaliens.bonus;

import br.com.killaliens.ammunition.Ammunition;
import br.com.killaliens.bullet.BulletType;
import br.com.killaliens.ship.Ship;

public class BigBulletBonus extends Bonus {
    
    private static final int BULLET_TOTAL = 100;
    private static final String[] ANIMATION_FRAME_NAMES = {"bigbullet_icon"};
    private static final float ANIMATION_FRAME_TIME = 0.2f;

    public BigBulletBonus(float positionX, float positionY) {
        super(positionX, positionY);
    }

    @Override
    protected void addBonusToShip(Ship ship) {
        Ammunition ammo = new Ammunition(BulletType.BIGBULLET, ship.getTopAmmunition().getLevel(),
                BULLET_TOTAL);
        ship.addAmmunition(ammo);
    }

    @Override
    protected float getFrameTimeNormalAnimation() {
        return ANIMATION_FRAME_TIME;
    }

    @Override
    protected String[] getFrameNames() {
        return ANIMATION_FRAME_NAMES;
    }
}
