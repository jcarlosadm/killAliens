package br.com.killaliens.util.cache.font;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontCache {
    private static final String FONT_FOLDER = "fonts/";
    private static Map<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
    
    public static BitmapFont getFont(String fontName){
        if (fonts.containsKey(fontName)) {
            return fonts.get(fontName);
        }
        
        BitmapFont font = new BitmapFont(Gdx.files.internal(FONT_FOLDER+fontName));
        fonts.put(fontName, font);
        return font;
    }
    
    public static void dispose() {
        for (BitmapFont font : fonts.values()) {
            font.dispose();
        }
    }
}
