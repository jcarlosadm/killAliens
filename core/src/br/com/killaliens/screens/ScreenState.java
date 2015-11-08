package br.com.killaliens.screens;

/**
 * ScreenState
 * TODO State Pattern
 */
public interface ScreenState {
    
    /**
     * act of the state
     */
    public void act();
    
    /**
     * draw of the state
     */
    public void draw();
}
