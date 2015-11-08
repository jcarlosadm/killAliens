package br.com.killaliens.ship.status.shield;

import br.com.killaliens.util.random.StaticRandom;

public class Shield {
    private static final int MIN_LIMIT = 1;
    private static final int MAX_LIMIT = 6;
    private int shieldLevel = 0;
    
    /**
     * Constructor
     * @param shieldLevel shield base value
     */
    public Shield(int shieldLevel) {
        this.setShieldLevel(shieldLevel);
    }

    /**
     * Calculate protection value with this formula: [random part]+[protection base]
     * the random part means a value in an interval (determined by constants)
     * @return protection value
     */
    public int calcProtectionValue(){
        return StaticRandom.getRandomValue(MIN_LIMIT, MAX_LIMIT) + this.shieldLevel;
    }

    /**
     * @return the shieldLevel
     */
    public int getShieldLevel() {
        return this.shieldLevel;
    }

    /**
     * @param shieldLevel the shieldLevel to set
     */
    public void setShieldLevel(int shieldLevel) {
        this.shieldLevel = shieldLevel;
    }
}
