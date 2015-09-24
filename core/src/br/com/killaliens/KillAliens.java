package br.com.killaliens;

import br.com.killaliens.screens.gamescreen.GameScreen;
import br.com.killaliens.util.image.TextureCache;
import br.com.killaliens.util.touch.TouchState;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class KillAliens extends ApplicationAdapter implements InputProcessor {
	
	//private Map<String, Stage> screens = new HashMap<String, Stage>();
	private Stage currentScreen = null;
	
	@Override
	public void create () {
		
		//this.screens.put("GameScreen", new GameScreen());
		//this.currentScreen = this.screens.get("GameScreen");
		this.currentScreen = new GameScreen();
		
		//Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void dispose() {
	    // TODO Auto-generated method stub
	    super.dispose();
	    TextureCache.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.currentScreen.act();
		this.currentScreen.draw();
	}

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        TouchState.setDownState(true);
        TouchState.setPosition(screenX, Gdx.graphics.getHeight() - screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        TouchState.setDownState(false);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}
