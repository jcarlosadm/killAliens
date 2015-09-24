package br.com.killaliens.util.touch;

import com.badlogic.gdx.math.Vector2;

public class TouchState {
    
    private static boolean downState;
    private static Vector2 position = new Vector2();
    
    public static boolean getDownState() {
        return downState;
    }
    
    public static void setDownState(boolean dState) {
        downState = dState;
    }
    
    public static void setPosition(float posX, float posY) {
        position.x = posX;
        position.y = posY;
    }
    
    public static Vector2 getPosition() {
        return position;
    }
}
