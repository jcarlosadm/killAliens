package br.com.killaliens.screens;

import java.util.HashMap;
import java.util.Map;

import br.com.killaliens.screens.gamescreen.GameScreen;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class ScreenManager {
    
    private Map<ScreenType, Stage> screens = new HashMap<ScreenType, Stage>();
    
    private Stage currentScreen = null;
    
    public ScreenManager() {
        // TODO Auto-generated constructor stub
        this.screens.put(ScreenType.GAME_SCREEN, new GameScreen(this));
        
        this.currentScreen = this.screens.get(ScreenType.GAME_SCREEN);
    }
    
    public void act() {
        this.currentScreen.act();
    }
    
    public void draw(){
        this.currentScreen.draw();
    }
    
    public void changeCurrentScreen(ScreenType type){
        Stage stage = this.screens.get(type);
        if (stage != null) {
            this.currentScreen = stage;
        }
    }
    
    public void resize(int width, int height) {
        this.currentScreen.getViewport().update(width, height);
    }
    
}
