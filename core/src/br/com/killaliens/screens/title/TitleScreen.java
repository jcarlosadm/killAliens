package br.com.killaliens.screens.title;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.killaliens.screens.Screen;
import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.ScreenState;
import br.com.killaliens.screens.ScreenType;
import br.com.killaliens.screens.gameplay.GamePlayScreen;
import br.com.killaliens.screens.title.states.Credits;
import br.com.killaliens.screens.title.states.MainTitle;
import br.com.killaliens.util.cache.musics.MusicCache;

public class TitleScreen implements Screen {

    private ScreenState mainState = null;
    private ScreenState creditsState = null;
    
    private ScreenState currentState = null;
    
    private ScreenManager screenManager = null;
    
    private GamePlayScreen gamePlayScreen = null;
    
    private static TitleScreen instance = null;
    
    private Music titleMusic = MusicCache.getMusic("title.mp3");
    
    /**
     * Constructor
     * @param screenManager ScreenManager object
     * @param gamePlayScreen GamePlayScreen object
     */
    private TitleScreen(ScreenManager screenManager, GamePlayScreen gamePlayScreen) {
        this.screenManager = screenManager;
        this.gamePlayScreen = gamePlayScreen;
        
        this.mainState = new MainTitle(this);
        this.creditsState = new Credits(this);
        
        this.mainTitle();
    }
    
    /**
     * Get TitleScreen instance
     * @param screenManager ScreenManager object
     * @param gamePlayScreen GamePlayScreen object
     * @return TitleScreen instance
     */
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
        if (this.currentState instanceof Stage) {
            ((Stage) this.currentState).getViewport().update(width, height);
        }
    }
    
    /**
     * show the main title
     */
    public void mainTitle(){
        this.currentState = this.mainState;
        if (!this.titleMusic.isPlaying()) {
            this.titleMusic.setLooping(true);
            this.titleMusic.play();
        }
    }
    
    /**
     * start the game
     */
    public void startGame() {
        this.titleMusic.stop();
        this.screenManager.changeCurrentScreen(ScreenType.GAMEPLAY_SCREEN);
        this.gamePlayScreen.resume();
    }
    
    /**
     * show the credits
     */
    public void credits() {
        this.currentState = this.creditsState;
    }
}
