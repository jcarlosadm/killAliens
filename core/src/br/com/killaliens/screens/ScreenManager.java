package br.com.killaliens.screens;

import java.util.HashMap;
import java.util.Map;

import br.com.killaliens.screens.gameplay.GamePlayScreen;

public class ScreenManager {
    
    private Map<ScreenType, Screen> screens = new HashMap<ScreenType, Screen>();
    
    private Screen currentScreen = null;
    
    private static ScreenManager instance = new ScreenManager();
    
    private ScreenManager() {
        // TODO Auto-generated constructor stub
        this.screens.put(ScreenType.GAMEPLAY_SCREEN, GamePlayScreen.getInstance());
        
        this.currentScreen = this.screens.get(ScreenType.GAMEPLAY_SCREEN);
    }
    
    public static ScreenManager getInstance(){
        return instance;
    }
    
    public void act() {
        this.currentScreen.act();
    }
    
    public void draw(){
        this.currentScreen.draw();
    }
    
    public void changeCurrentScreen(ScreenType type){
        Screen screen = this.screens.get(type);
        if (screen != null) {
            this.currentScreen = screen;
        }
    }
    
    public void resize(int width, int height) {
        this.currentScreen.resize(width, height);
    }
}
