package br.com.killaliens.ammunition.bullet.firepower;

import br.com.killaliens.util.random.StaticRandom;

public class FirePower {
    private static final int MIN_LIMIT = 1;
    private static final int MAX_LIMIT = 6;
    private int firePower;

    public FirePower(int firePower) {
        this.firePower = firePower;
    }

    public int getFirePower() {
        return this.firePower;
    }

    public void setFirePower(int firePower) {
        this.firePower = firePower;
    }
    
    public int getDamage(){
        return StaticRandom.getRandomValue(MIN_LIMIT, MAX_LIMIT) + this.firePower;
    }
}