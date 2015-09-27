package br.com.killaliens.screens.gamescreen.background;

import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {

    private static final String BACKGROUND_IMAGE_NAME = "background";
    
    private BackgroundImage backImage1 = null;
    private BackgroundImage backImage2 = null;

    public Background() {
        TextureRegion image = TextureCache.getTextureRegion(BACKGROUND_IMAGE_NAME);
        this.backImage1 = new BackgroundImage(image, 0, 0);
        this.backImage2 = new BackgroundImage(image, 0, this.backImage1.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.recalcPositionBackgrounds();
    }

    private void recalcPositionBackgrounds() {
        this.backImage1.recalcScaledFactors();
        this.backImage2.recalcScaledFactors();

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
