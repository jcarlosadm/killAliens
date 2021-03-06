package br.com.killaliens;

import br.com.killaliens.screens.ScreenManager;
import br.com.killaliens.util.cache.font.FontCache;
import br.com.killaliens.util.cache.images.TextureCache;
import br.com.killaliens.util.cache.musics.MusicCache;
import br.com.killaliens.util.cache.sounds.SoundCache;
import br.com.killaliens.util.mouse.TouchAndMouseState;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class KillAliens extends ApplicationAdapter {
	
	private ScreenManager screenManager = null;
	
	private TouchAndMouseState touchAndMouse = null;
	
	@Override
	public void create () {
	    this.screenManager = ScreenManager.getInstance();
	    this.touchAndMouse = TouchAndMouseState.getInstance();
	    
	    Gdx.input.setInputProcessor(this.touchAndMouse);
	}
	
	@Override
	public void dispose() {
	    super.dispose();
	    TextureCache.dispose();
	    FontCache.dispose();
	    SoundCache.dispose();
	    MusicCache.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.screenManager.act();
		this.screenManager.draw();
		this.touchAndMouse.update();
	}
	
	@Override
	public void resize(int width, int height) {
	    this.screenManager.resize(width, height);
	}
}
