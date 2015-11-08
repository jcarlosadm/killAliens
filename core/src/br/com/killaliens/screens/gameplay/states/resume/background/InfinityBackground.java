package br.com.killaliens.screens.gameplay.states.resume.background;

import br.com.killaliens.util.cache.images.TextureCache;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Background
 * Build a infinity scrolling background
 */
public class InfinityBackground extends Actor {

    private static final String BACKGROUND_IMAGE_NAME = "background";
    
    private InfinityBackgroundImage backImage1 = null;
    private InfinityBackgroundImage backImage2 = null;

    /**
     * Constructor
     */
    public InfinityBackground() {
        TextureRegion image = TextureCache.getTextureRegion(BACKGROUND_IMAGE_NAME);
        this.backImage1 = new InfinityBackgroundImage(image, 0, 0);
        this.backImage2 = new InfinityBackgroundImage(image, 0, this.backImage1.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.recalcPositionBackgrounds();
    }

    /**
     * recalculate background images position
     */
    private void recalcPositionBackgrounds() {
        this.backImage1.calcScaledFactors();
        this.backImage2.calcScaledFactors();

        Camera camera = this.getStage().getCamera();

        if (camera.position.y - camera.viewportHeight / 2 >= this.backImage1.getPositionY()
                + this.backImage1.getHeight()) {
            this.backImage1.setPositionY(camera.position.y - camera.viewportHeight / 2
                    + this.backImage2.getHeight());
        }

        if (camera.position.y - camera.viewportHeight / 2 >= this.backImage2.getPositionY()
                + this.backImage2.getHeight()) {
            this.backImage2.setPositionY(camera.position.y - camera.viewportHeight / 2
                    + this.backImage1.getHeight());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        this.backImage1.draw(batch);
        this.backImage2.draw(batch);
    }
}
