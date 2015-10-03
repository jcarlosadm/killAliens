package br.com.killaliens.screens.gamescreen.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.killaliens.screens.gamescreen.GameScreen;
import br.com.killaliens.util.scrollobserver.ScrollObserver;

public class InformationLevelPhase extends Actor implements ScrollObserver{

    private BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/statsFontSmall.fnt"));
    
    public InformationLevelPhase() {
        // TODO Auto-generated constructor stub
        this.setX(0);
        this.setY(Gdx.graphics.getHeight() - 10f);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        super.draw(batch, parentAlpha);
        
        String level = "";
        Stage stage = this.getStage();
        if (stage != null && stage instanceof GameScreen) {
            level = ((GameScreen) stage).getEnemySpawnLevel().toString();
        }
        
        font.draw(batch, "Phase level: "+ level, this.getX(), this.getY());
    }
    
    @Override
    public void updateScroll(float xDelta, float yDelta) {
        this.setY(this.getY() + yDelta);
    }
}
