package br.com.killaliens.ammunition;

import br.com.killaliens.bullet.BulletType;
import br.com.killaliens.bullet.factory.BulletFactory;
import br.com.killaliens.bullet.factory.CreateBulletParameter;

/**
 * Ammunition
 * class of bullet generator
 */
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

    /**
     * Constructor
     * @param type type of the bullet
     * @param level level of the ammunition
     * @param totalBullets total of bullets
     * @param infinity if is bullet total is infinity
     */
    private Ammunition(BulletType type, int level, int totalBullets, boolean infinity){
        this.type = type;
        this.bulletFactory = BulletFactory.getFactory(type);
        if (level >= MINLEVEL && level <= MAXLEVEL) {
            this.level = level;
        }
        
        this.reloadTime = type.getReloadTimeInSeconds();
        
        this.ammoCount.setInfinity(infinity);
        if (totalBullets > 0 && infinity == false) {
            this.ammoCount.setTotalBullets(totalBullets);
            this.ammoCount.setCurrentBullets(totalBullets);
        }
    }
    
    /**
     * Get infinity ammunition
     * @param type type of the bullet
     * @param level level of the ammunition
     * @return Ammunition instance
     */
    public static Ammunition getInfinityAmmunition(BulletType type, int level){
        return new Ammunition(type, level, MINBULLET, true);
    }
    
    /**
     * Get non infinity ammunition
     * @param type type of the bullet
     * @param level level of the ammunition
     * @param total total of bullets
     * @return Ammunition instance
     */
    public static Ammunition getNonInfinityAmmunition(BulletType type, int level,
            int total){
        return new Ammunition(type, level, total, false);
    }
    
    /**
     * @return the totalBullets
     */
    public int getTotalBullets() {
        return this.ammoCount.getTotalBullets();
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
    private void setCurrentBullets(int currentBullets) {
        this.ammoCount.setCurrentBullets(currentBullets);
    }

    /**
     * @return the infinity
     */
    public boolean isInfinity() {
        return this.ammoCount.isInfinity();
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Add level to ammunition
     */
    public void addLevel() {
        if (this.level < MAXLEVEL) {
            this.level += 1;
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
