package br.com.killaliens.util.random;

import java.util.Random;

public class StaticRandom {
    
    private static Random instance = null;
    
    private StaticRandom(){}
    
    /**
     * Get random value between two limits
     * @param min
     * @param max
     * @return random value
     */
    public static synchronized int getRandomValue(int min, int max){
        if (instance == null) {
            instance = new Random();
        }
        
        return (instance.nextInt(max - min + 1) + min);
    }    
}
