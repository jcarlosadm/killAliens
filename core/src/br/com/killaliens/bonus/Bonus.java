package br.com.killaliens.bonus;

import br.com.killaliens.screens.gamescreen.GameScreen;
import br.com.killaliens.ship.Ship;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.util.animation.AnimationManagement;
import br.com.killaliens.util.animation.AnimationTypes;
import br.com.killaliens.util.animation.BuildAnimation;
import br.com.killaliens.util.camera.CheckVisibleOnCamera;
import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Bonus extends Actor {
    
    private AnimationManagement animationManagement = new AnimationManagement();
    
    private Rectangle limits = new Rectangle();
    
    private CheckVisibleOnCamera checkVisibleOnCamera  = null; 
    
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
        
        batch.draw(this.animationManagement.getCurrentTextureRegion(true), 
                this.getX(), this.getY());
    }
    
    @Override
    public boolean remove() {
        Stage stage = this.getStage();
        
        if (stage != null && stage instanceof GameScreen) {
            ((GameScreen) stage).removeBonus(this);
        }
        
        return super.remove();
    }
    
    public boolean colliding(PlayerShip ship){
        if (ship.colliding(this.limits)) {
            this.addBonusToShip(ship);
            this.remove();
            return true;
        }
        
        return false;
    }
    
    protected abstract void addBonusToShip(Ship ship);
    
    protected abstract float getFrameTimeNormalAnimation();
    
    protected abstract String[] getFrameNames();
}
