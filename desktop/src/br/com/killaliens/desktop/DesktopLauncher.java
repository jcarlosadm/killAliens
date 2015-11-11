package br.com.killaliens.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import br.com.killaliens.KillAliens;

public class DesktopLauncher {
    private static final double ADJUST_HEIGHT = 0.10;
    private static final double ADJUST_WIDTH = 0.65;
    
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		config.height = (int) (screenSize.getHeight() 
		        - (screenSize.getHeight()*ADJUST_HEIGHT));
		config.width = (int) (screenSize.getWidth() 
		        - (screenSize.getWidth()*ADJUST_WIDTH));
		
		new LwjglApplication(new KillAliens(), config);
	}
}
