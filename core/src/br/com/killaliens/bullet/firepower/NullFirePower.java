package br.com.killaliens.bullet.firepower;

public class NullFirePower extends FirePower {

    private static FirePower firepowerInstance = null;
    
    private NullFirePower(int firePower) {
        super(firePower);
    }
    
    /**
     * Get NullFirePower instance
     * @return NullFirePower instance
     */
    public static synchronized FirePower getNullFirePowerInstance(){
        if (firepowerInstance == null) {
            firepowerInstance = new NullFirePower(0);
        }
        
        return firepowerInstance;
    }

}
