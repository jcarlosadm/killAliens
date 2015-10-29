package br.com.killaliens.screens.title.states;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;

import br.com.killaliens.screens.options.OptionsScreen;
import br.com.killaliens.screens.title.background.MainTitleBackground;
import br.com.killaliens.util.buttons.Button;
import br.com.killaliens.util.buttons.implementations.CreditsButton;
import br.com.killaliens.util.buttons.implementations.PlayButton;
import br.com.killaliens.util.buttons.implementations.QuitMainTitleButton;

public class MainTitle extends OptionsScreen {
    
    public MainTitle() {
        this.addActor(new MainTitleBackground());
        System.out.println(Gdx.graphics.getWidth()+" "+Gdx.graphics.getHeight());
        
        List<Button> buttons = new ArrayList<Button>();
        
        buttons.add(new QuitMainTitleButton());
        buttons.add(new CreditsButton());
        buttons.add(new PlayButton());
        
        this.setButtons(buttons);
    }
    
}
