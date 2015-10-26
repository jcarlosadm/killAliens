package br.com.killaliens.bonus;

import br.com.killaliens.ship.Ship;

public class UpAttack extends Bonus {

    private static final String[] ANIMATION_FRAME_NAMES = {"upattack_icon"};
    private static final float ANIMATION_FRAME_TIME = 0.2f;
    
    public UpAttack(float positionX, float positionY) {
        super(positionX, positionY);
    }

    @Override
    protected void addBonusToShip(Ship ship) {
        ship.getTopAmmunition().addLevel();
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
