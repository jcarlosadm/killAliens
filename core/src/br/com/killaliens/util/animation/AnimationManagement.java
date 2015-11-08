package br.com.killaliens.util.animation;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationManagement {
    private Map<AnimationTypes, Animation> animations = 
            new HashMap<AnimationTypes, Animation>();
    
    private Animation currentAnimation = null;
    
    private float elapsedTime = 0;

    /**
     * @return current texture region image
     */
    public TextureRegion getCurrentTextureRegion(){
        return this.currentAnimation.getKeyFrame(elapsedTime, true);
    }

    /**
     * set current animation type
     * @param key animation type
     * @return true if successful
     */
    public boolean setCurrentAnimation(AnimationTypes key) {
        if (this.animations.containsKey(key)) {
            this.currentAnimation = this.animations.get(key);
            this.elapsedTime = 0;
            return true;
        }
        return false;
    }

    /**
     * Advance time of animation manager
     * @param deltaTime time in seconds since the last frame
     */
    public void advanceTime(float deltaTime){
        this.elapsedTime += deltaTime;
    }
    
    /**
     * add animation
     * @param type type of the animation
     * @param animation animation to add
     */
    public void addAnimation(AnimationTypes type, Animation animation){
        this.animations.put(type, animation);
    }
}