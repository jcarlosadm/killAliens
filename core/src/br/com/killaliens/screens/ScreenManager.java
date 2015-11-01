package br.com.killaliens.screens;

import java.util.HashMap;
import java.util.Map;

import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.screens.title.TitleScreen;

/**
 * ScreenManager
 * TODO Singleton pattern
 */
public class ScreenManager {
    
    private Map<ScreenType, Screen> screens = new HashMap<ScreenType, Screen>();
    
    private Screen currentScreen = null;
    
    private static ScreenManager instance = new ScreenManager();
    
    private ScreenManager() {
        GamePlayScreen gamePlayScreen = GamePlayScreen.getInstance(this);
        this.screens.put(ScreenType.GAMEPLAY_SCREEN, gamePlayScreen);
        this.screens.put(ScreenType.TITLE_SCREEN, TitleScreen.getInstance(this, gamePlayScreen));
        
        this.currentScreen = this.screens.get(ScreenType.TITLE_SCREEN);
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
