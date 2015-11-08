package br.com.killaliens.ship.enemy;

import br.com.killaliens.ship.status.management.StatusManagement;

public abstract class EnemyStatus implements StatusManagement {

    private EnemyShip enemyShip = null;
    
    private boolean setupComplete = false;
    
    /**
     * Constructor
     * @param enemyShip
     */
    public EnemyStatus(EnemyShip enemyShip) {
        this.enemyShip = enemyShip;
    }
    
    @Override
    public void setup() {
        if (this.setupComplete == false) {
            this.enemyShip.resetAllStatus();
            this.specificSetup();
            this.setupComplete = true;
        }
    }
    
    /**
     * additional setup
     */
    protected abstract void specificSetup();

    @Override
    public void reset() {
        this.setupComplete = false;
    }
    
    /**
     * @return enemy ship with this status
     */
    protected EnemyShip getShip(){
        return this.enemyShip;
    }
}
