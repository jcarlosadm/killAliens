package br.com.killaliens.ship.enemy.types.ufo;

import br.com.killaliens.ammunition.AmmunitionTypes;
import br.com.killaliens.ship.ShipPropertiesBuilder;

import com.badlogic.gdx.Gdx;

public class UFOPropertiesBuilder extends ShipPropertiesBuilder {

    private static final String[] ANIMATION_NORMAL_FRAMES = { "alien05" };
    private static final String[] ANIMATION_DEAD_FRAMES = { "alien05", "alien05_blank"};
    private static final float FRAMETIME_NORMAL = 0.2f;
    private static final float FRAMETIME_DEAD = 0.05f;
    
    private static final float INITIAL_POSITION_X = Gdx.graphics.getWidth()/2;
    private static final float INITIAL_POSITION_Y = Gdx.graphics.getHeight() - 50f;

    private static final float INITIAL_ROTATION = 180f;

    private static final float INITIAL_SPEEDX = 0f;
    private static final float INITIAL_SPEEDY = 0f;

    private static final int INITIAL_MAXLIFE = 15;
    private static final int INITIAL_SHIELD = 0;

    private static final AmmunitionTypes BASIC_AMMUNITION = AmmunitionTypes.NORMALBULLET;
    
    @Override
    protected String[] getAnimationNormalFrameNames() {
        return ANIMATION_NORMAL_FRAMES;
    }

    @Override
    protected String[] getAnimationDeadFrameNames() {
        return ANIMATION_DEAD_FRAMES;
    }

    @Override
    protected float getStartPositionX() {
        return INITIAL_POSITION_X;
    }

    @Override
    protected float getStartPositionY() {
        return INITIAL_POSITION_Y;
    }

    @Override
    protected float getStartRotation() {
        return INITIAL_ROTATION;
    }

    @Override
    protected float getStartSpeedX() {
        return INITIAL_SPEEDX;
    }

    @Override
    protected float getStartSpeedY() {
        return INITIAL_SPEEDY;
    }

    @Override
    protected int getStartLife() {
        return INITIAL_MAXLIFE;
    }

    @Override
    protected int getStartShield() {
        return INITIAL_SHIELD;
    }

    @Override
    protected AmmunitionTypes getBasicAmmunition() {
        return BASIC_AMMUNITION;
    }

    @Override
    protected float getFrameTimeNormalAnimation() {
        return FRAMETIME_NORMAL;
    }

    @Override
    protected float getFrameTimeDeadAnimation() {
        return FRAMETIME_DEAD;
    }
}
