package br.com.killaliens.status;

public class Life {
    private int currentLife = 1;
    private int maxLife = 1;
    
    public Life(int maxLife) {
        // TODO Auto-generated constructor stub
        this.maxLife = maxLife;
        this.currentLife = maxLife;
    }
    
    public void modifyCurrentLife(int amount){
        if ( amount > 0 ) {
            this.downLife(amount);
        } else {
            this.upLife(amount);
        }
    }

    private void upLife(int amount) {
        // TODO Auto-generated method stub
        if (this.currentLife + amount <= this.maxLife) {
            this.currentLife += amount;
        } else {
            this.currentLife = this.maxLife;
        }
    }

    private void downLife(int amount) {
        // TODO Auto-generated method stub
        if (this.currentLife >= amount) {
            this.currentLife -= amount;
        } else {
            this.currentLife = 0;
        }
    }
    
    public boolean isDead(){
        return (this.currentLife <= 0);
    }
    
    public void setMaxLife(int value){
        if (value > 0) {
            this.maxLife = value;
            if (this.currentLife > this.maxLife) {
                this.currentLife = this.maxLife;
            }
        }
    }
    
    public int getCurrentLife(){
        return this.currentLife;
    }
    
    public int getMaxLife(){
        return this.maxLife;
    }
}
