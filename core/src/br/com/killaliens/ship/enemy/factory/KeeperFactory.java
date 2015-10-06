package br.com.killaliens.ship.enemy.factory;

import br.com.killaliens.ammunition.bullet.BulletType;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.types.keeper.Keeper;

public class KeeperFactory extends EnemyFactory{

    private static final String[] ANIMATION_NORMAL_FRAME_NAMES = {"alien04"};
    private static final String[] ANIMATION_DEAD_FRAME_NAMES = {"alien04", "alien04_blank"};
    private static final float ANIMATION_NORMAL_FRAMETIME = 0.2f;
    private static final float ANIMATION_DEAD_FRAMETIME = 0.05f;
    
    private static final float SPEED_X = 0f;
    private static final float SPEED_Y = 0f;
    private static final float ROTATION = 180f;
    
    private static final int LIFE_VALUE = 5;
    private static final int SHIELD_VALUE = 0;
    private static final BulletType BASIC_BULLET = BulletType.FLOWERBULLET;
    
    @Override
    protected EnemyShip getShipInstance(ShipProperties shipProperties) {
        return new Keeper(shipProperties);
    }

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
        return ANIMATION_NORMAL_FRAMETIME;
    }

    @Override
    protected float getAnimationDeadFrameTime() {
        return ANIMATION_DEAD_FRAMETIME;
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
        return BASIC_BULLET;
    }
}
