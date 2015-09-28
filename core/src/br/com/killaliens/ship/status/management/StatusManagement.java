package br.com.killaliens.ship.status.management;

public interface StatusManagement {
    
    public void setup();
    
    public void reset();
    
    public void act(float delta);
}
