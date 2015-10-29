package br.com.killaliens.screens.title;

import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.killaliens.screens.Screen;
import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.ScreenType;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.screens.title.states.Credits;
import br.com.killaliens.screens.title.states.MainTitle;

public class TitleScreen implements Screen {

    private Stage mainState = new MainTitle();
    private Stage creditsState = new Credits();
    
    private Stage currentState = null;
    
    private static TitleScreen instance = new TitleScreen();
    
    private TitleScreen() {
        this.mainTitle();
    }
    
    public static TitleScreen getInstance(){
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
        ScreenManager.getInstance().changeCurrentScreen(ScreenType.GAMEPLAY_SCREEN);
        GamePlayScreen.getInstance().resume();
    }
    
    public void credits() {
        this.currentState = this.creditsState;
    }
}
