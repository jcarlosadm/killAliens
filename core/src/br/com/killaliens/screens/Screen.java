package br.com.killaliens.screens;

/**
 * Screen
 * TODO Strategy pattern
 */
public interface Screen {
    
    /**
     * Logic of the screen
     */
    public void act();
    
    /**
     * Draw of the screen
     */
    public void draw();
    
    /**
     * Resize screen
     * @param width width of the screen
     * @param height height of the screen
     */
    public void resize(int width, int height);
}
