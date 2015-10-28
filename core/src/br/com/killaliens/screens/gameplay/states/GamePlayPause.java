package br.com.killaliens.screens.gameplay.states;

import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.QuitButton;
import br.com.killaliens.util.buttons.implementations.ResumeButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GamePlayPause extends Stage {
    
    private static final float SPACE_BETWEEN_BUTTONS = 5f;
    
    public GamePlayPause() {
        // TODO Auto-generated constructor stub
        Button resumeButton = new ResumeButton(0, 0);
        Button quitButton = new QuitButton(0, 0);
        this.addActor(quitButton);
        this.addActor(resumeButton);
        
        float centerX = Gdx.graphics.getWidth() / 2;
        float centerY = Gdx.graphics.getHeight() / 2;
        
        float totalHeightButtons = (this.getActors().size - 1) * SPACE_BETWEEN_BUTTONS;
        
        for (Actor button : this.getActors()) {
            totalHeightButtons += button.getHeight();
        }
        
        float currentY = centerY + totalHeightButtons / 2;
        
        for (Actor button : this.getActors()) {
            button.setX(centerX - button.getWidth() / 2);
            button.setY(currentY);
            currentY += SPACE_BETWEEN_BUTTONS + button.getHeight();
        }
    }
}
