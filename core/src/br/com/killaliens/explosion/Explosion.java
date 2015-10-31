package br.com.killaliens.explosion;

import br.com.killaliens.screens.gameplay.states.resume.GamePlayObjects;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;
import br.com.killaliens.util.cache.images.TextureCache;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Explosion extends Actor {

    private static final float TIME_LIMIT_IN_FRAMES = 1f;
    private float elapsedTime = 0f;
    private static final float ANIMATION_FRAME_TIME = 0.2f;

    private static final String[] ANIMATION_NORMAL_FRAME_NAMES = { "explosion01", 
        "explosion02", "explosion03" };

    private AnimationManagement animationData = new AnimationManagement();
    
    private static final float RADIUS = TextureCache.
            getTextureRegion(ANIMATION_NORMAL_FRAME_NAMES[0]).getRegionWidth()/2;

    public Explosion(float centerX, float centerY) {
        this.setX(centerX - RADIUS);
        this.setY(centerY - RADIUS);
        this.setWidth(RADIUS * 2);
        this.setHeight(RADIUS * 2);
        this.setOrigin(this.getWidth()/2, this.getHeight()/2);

        Animation animation = BuildAnimation.build(ANIMATION_FRAME_TIME,
                ANIMATION_NORMAL_FRAME_NAMES);
        this.animationData.addAnimation(AnimationTypes.NORMAL_STATE, animation);
        this.animationData.setCurrentAnimation(AnimationTypes.NORMAL_STATE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.elapsedTime += delta;
        
        this.animationData.advanceTime(delta);

        if (this.elapsedTime >= TIME_LIMIT_IN_FRAMES) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(this.animationData.getCurrentTextureRegion(true),
                this.getX(), this.getY(), this.getOriginX(), this.getOriginY(),
                this.getWidth(), this.getHeight(), this.getScaleX(),
                this.getScaleY(), this.getRotation());
    }
    
    @Override
    public boolean remove() {
        Stage stage = this.getStage();
        if (stage != null && stage instanceof GamePlayResume) {
            ((GamePlayResume) stage).removeObjectFromGroup(GamePlayObjects.EXPLOSIONS, this);
        }
        return super.remove();
    }

}
