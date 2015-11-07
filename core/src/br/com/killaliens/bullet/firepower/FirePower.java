package br.com.killaliens.bullet.firepower;

import br.com.killaliens.util.random.StaticRandom;

public class FirePower {
    private static final int MIN_LIMIT = 1;
    private static final int MAX_LIMIT = 6;
    private int firePower;

    /**
     * Constructor
     * @param firePower base value of the fire power
     */
    public FirePower(int firePower) {
        this.firePower = firePower;
    }

    /**
     * get base value of the fire power
     * @return base value of the fire power
     */
    public int getFirePower() {
        return this.firePower;
    }

    /**
     * Set base value of the fire power
     * @param firePower base value of the fire power
     */
    public void setFirePower(int firePower) {
        this.firePower = firePower;
    }
    
    /**
     * Obtain damage value with this formula: [random part]+[base value]
     * the random part means a value in an interval (determined by constants)
     * @return damage value
     */
    public int getDamage(){
        return StaticRandom.getRandomValue(MIN_LIMIT, MAX_LIMIT) + this.firePower;
    }
}