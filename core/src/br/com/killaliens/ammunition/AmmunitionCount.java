package br.com.killaliens.ammunition;

public class AmmunitionCount {
    private int totalBullets = 1;
    private int currentBullets = 1;
    private boolean infinity = false;

    public AmmunitionCount(int totalBullets, int currentBullets,
            boolean infinity) {
        this.totalBullets = totalBullets;
        this.currentBullets = currentBullets;
        this.infinity = infinity;
    }

    /**
     * @return the totalBullets
     */
    public int getTotalBullets() {
        return this.totalBullets;
    }

    /**
     * @param totalBullets the totalBullets to set
     */
    public void setTotalBullets(int totalBullets) {
        this.totalBullets = totalBullets;
        if (this.currentBullets > this.totalBullets) {
            this.currentBullets = this.totalBullets;
        }
    }

    /**
     * @return the currentBullets
     */
    public int getCurrentBullets() {
        return this.currentBullets;
    }

    /**
     * @param currentBullets the currentBullets to set
     */
    public void setCurrentBullets(int currentBullets) {
        this.currentBullets = currentBullets;
        
        if (this.currentBullets > this.totalBullets) {
            this.currentBullets = this.totalBullets;
        } else if (this.currentBullets < 0) {
            this.currentBullets = 0;
        }
    }

    /**
     * @return the infinity
     */
    public boolean isInfinity() {
        return this.infinity;
    }

    /**
     * @param infinity the infinity to set
     */
    public void setInfinity(boolean infinity) {
        this.infinity = infinity;
    }
}