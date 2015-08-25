package br.com.killaliens.ammunition;

import br.com.killaliens.bullet.factory.BulletFactory;

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
    
    public void makeBullets(boolean isEnemy, float originX, float originY){
        int levelCount = this.level;
        float xValue = 0;
        float adjust = 0;
        
        while (levelCount > 0) {
            levelCount -= 1;
            
            adjust = levelCount * 10;
            xValue = originX + (levelCount % 2 == 0 ? adjust : adjust * (-1) );
            this.bulletFactory.createBullet(isEnemy, xValue, originY);
        }
    }

}
