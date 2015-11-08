package br.com.killaliens.screens.gameplay.states.resume.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InfinityBackgroundImage {
    private float positionX = 0f, positionY = 0f;
    private TextureRegion image = null;
    
    private int scaledWidthFactor = 1;
    private int scaledHeightFactor = 1;
    
    /**
     * Constructor
     * @param image image to background
     * @param positionX initial position x
     * @param positionY initial position y
     */
    protected InfinityBackgroundImage(TextureRegion image, float positionX, float positionY) {
        this.image = image;
        this.positionX = positionX;
        this.positionY = positionY;
        
        this.calcScaledFactors();
    }
    
    /**
     * calculate factors to scaling background
     */
    protected void calcScaledFactors() {
        float scaledWidthFactor = Gdx.graphics.getWidth()
                / (float) this.image.getRegionWidth();
        float scaledHeightFactor = Gdx.graphics.getHeight()
                / (float) this.image.getRegionHeight();

        this.scaledWidthFactor = (int) Math.ceil(scaledWidthFactor);
        this.scaledHeightFactor = (int) Math.ceil(scaledHeightFactor);
    }
    
    /**
     * @param positionX position x to set
     */
    protected void setPositionX(float positionX){
        this.positionX = positionX;
    }
    
    /**
     * @return position x
     */
    protected float getPositionX(){
        return this.positionX;
    }
    
    /**
     * @param positionY position y to set
     */
    protected void setPositionY(float positionY){
        this.positionY = positionY;
    }
    
    /**
     * @return position y
     */
    protected float getPositionY(){
        return this.positionY;
    }
    
    /**
     * @return scaled width
     */
    protected float getWidth(){
        return this.image.getRegionWidth() * this.scaledWidthFactor;
    }
    
    /**
     * @return scaled height
     */
    protected float getHeight(){
        return this.image.getRegionHeight() * this.scaledHeightFactor;
    }
    
    /**
     * Draw this background image
     * @param batch place to draw
     */
    protected void draw(Batch batch) {
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
