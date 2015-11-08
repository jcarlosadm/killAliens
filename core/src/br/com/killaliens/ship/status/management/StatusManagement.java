package br.com.killaliens.ship.status.management;

public interface StatusManagement {
    
    /**
     * Set up this status (when you get him)
     */
    public void setup();
    
    /**
     * reset this status
     */
    public void reset();
    
    /**
     * Logic of this status
     * @param delta time in seconds since the last frame
     */
    public void act(float delta);
}
