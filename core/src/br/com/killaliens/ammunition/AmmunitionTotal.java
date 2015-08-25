package br.com.killaliens.ammunition;

public class AmmunitionTotal {
    private int totalBullets;
    private int currentBullets;
    private boolean infinity;

    public AmmunitionTotal(int totalBullets, int currentBullets,
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