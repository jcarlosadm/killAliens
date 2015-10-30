package br.com.killaliens.screens.title;

import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.killaliens.screens.Screen;
import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.ScreenType;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.screens.title.states.Credits;
import br.com.killaliens.screens.title.states.MainTitle;

public class TitleScreen implements Screen {

    private Stage mainState = null;
    private Stage creditsState = null;
    
    private Stage currentState = null;
    
    private ScreenManager screenManager = null;
    
    private GamePlayScreen gamePlayScreen = null;
    
    private static TitleScreen instance = null;
    
    private TitleScreen(ScreenManager screenManager, GamePlayScreen gamePlayScreen) {
        this.screenManager = screenManager;
        this.gamePlayScreen = gamePlayScreen;
        
        this.mainState = new MainTitle(this);
        this.creditsState = new Credits(this);
        
        this.mainTitle();
    }
    
    public static TitleScreen getInstance(ScreenManager screenManager,
            GamePlayScreen gamePlayScreen){
        if (instance == null) {
            instance = new TitleScreen(screenManager, gamePlayScreen);
        }
        
        return instance;
    }
    
    @Override
    public void act() {
        this.currentState.act();
    }

    @Override
    public void draw() {
        this.currentState.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.currentState.getViewport().update(width, height);
    }
    
    public void mainTitle(){
        this.currentState = this.mainState;
    }
    
    public void startGame() {
        this.screenManager.changeCurrentScreen(ScreenType.GAMEPLAY_SCREEN);
        this.gamePlayScreen.resume();
    }
    
    public void credits() {
        this.currentState = this.creditsState;
    }
}
