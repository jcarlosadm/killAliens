package br.com.killaliens.ship.status.shield;

import java.util.Random;

public class Shield {
    private static final int MIN_LIMIT = 1;
    private static final int MAX_LIMIT = 6;
    private int shieldLevel = 0;
    
    public Shield(int shieldLevel) {
        this.setShieldLevel(shieldLevel);
    }

    public int calcProtectionValue(){
        Random randomInstance = new Random();
        return randomInstance.nextInt(MAX_LIMIT - MIN_LIMIT) + MIN_LIMIT + this.shieldLevel;
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
