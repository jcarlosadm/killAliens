package br.com.killaliens.ship.enemy.factory;

import br.com.killaliens.ammunition.bullet.BulletType;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.types.crazyufo.CrazyUFO;

public class CrazyUFOFactory extends EnemyFactory {

    private static final String[] ANIMATION_NORMAL_FRAME_NAMES = 
        {"alien05"};
    private static final String[] ANIMATION_DEAD_FRAME_NAMES =
        {"alien05", "alien05_blank"};
    private static final float FRAMETIME_NORMAL_ANIMATION = 0.2f;
    private static final float FRAMETIME_DEAD_ANIMATION = 0.05f;
    
    private static final int LIFE_VALUE = 15;
    private static final int SHIELD_VALUE = 0;
    
    private static final float SPEED_X = 0f;
    private static final float SPEED_Y = -2f;    
    private static final float ROTATION = 90f;
    
    private static final BulletType BASIC_AMMUNITION = BulletType.SLOWBULLET;
    
    protected CrazyUFOFactory() {}
    
    @Override
    protected String[] getAnimationNormalFrameNames() {
        return ANIMATION_NORMAL_FRAME_NAMES;
    }

    @Override
    protected String[] getAnimationDeadFrames() {
        return ANIMATION_DEAD_FRAME_NAMES;
    }

    @Override
    protected float getAnimationNormalFrameTime() {
        return FRAMETIME_NORMAL_ANIMATION;
    }

    @Override
    protected float getAnimationDeadFrameTime() {
        return FRAMETIME_DEAD_ANIMATION;
    }

    @Override
    protected float getRotation() {
        return ROTATION;
    }

    @Override
    protected float getSpeedX() {
        return SPEED_X;
    }

    @Override
    protected float getSpeedY() {
        return SPEED_Y;
    }

    @Override
    protected int getLifeValue() {
        return LIFE_VALUE;
    }

    @Override
    protected int getShieldValue() {
        return SHIELD_VALUE;
    }

    @Override
    protected BulletType getBasicAmmunition() {
        return BASIC_AMMUNITION;
    }

    @Override
    protected EnemyShip getShipInstance(ShipProperties shipProperties) {
        return new CrazyUFO(shipProperties);
    }

}
