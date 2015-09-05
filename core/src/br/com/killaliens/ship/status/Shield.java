package br.com.killaliens.ship.status;

public class Shield {
    
    private int shieldLevel = 0;
    
    public Shield(int shieldLevel) {
        this.setShieldLevel(shieldLevel);
    }

    public int calcProtectionValue(){
        // TODO implement
        return 0;
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
