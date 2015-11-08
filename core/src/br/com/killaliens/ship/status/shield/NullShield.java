package br.com.killaliens.ship.status.shield;


public class NullShield extends Shield{

    private static Shield nullShieldInstance = null;
    
    /**
     * Constructor 
     */
    private NullShield() {
        super(0);
    }
    
    /**
     * @return Shield instance
     */
    public static synchronized Shield getNullShieldInstance(){
        if (nullShieldInstance == null) {
            nullShieldInstance = new NullShield();
        }
        
        return nullShieldInstance;
    }

}
