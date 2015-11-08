package br.com.killaliens.ship.status.life;


public class NullLife extends Life {

    private static Life nullLifeInstance = null;

    /**
     * Constructor
     * @param maxLife maximum life value
     */
    private NullLife(int maxLife) {
        super(maxLife);
    }

    /**
     * @return Life instance
     */
    public static synchronized Life getNullLifeInstance() {
        if (nullLifeInstance == null) {
            nullLifeInstance = new NullLife(1);
        }

        return nullLifeInstance;
    }
}
