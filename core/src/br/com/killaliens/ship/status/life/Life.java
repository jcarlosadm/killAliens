package br.com.killaliens.ship.status.life;

public class Life {
    private int currentLife = 1;
    private int maxLife = 1;
    
    /**
     * Constructor
     * @param maxLife maximum life value
     */
    public Life(int maxLife) {
        this.maxLife = maxLife;
        this.currentLife = maxLife;
    }

    /**
     * Add amount to life
     * @param amount
     */
    public void upLife(int amount) {
        if (amount < 1) {
            return;
        }
        
        if (this.currentLife + amount <= this.maxLife) {
            this.currentLife += amount;
        } else {
            this.currentLife = this.maxLife;
        }
    }

    /**
     * Remove amount from life
     * @param amount
     */
    public void downLife(int amount) {
        if (amount < 1) {
            return;
        }
        
        if (this.currentLife >= amount) {
            this.currentLife -= amount;
        } else {
            this.currentLife = 0;
        }
    }
    
    /**
     * Check if is dead
     * @return true if is dead
     */
    public boolean isDead(){
        return (this.currentLife <= 0);
    }
    
    /**
     * set maximum life value
     * @param value value to set
     */
    public void setMaxLife(int value){
        if (value > 0) {
            this.maxLife = value;
            if (this.currentLife > this.maxLife) {
                this.currentLife = this.maxLife;
            }
        }
    }
    
    /**
     * @return current life value
     */
    public int getCurrentLife(){
        return this.currentLife;
    }
    
    /**
     * @return maximum life value
     */
    public int getMaxLife(){
        return this.maxLife;
    }
}
