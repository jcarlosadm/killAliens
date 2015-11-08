package br.com.killaliens.bonus;

import br.com.killaliens.screens.gameplay.states.resume.GamePlayObjects;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;
import br.com.killaliens.util.cache.images.TextureCache;
import br.com.killaliens.util.camera.CheckVisibleOnCamera;
import br.com.killaliens.util.collision.CollisionChecker;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Bonus extends Actor {
    
    private AnimationManagement animationManagement = new AnimationManagement();
    
    private Rectangle limits = new Rectangle();
    
    private CheckVisibleOnCamera checkVisibleOnCamera  = null; 
    
    /**
     * Constructor
     * @param positionX
     * @param positionY
     * TODO Template method pattern
     */
    public Bonus(float positionX, float positionY) {
        this.setX(positionX);
        this.setY(positionY);
        this.setWidth(TextureCache.getTextureRegion(this.getFrameNames()[0]).getRegionWidth());
        this.setHeight(TextureCache.getTextureRegion(this.getFrameNames()[0]).getRegionHeight());
        this.setOrigin(this.getWidth()/2, this.getHeight()/2);
        
        Animation animation = BuildAnimation.build(this.getFrameTimeNormalAnimation(),
                this.getFrameNames());
        
        this.animationManagement.addAnimation(AnimationTypes.NORMAL_STATE, animation);
        this.animationManagement.setCurrentAnimation(AnimationTypes.NORMAL_STATE);
        
        this.checkVisibleOnCamera = new CheckVisibleOnCamera(this);
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
        
        this.animationManagement.advanceTime(delta);
        if (!this.checkVisibleOnCamera.actorIsVisible()) {
            this.remove();
        }
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        batch.draw(this.animationManagement.getCurrentTextureRegion(), 
                this.getX(), this.getY());
    }
    
    @Override
    public boolean remove() {
        Stage stage = this.getStage();
        
        if (stage != null && stage instanceof GamePlayResume) {
            ((GamePlayResume) stage).removeObjectFromGroup(GamePlayObjects.BONUS_LIST, this);
        }
        
        return super.remove();
    }
    
    /**
     * Check collision with player ship
     * @param ship player ship
     * @return true if the collision happens
     */
    public boolean colliding(PlayerShip ship){
        if (CollisionChecker.check(ship.getLimits(), this.limits)) {
            this.addBonusToShip(ship);
            this.remove();
            return true;
        }
        
        return false;
    }
    
    /**
     * Add bonus to ship
     * @param ship
     */
    protected abstract void addBonusToShip(Ship ship);
    
    /**
     * Get default timer of bonus animation
     * @return time in seconds from one frame to another
     */
    protected abstract float getFrameTimeNormalAnimation();
    
    /**
     * Get all frame names of bonus animation
     * @return frame names of animation
     */
    protected abstract String[] getFrameNames();
}
