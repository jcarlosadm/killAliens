package br.com.killaliens.screens.gameplay;

import br.com.killaliens.screens.Screen;
import br.com.killaliens.screens.gameplay.states.pause.GamePlayPause;
import br.com.killaliens.screens.gameplay.states.resume.GamePlayResume;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GamePlayScreen implements Screen {
    
    private Stage resumeState = new GamePlayResume();
    private Stage pauseState = new GamePlayPause();
    
    private Stage currentState = null;
    
    private static GamePlayScreen instance = new GamePlayScreen();
    
    private GamePlayScreen() {
        this.resume();
    }
    
    public static GamePlayScreen getInstance(){
        return instance;
    }
    
    @Override
    public void act() {
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
        // TODO Auto-generated method stub
        
    }
    
    public void win(){
        // TODO Auto-generated method stub
    }
    
    public void reset() {
        this.resumeState.dispose();
        this.resumeState = new GamePlayResume();
    }
}
