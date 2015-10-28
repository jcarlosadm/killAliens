package br.com.killaliens.util.buttons;

import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public abstract class Button extends Actor {

    private boolean touched = false;
    
    private TextureRegion image = null;
    
    public Button(float positionX, float positionY) {
        this.image = TextureCache.getTextureRegion(this.getImageName());
        this.setName(this.getButtonName());
        
        this.setX(positionX);
        this.setY(positionY);
        this.setWidth(this.image.getRegionWidth());
        this.setHeight(this.image.getRegionHeight());
        this.setOrigin(this.getWidth()/2, this.getHeight()/2);
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        this.setTouchable(Touchable.enabled);
        this.setVisible(true);
        this.addListener(new ButtonTouchListener(this));
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        
        if (this.touched == true) {
            this.action();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        batch.draw(this.image, this.getX(), this.getY());
    }
    
    protected void setTouched(boolean touched){
        this.touched = touched;
    }
    
    protected abstract void action();
    
    protected abstract String getImageName();
    
    protected abstract String getButtonName();
}
