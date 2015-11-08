package br.com.killaliens.util.mouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * TouchAndMouseState
 * TODO Singleton pattern
 */
public class TouchAndMouseState implements InputProcessor {
    
    private boolean oneClick = false;
    private boolean pressing = false;
    private boolean release = false;
    
    private Vector2 point = new Vector2(0, 0);
    
    private static TouchAndMouseState instance = new TouchAndMouseState();
    
    private TouchAndMouseState() {
    }
    
    /**
     * @return TouchAndMouseState instance
     */
    public static TouchAndMouseState getInstance(){
        return instance;
    }
    
    /**
     * update touch and mouse states
     */
    public void update(){
        this.oneClick = false;
        this.release = false;
    }
    
    /**
     * @return true if the mouse is clicked (or touch is touched) once
     */
    public boolean isOneClickDown(){
        return (this.oneClick == true);
    }
    
    /**
     * @return true if the mouse or touch is released
     */
    public boolean isOneClickUp(){
        return (this.release == true);
    }
    
    /**
     * @return true if the mouse or touch is hold down
     */
    public boolean isPressing(){
        return (this.pressing == true);
    }
    
    /**
     * @return vector with x and y coordinates of the mouse pointer or last place touched
     */
    public Vector2 getPoint(){
        return this.point;
    }
    
    /**
     * Adjust y coordinate to use down-top orientation
     * @param positionY y coordinate with top-down orientation
     * @return y coordinate with down-top coordinate
     */
    private float adjustY(float positionY){
        return Gdx.graphics.getHeight() - positionY;
    }
    
    /**
     * Set position of the mouse pointer or last place touched
     * @param screenX
     * @param screenY
     */
    private void setPosition(float screenX, float screenY){
        this.point.set(screenX, this.adjustY(screenY));
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.oneClick = true;
        this.pressing = true;
        this.setPosition(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        this.release = true;
        this.pressing = false;
        this.setPosition(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        this.setPosition(screenX, screenY);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
