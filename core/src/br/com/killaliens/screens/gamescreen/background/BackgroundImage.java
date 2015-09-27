package br.com.killaliens.screens.gamescreen.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackgroundImage {
    private float positionX = 0f, positionY = 0f;
    private TextureRegion image = null;
    
    private int scaledWidthFactor = 1;
    private int scaledHeightFactor = 1;
    
    public BackgroundImage(TextureRegion image, float positionX, float positionY) {
        this.image = image;
        this.positionX = positionX;
        this.positionY = positionY;
        
        this.recalcScaledFactors();
    }
    
    public void recalcScaledFactors() {
        float scaledWidthFactor = Gdx.graphics.getWidth()
                / (float) this.image.getRegionWidth();
        float scaledHeightFactor = Gdx.graphics.getHeight()
                / (float) this.image.getRegionHeight();

        this.scaledWidthFactor = (int) Math.ceil(scaledWidthFactor);
        this.scaledHeightFactor = (int) Math.ceil(scaledHeightFactor);
    }
    
    public void setPositionX(float positionX){
        this.positionX = positionX;
    }
    
    public float getPositionX(){
        return this.positionX;
    }
    
    public void setPositionY(float positionY){
        this.positionY = positionY;
    }
    
    public float getPositionY(){
        return this.positionY;
    }
    
    public float getWidth(){
        return this.image.getRegionWidth() * this.scaledWidthFactor;
    }
    
    public float getHeight(){
        return this.image.getRegionHeight() * this.scaledHeightFactor;
    }
    
    public void draw(Batch batch) {
        float addToX = 0, addToY = 0;
        for (int currentFactorY = 0; currentFactorY < this.scaledHeightFactor; currentFactorY++) {
            for (int currentFactorX = 0; currentFactorX < this.scaledWidthFactor; currentFactorX++) {
                addToX = this.image.getRegionWidth() * currentFactorX;
                addToY = this.image.getRegionHeight() * currentFactorY;

                batch.draw(this.image, this.positionX + addToX,this.positionY + addToY);
            }
        }
    }
}
