package br.com.killaliens.util.speed;

public class NullSpeed extends Speed{

    private static Speed speedInstance = null;
    
    private NullSpeed(float speedX, float speedY) {
        super(speedX, speedY);
    }
    
    public static synchronized Speed getNullSpeedInstance(){
        if (speedInstance == null) {
            speedInstance = new NullSpeed(0, 0);
        }
        
        return speedInstance;
    }

}
