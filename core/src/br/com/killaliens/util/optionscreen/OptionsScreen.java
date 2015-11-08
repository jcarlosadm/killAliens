package br.com.killaliens.util.optionscreen;

import java.util.List;

import br.com.killaliens.util.buttons.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class OptionsScreen extends Stage {
    
    private static final float SPACE_BETWEEN_BUTTONS = 20f;
    
    protected void setButtons(List<Button> buttons){
        float centerX = Gdx.graphics.getWidth() / 2;
        float centerY = Gdx.graphics.getHeight() / 2;

        float totalHeightButtons = 0f;

        for (Button button : buttons) {
            totalHeightButtons += button.getHeight();
            totalHeightButtons += SPACE_BETWEEN_BUTTONS;
        }
        totalHeightButtons -= SPACE_BETWEEN_BUTTONS;

        float currentY = centerY - totalHeightButtons / 2;

        for (Button button : buttons) {
            button.setX(centerX - button.getWidth() / 2);
            button.setY(currentY);
            currentY += SPACE_BETWEEN_BUTTONS + button.getHeight();
            
            this.addActor(button);
        }
    }
}
