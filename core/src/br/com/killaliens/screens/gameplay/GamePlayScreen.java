package br.com.killaliens.screens.gameplay;

import br.com.killaliens.screens.Screen;
import br.com.killaliens.screens.gameplay.states.gameover.GamePlayOver;
import br.com.killaliens.screens.gameplay.states.pause.GamePlayPause;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;
import br.com.killaliens.screens.gameplay.states.win.GamePlayWin;
import br.com.killaliens.ship.player.PlayerShip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GamePlayScreen implements Screen {
    
    private static final float TIMELIMIT_TO_WIN = 3f;
    
    private Stage resumeState = new GamePlayResume();
    private Stage pauseState = new GamePlayPause();
    private Stage gameoverState = new GamePlayOver();
    private Stage winState = new GamePlayWin();
    
    private Stage currentState = null;
    
    private static GamePlayScreen instance = new GamePlayScreen();
    
    private boolean winTimerOn = false;
    private float winTimer = 0f;
    
    private GamePlayScreen() {
        this.resume();
    }
    
    public static GamePlayScreen getInstance(){
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
        this.currentState.getViewport().update(width, height);
    }
    
    public void resume() {
        this.currentState = this.resumeState;
    }
    
    public void pause() {
        this.currentState = this.pauseState;
    }
    
    public void gameover() {
        this.currentState = this.gameoverState;
    }
    
    public void win(){
        this.winTimerOn = true;
    }
    
    public void reset() {
        this.resumeState.dispose();
        PlayerShip.reset();
        
        this.resumeState = new GamePlayResume();
    }
}
