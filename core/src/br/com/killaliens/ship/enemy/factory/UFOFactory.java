package br.com.killaliens.ship.enemy.factory;

import br.com.killaliens.ammunition.bullet.types.BulletType;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.types.ufo.UFO;

public class UFOFactory extends EnemyFactory {
    
    private static final String[] ANIMATION_NORMAL_FRAMES = { "alien05" };
    private static final String[] ANIMATION_DEAD_FRAMES = { "alien05", "alien05_blank"};
    private static final float FRAMETIME_NORMAL = 0.2f;
    private static final float FRAMETIME_DEAD = 0.05f;

    private static final float INITIAL_ROTATION = 90f;

    private static final float INITIAL_SPEEDX = 0f;
    private static final float INITIAL_SPEEDY = 0f;

    private static final int INITIAL_MAXLIFE = 15;
    private static final int INITIAL_SHIELD = 0;

    private static final BulletType BASIC_AMMUNITION = BulletType.SLOWBULLET;
    
    protected UFOFactory() {}
    
    @Override
    protected String[] getAnimationNormalFrameNames() {
        return ANIMATION_NORMAL_FRAMES;
    }

    @Override
    protected String[] getAnimationDeadFrames() {
        return ANIMATION_DEAD_FRAMES;
    }

    @Override
    protected float getAnimationNormalFrameTime() {
        return FRAMETIME_NORMAL;
    }

    @Override
    protected float getAnimationDeadFrameTime() {
        return FRAMETIME_DEAD;
    }

    @Override
    protected float getRotation() {
        return INITIAL_ROTATION;
    }

    @Override
    protected float getSpeedX() {
        return INITIAL_SPEEDX;
    }

    @Override
    protected float getSpeedY() {
        return INITIAL_SPEEDY;
    }

    @Override
    protected int getLifeValue() {
        return INITIAL_MAXLIFE;
    }

    @Override
    protected int getShieldValue() {
        return INITIAL_SHIELD;
    }

    @Override
    protected BulletType getBasicAmmunition() {
        return BASIC_AMMUNITION;
    }

    @Override
    protected EnemyShip getShipInstance(ShipProperties shipProperties) {
        return new UFO(shipProperties);
    }

}
