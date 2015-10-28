package br.com.killaliens.screens.gameplay;

import br.com.killaliens.screens.Screen;
import br.com.killaliens.screens.gameplay.states.GamePlayResume;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GamePlayScreen implements Screen {
    
    private Stage resumeState = new GamePlayResume();
    
    private Stage currentState = this.resumeState;
    
    private static GamePlayScreen instance = new GamePlayScreen();
    
    private GamePlayScreen() {
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
        // TODO Auto-generated method stub
        
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
