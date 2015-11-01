package br.com.killaliens.bonus.factory;

import br.com.killaliens.bonus.BigBulletBonus;
import br.com.killaliens.bonus.Bonus;
import br.com.killaliens.bonus.BonusType;
import br.com.killaliens.bonus.FastBulletBonus;
import br.com.killaliens.bonus.UpAttack;
import br.com.killaliens.bonus.UpLife;
import br.com.killaliens.bonus.UpShield;

public class BonusFactory {

    /**
     * Get Bonus instance
     * @param type type of the bonus
     * @param positionX position x of the bonus
     * @param positionY position y of the bonus
     * @return Bonus instance
     * TODO Factory method pattern
     */
    public static Bonus getBonus(BonusType type, float positionX,
            float positionY) {
        
        if (type == null) {
            return null;
        }

        if (type.equals(BonusType.UPLIFE)) {
            return new UpLife(positionX, positionY);
        }
        else if (type.equals(BonusType.UPATTACK)) {
            return new UpAttack(positionX, positionY);
        }
        else if (type.equals(BonusType.UPSHIELD)) {
            return new UpShield(positionX, positionY);
        }
        else if (type.equals(BonusType.FASTBULLET)) {
            return new FastBulletBonus(positionX, positionY);
        }
        else if (type.equals(BonusType.BIGBULLET)) {
            return new BigBulletBonus(positionX, positionY);
        }

        return null;
    }

}
