package br.com.killaliens.ammunition;

import br.com.killaliens.bullet.factory.BulletFactory;
import br.com.killaliens.bullet.factory.CreateBulletParameter;

public abstract class Ammunition {

    private static final int MINBULLET = 1;
    private static final int MINLEVEL = 1;
    private static final int MAXLEVEL = 6;
    
    private AmmunitionTotal total = new AmmunitionTotal(MINBULLET, MINBULLET, true);
    private BulletFactory bulletFactory = null;
    private int level = MINLEVEL;
    
    public Ammunition(AmmunitionTypes type, int level) {
        this.bulletFactory = BulletFactory.getFactory(type);
        if (level >= MINLEVEL && level <= MAXLEVEL) {
            this.level = level;
        }
    }
    
    public Ammunition(AmmunitionTypes type) {
        this(type, MINLEVEL);
    }
    
    public Ammunition() {
        this(AmmunitionTypes.NORMALBULLET, MINLEVEL);
    }
    
    /**
     * @return the totalBullets
     */
    public int getTotalBullets() {
        return this.total.getTotalBullets();
    }

    /**
     * @param totalBullets
     *            the totalBullets to set
     */
    public void setTotalBullets(int totalBullets) {
        this.total.setTotalBullets(totalBullets);
    }

    /**
     * @return the currentBullets
     */
    public int getCurrentBullets() {
        return this.total.getCurrentBullets();
    }

    /**
     * @param currentBullets
     *            the currentBullets to set
     */
    public void setCurrentBullets(int currentBullets) {
        this.total.setCurrentBullets(currentBullets);
    }

    /**
     * @return the infinity
     */
    public boolean isInfinity() {
        return this.total.isInfinity();
    }

    /**
     * @param infinity
     *            the infinity to set
     */
    public void setInfinity(boolean infinity) {
        this.total.setInfinity(infinity);
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
    
    public void makeBullets(CreateBulletParameter cParameter){
        
        for (int currentNumBullet = 1; currentNumBullet <= this.level; currentNumBullet++) {
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

}
