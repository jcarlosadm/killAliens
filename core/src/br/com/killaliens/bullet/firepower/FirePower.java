package br.com.killaliens.bullet.firepower;

import java.util.Random;

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
        Random randomInstance = new Random();
        return randomInstance.nextInt(MAX_LIMIT - MIN_LIMIT) + MIN_LIMIT + this.firePower;
    }
}