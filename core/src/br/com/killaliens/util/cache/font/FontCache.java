package br.com.killaliens.util.cache.font;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * FontCache
 * TODO Flyweight pattern
 */
public class FontCache {
    private static final String FONT_FOLDER = "fonts/";
    private static Map<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
    
    private FontCache() {
    }
    
    /**
     * get font
     * @param fontName
     * @return font instance
     */
    public static BitmapFont getFont(String fontName){
        if (fonts.containsKey(fontName)) {
            return fonts.get(fontName);
        }
        
        BitmapFont font = new BitmapFont(Gdx.files.internal(FONT_FOLDER+fontName));
        fonts.put(fontName, font);
        return font;
    }
    
    /**
     * dispose all fonts
     */
    public static void dispose() {
        for (BitmapFont font : fonts.values()) {
            font.dispose();
        }
    }
}
