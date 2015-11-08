package br.com.killaliens.ship.enemy;

import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.killaliens.bonus.Bonus;
import br.com.killaliens.bonus.BonusType;
import br.com.killaliens.bonus.factory.BonusFactory;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayObjects;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.enemy.status.EnemyDeadStatus;
import br.com.killaliens.ship.enemy.status.EnemyNormalStatus;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.camera.CheckVisibleOnCamera;
import br.com.killaliens.util.random.StaticRandom;

public abstract class EnemyShip extends Ship {

    private CheckVisibleOnCamera checkVisibleOnCamera = new CheckVisibleOnCamera(
            this);

    /**
     * Constructor
     * @param properties
     */
    public EnemyShip(ShipProperties properties) {
        super(properties);
        this.setIfIsEnemy(true);

        this.addStatus(AnimationTypes.NORMAL_STATE, new EnemyNormalStatus(this));
        this.addStatus(AnimationTypes.DEAD, new EnemyDeadStatus(this));
        this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (!this.checkVisibleOnCamera.actorIsVisible()) {
            this.remove();
        }

        if (!this.isDead()) {
            this.setCurrentStatus(AnimationTypes.NORMAL_STATE);
        } else {
            this.setCurrentStatus(AnimationTypes.DEAD);
        }

        this.getCurrentStatus().setup();
        this.getCurrentStatus().act(delta);
    }

    @Override
    public boolean remove() {
        Stage stage = this.getStage();

        if (stage != null && stage instanceof GamePlayResume) {
            this.createBonus((GamePlayResume) stage);
            ((GamePlayResume) this.getStage()).removeObjectFromGroup(
                    GamePlayObjects.ENEMY_SHIPS, this);
        }

        return super.remove();
    }

    /**
     * Generate bonus (if is dead)
     * @param gamePlayResume GamePlayResume object
     */
    private void createBonus(GamePlayResume gamePlayResume) {
        int randomNumber = StaticRandom.getRandomValue(1, 10);

        Bonus bonus = null;
        BonusType type = null;

        if (randomNumber <= 2) {
            return;
        } else if (randomNumber <= 6) {
            type = this.getLowBonusType();
        } else if (randomNumber <= 9) {
            type = this.getMiddleBonusType();
        } else {
            type = this.getHighBonusType();
        }

        bonus = BonusFactory.getBonus(type, this.getX(), this.getY());
        if (bonus == null) {
            return;
        }

        gamePlayResume.addObjectToGroup(GamePlayObjects.BONUS_LIST, bonus);
    }

    /**
     * @return low bonus type
     */
    protected abstract BonusType getLowBonusType();

    /**
     * @return middle bonus type
     */
    protected abstract BonusType getMiddleBonusType();

    /**
     * @return high bonus type
     */
    protected abstract BonusType getHighBonusType();

    /**
     * default AI of this enemy ship
     * @param delta time in seconds since the last frame
     */
    public abstract void runArtificialIntelligence(float delta);
}
