package br.com.killaliens.util.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TextMetrics {

    private GlyphLayout layout = null;

    public void setText(BitmapFont font, String text) {
        if (this.layout == null) {
            this.layout = new GlyphLayout();
        }
        
        this.layout.setText(font, text);
    }

    public float getWidth() {
        if (this.layout == null) {
            return 0;
        }
        
        return this.layout.width;
    }

    public float getHeight() {
        if (this.layout == null) {
            return 0;
        }
        
        return this.layout.height;
    }
}
