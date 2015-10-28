package br.com.killaliens;

import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.util.font.FontCache;
import br.com.killaliens.util.image.TextureCache;
import br.com.killaliens.util.mouse.TouchAndMouseState;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class KillAliens extends ApplicationAdapter {
	
	private ScreenManager screenManager = null;
	
	@Override
	public void create () {
		// TODO implement
	    this.screenManager = ScreenManager.getInstance();
	    
	    Gdx.input.setInputProcessor(TouchAndMouseState.getInstance());
	}
	
	@Override
	public void dispose() {
	    // TODO Auto-generated method stub
	    super.dispose();
	    TextureCache.dispose();
	    FontCache.dispose();
	    // TODO dispose screens, sounds and musics
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.screenManager.act();
		this.screenManager.draw();
		TouchAndMouseState.getInstance().update();
	}
	
	@Override
	public void resize(int width, int height) {
	    this.screenManager.resize(width, height);
	}
}
