package br.com.killaliens.bonus;

import br.com.killaliens.ship.Ship;

public class UpShield extends Bonus {

    private static final String[] ANIMATION_FRAME_NAMES = {"upshield_icon"};
    private static final float ANIMATION_FRAME_TIME = 0.2f;
    
    /**
     * Constructor
     * @param positionX
     * @param positionY
     */
    public UpShield(float positionX, float positionY) {
        super(positionX, positionY);
    }

    @Override
    protected void addBonusToShip(Ship ship) {
        ship.upShieldLevel();
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
