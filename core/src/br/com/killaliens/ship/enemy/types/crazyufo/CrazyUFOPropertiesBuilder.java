package br.com.killaliens.ship.enemy.types.crazyufo;

import br.com.killaliens.ammunition.AmmunitionTypes;
import br.com.killaliens.ship.ShipPropertiesBuilder;

public class CrazyUFOPropertiesBuilder extends ShipPropertiesBuilder {

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
    
    private static final AmmunitionTypes BASIC_AMMUNITION = AmmunitionTypes.SLOWBULLET;
    
    @Override
    protected String[] getAnimationNormalFrameNames() {
        return ANIMATION_NORMAL_FRAME_NAMES;
    }

    @Override
    protected String[] getAnimationDeadFrameNames() {
        return ANIMATION_DEAD_FRAME_NAMES;
    }

    @Override
    protected float getStartPositionX() {
        return 0;
    }

    @Override
    protected float getStartPositionY() {
        return 0;
    }

    @Override
    protected float getStartRotation() {
        return ROTATION;
    }

    @Override
    protected float getStartSpeedX() {
        return SPEED_X;
    }

    @Override
    protected float getStartSpeedY() {
        return SPEED_Y;
    }

    @Override
    protected int getStartLife() {
        return LIFE_VALUE;
    }

    @Override
    protected int getStartShield() {
        return SHIELD_VALUE;
    }

    @Override
    protected AmmunitionTypes getBasicAmmunition() {
        return BASIC_AMMUNITION;
    }

    @Override
    protected float getFrameTimeNormalAnimation() {
        return FRAMETIME_NORMAL_ANIMATION;
    }

    @Override
    protected float getFrameTimeDeadAnimation() {
        return FRAMETIME_DEAD_ANIMATION;
    }

}
