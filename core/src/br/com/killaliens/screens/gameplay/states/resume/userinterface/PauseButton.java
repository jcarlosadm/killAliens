package br.com.killaliens.screens.gameplay.states.resume.userinterface;

import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.util.cache.images.TextureCache;
import br.com.killaliens.util.cache.sounds.SoundCache;
import br.com.killaliens.util.collision.CollisionChecker;
import br.com.killaliens.util.mouse.TouchAndMouseState;
import br.com.killaliens.util.scrollobserver.ScrollObserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * PauseButton
 * Show pause button in inferior left corner of the screen
 */
public class PauseButton extends Actor implements ScrollObserver {

    private static final float MINIMUM_DISTANCE_TO_ACTOR = 100f;
    private static final String PAUSE_ICON_IMAGE = "pause_icon";
    private float accumulatorScrollY = 0;

    private TextureRegion image = TextureCache
            .getTextureRegion(PAUSE_ICON_IMAGE);

    private PlayerShip playerShip = null;

    private Rectangle limits = new Rectangle();
    
    private GamePlayScreen gamePlayScreen = null;
    
    private Sound soundClick = SoundCache.getSound("buttonClick.ogg");

    /**
     * Constructor
     * @param playerShip player ship object
     * @param gamePlayScreen GamePlayScreen object
     */
    public PauseButton(PlayerShip playerShip, GamePlayScreen gamePlayScreen) {
        this.playerShip = playerShip;
        this.gamePlayScreen = gamePlayScreen;

        this.setX(0);
        this.setY(0);
        this.setWidth(this.image.getRegionWidth());
        this.setHeight(this.image.getRegionHeight());
        this.setBounds(this.getX(), this.getY(), this.getWidth(),
                this.getHeight());
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        this.limits.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        this.limits.setY(y);
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        this.limits.setWidth(width);
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        this.limits.setHeight(height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        this.checkDistanceToPlayer();
        this.touchedAction();
    }

    /**
     * behavior if pause button is touched
     */
    private void touchedAction() {
        TouchAndMouseState touch = TouchAndMouseState.getInstance();
        if (touch.isOneClickDown()) {
            float pointX = touch.getPoint().x;
            float pointY = touch.getPoint().y + this.accumulatorScrollY;
            if (CollisionChecker.check(this.limits, pointX, pointY)) {
                this.soundClick.play();
                this.gamePlayScreen.pause();
            }
        }
    }

    /**
     * check the distance to player ship, and change position if is too close
     */
    private void checkDistanceToPlayer() {
        float distance = Vector2.dst(this.getX(), this.getY(),
                this.playerShip.getX(), this.playerShip.getY());

        if (distance <= MINIMUM_DISTANCE_TO_ACTOR) {
            this.changePosition();
        }
    }

    /**
     * change position of the pause button
     */
    private void changePosition() {
        if (this.getX() == 0) {
            this.setX(Gdx.graphics.getWidth() - this.getWidth());
        } else {
            this.setX(0);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(this.image, this.getX(), this.getY());
    }

    @Override
    public void updateScroll(float xDelta, float yDelta) {
        this.setY(this.getY() + yDelta);
        this.accumulatorScrollY += yDelta;
    }

}
