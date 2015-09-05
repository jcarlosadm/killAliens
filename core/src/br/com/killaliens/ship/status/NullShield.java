package br.com.killaliens.ship.status;

public class NullShield extends Shield{

    private static Shield nullShieldInstance = null;
    
    private NullShield(int shieldLevel) {
        super(shieldLevel);
    }
    
    public static synchronized Shield getNullShieldInstance(){
        if (nullShieldInstance == null) {
            nullShieldInstance = new NullShield(0);
        }
        
        return nullShieldInstance;
    }

}
