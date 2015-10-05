package br.com.killaliens.ammunition;

import br.com.killaliens.ammunition.bullet.BulletType;
import br.com.killaliens.ammunition.bullet.factory.BulletFactory;
import br.com.killaliens.ammunition.bullet.factory.CreateBulletParameter;

public class Ammunition {

    private static final int MINBULLET = 1;
    private static final int MINLEVEL = 1;
    private static final int MAXLEVEL = 7;
    
    private AmmunitionCount ammoCount = new AmmunitionCount(MINBULLET, MINBULLET, false);
    private BulletFactory bulletFactory = null;
    private int level = MINLEVEL;
    private BulletType type = null;
    
    private float reloadTime = 0;
    private float currentReloadTime = 0;
    
    public Ammunition(BulletType type, int level) {
        this.type = type;
        this.bulletFactory = BulletFactory.getFactory(type);
        if (level >= MINLEVEL && level <= MAXLEVEL) {
            this.level = level;
        }
        
        this.reloadTime = type.getReloadTimeInSeconds();
    }
    
    public Ammunition(BulletType type) {
        this(type, MINLEVEL);
    }
    
    public Ammunition() {
        this(BulletType.NORMALBULLET, MINLEVEL);
    }
    
    /**
     * @return the totalBullets
     */
    public int getTotalBullets() {
        return this.ammoCount.getTotalBullets();
    }

    /**
     * @param totalBullets
     *            the totalBullets to set
     */
    public void setTotalBullets(int totalBullets) {
        this.ammoCount.setTotalBullets(totalBullets);
    }

    /**
     * @return the currentBullets
     */
    public int getCurrentBullets() {
        return this.ammoCount.getCurrentBullets();
    }

    /**
     * @param currentBullets
     *            the currentBullets to set
     */
    public void setCurrentBullets(int currentBullets) {
        this.ammoCount.setCurrentBullets(currentBullets);
    }

    /**
     * @return the infinity
     */
    public boolean isInfinity() {
        return this.ammoCount.isInfinity();
    }

    /**
     * @param infinity
     *            the infinity to set
     */
    public void setInfinity(boolean infinity) {
        this.ammoCount.setInfinity(infinity);
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return this.level;
    }

    public void addLevel() {
        if (this.level < MAXLEVEL) {
            this.level += 1;
        }
    }
    
    public void downLevel() {
        if (this.level > MINLEVEL) {
            this.level -= 1;
        }
    }
    
    public void makeBullets(float deltaTime, CreateBulletParameter cParameter){
        
        this.currentReloadTime += deltaTime;
        if (this.currentReloadTime < this.reloadTime) {
            return;
        }
        this.currentReloadTime = 0f;
        
        for (int currentNumBullet = 1; currentNumBullet <= this.level; 
                currentNumBullet++) {
            if (!this.hasAmmunition()) {
                break;
            }
            
            if (!this.isInfinity()) {
                this.setCurrentBullets(this.getCurrentBullets() - 1);
            }
            
            cParameter.setNumBullet(currentNumBullet);
            this.bulletFactory.createBullet(cParameter);
        }
    }

    public boolean hasAmmunition() {
        if (this.isInfinity() || this.getCurrentBullets() > 0) {
            return true;
        }
        return false;
    }
    
    public BulletType getType(){
        return this.type;
    }

}
