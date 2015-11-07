package br.com.killaliens.bonus;

import br.com.killaliens.ship.Ship;
import br.com.killaliens.util.random.StaticRandom;

public class UpLife extends Bonus {

    private static final int MIN_HEAL_VALUE = 1;
    private static final int MAX_HEAL_VALUE = 10;
    
    private static final String[] ANIMATION_FRAME_NAMES = {"uplife_icon"};
    private static final float ANIMATION_FRAME_TIME = 0.2f;
    
    /**
     * Constructor
     * @param positionX
     * @param positionY
     */
    public UpLife(float positionX, float positionY) {
        super(positionX, positionY);
    }

    @Override
    protected void addBonusToShip(Ship ship) {
        ship.getHeal(StaticRandom.getRandomValue(MIN_HEAL_VALUE, MAX_HEAL_VALUE));
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
