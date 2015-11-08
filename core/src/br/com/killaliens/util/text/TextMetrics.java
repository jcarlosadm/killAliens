package br.com.killaliens.util.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TextMetrics {

    private GlyphLayout layout = null;

    /**
     * set text to measure
     * @param font font of the text
     * @param text text to measure
     */
    public void setText(BitmapFont font, String text) {
        if (this.layout == null) {
            this.layout = new GlyphLayout();
        }
        
        this.layout.setText(font, text);
    }

    /**
     * @return width of the text
     */
    public float getWidth() {
        if (this.layout == null) {
            return 0;
        }
        
        return this.layout.width;
    }

    /**
     * @return height of the text
     */
    public float getHeight() {
        if (this.layout == null) {
            return 0;
        }
        
        return this.layout.height;
    }
}
