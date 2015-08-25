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

    public TextureRegion getCurrentTextureRegion(boolean looping){
        return this.currentAnimation.getKeyFrame(elapsedTime, looping);
    }

    public boolean setCurrentAnimation(AnimationTypes key) {
        if (this.animations.containsKey(key)) {
            this.currentAnimation = this.animations.get(key);
            this.elapsedTime = 0;
            return true;
        }
        return false;
    }

    public void advanceTime(float deltaTime){
        this.elapsedTime += deltaTime;
    }
    
    public void addAnimation(AnimationTypes type, Animation animation){
        this.animations.put(type, animation);
    }
    
    public boolean removeAnimation(AnimationTypes key){
        if (this.animations.containsKey(key)) {
            this.animations.remove(key);
            return true;
        }
        return false;
    }
}