package br.com.killaliens.explosion;

import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Explosion extends Actor {

    private static final float TIME_LIMIT_IN_FRAMES = 150f;
    private static final float ANIMATION_FRAME_TIME = 10f;
    
    private static final float RADIUS = 20f;

    // TODO define frames
    private static final String[] ANIMATION_NORMAL_FRAME_NAMES = { "" };

    private AnimationManagement animationData = new AnimationManagement();

    // sound with low volume

    private float elapsedTime = 0f;

    public Explosion(float centerX, float centerY) {
        // TODO Auto-generated constructor stub
        this.setX(centerX - RADIUS);
        this.setY(centerY - RADIUS);

        Animation animation = BuildAnimation.build(ANIMATION_FRAME_TIME,
                ANIMATION_NORMAL_FRAME_NAMES);
        this.animationData.addAnimation(AnimationTypes.NORMAL_STATE, animation);

        // load sound
    }

    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
        this.elapsedTime += delta;
        this.animationData.advanceTime(delta);

        if (this.elapsedTime >= TIME_LIMIT_IN_FRAMES) {
            this.remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        super.draw(batch, parentAlpha);

        batch.draw(this.animationData.getCurrentTextureRegion(true),
                this.getX(), this.getY(), this.getOriginX(), this.getOriginY(),
                this.getWidth(), this.getHeight(), this.getScaleX(),
                this.getScaleY(), this.getRotation());
    }

}
