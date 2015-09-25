package br.com.killaliens;

import br.com.killaliens.screens.gamescreen.GameScreen;
import br.com.killaliens.util.image.TextureCache;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class KillAliens extends ApplicationAdapter {
	
	//private Map<String, Stage> screens = new HashMap<String, Stage>();
	private Stage currentScreen = null;
	
	@Override
	public void create () {
		
		//this.screens.put("GameScreen", new GameScreen());
		//this.currentScreen = this.screens.get("GameScreen");
		this.currentScreen = new GameScreen();
		
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
}
