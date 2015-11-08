package br.com.killaliens.screens.gameplay;

import br.com.killaliens.screens.Screen;
import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.screens.ScreenState;
import br.com.killaliens.screens.gameplay.states.gameover.GamePlayOver;
import br.com.killaliens.screens.gameplay.states.pause.GamePlayPause;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.screens.gameplay.states.win.GamePlayWin;
import br.com.killaliens.ship.player.PlayerShip;
import br.com.killaliens.util.cache.musics.MusicCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GamePlayScreen implements Screen {

    private static final float TIMELIMIT_TO_WIN = 3f;

    private ScreenState resumeState = null;
    private ScreenState pauseState = null;
    private ScreenState gameoverState = null;
    private ScreenState winState = null;

    private ScreenState currentState = null;

    private static GamePlayScreen instance = null;

    private boolean winTimerOn = false;
    private float winTimer = 0f;
    
    private Music gameMusic = MusicCache.getMusic("game.mp3");

    /**
     * Constructor
     * @param screenManager ScreenManager object
     */
    private GamePlayScreen(ScreenManager screenManager) {
        this.resumeState = new GamePlayResume(this);
        this.pauseState = new GamePlayPause(this, screenManager);
        this.gameoverState = new GamePlayOver(this, screenManager);
        this.winState = new GamePlayWin(this, screenManager);
    }

    /**
     * get GamePlayScreen instance
     * @param screenManager ScreenManager object
     * @return GamePlayScreen instance
     */
    public static synchronized GamePlayScreen getInstance(ScreenManager screenManager) {
        if (instance == null) {
            instance = new GamePlayScreen(screenManager);
        }
        
        return instance;
    }

    @Override
    public void act() {
        if (this.winTimerOn) {
            this.winTimer += Gdx.graphics.getDeltaTime();
        }
        if (this.winTimer >= TIMELIMIT_TO_WIN) {
            this.currentState = this.winState;
            this.winTimerOn = false;
            this.winTimer = 0f;
        }

        this.currentState.act();
    }

    @Override
    public void draw() {
        if (!(this.currentState instanceof GamePlayResume)) {
            this.resumeState.draw();
        }
        this.currentState.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (this.currentState instanceof Stage) {
            ((Stage)this.currentState).getViewport().update(width, height);
        }
    }

    /**
     * set resume state
     */
    public void resume() {
        this.currentState = this.resumeState;
        if (!this.gameMusic.isPlaying()) {
            this.gameMusic.setLooping(true);
            this.gameMusic.play();
        }
    }

    /**
     * set pause state
     */
    public void pause() {
        this.currentState = this.pauseState;
    }

    /**
     * set game over state
     */
    public void gameover() {
        this.currentState = this.gameoverState;
    }

    /**
     * set win state
     */
    public void win() {
        this.winTimerOn = true;
    }

    /**
     * reset game play screen
     */
    public void reset() {
        this.gameMusic.stop();
        PlayerShip.reset();

        this.resumeState = new GamePlayResume(this);
    }
}
