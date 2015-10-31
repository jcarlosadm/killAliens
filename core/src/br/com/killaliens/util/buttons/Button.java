package br.com.killaliens.util.buttons;

import br.com.killaliens.util.collision.CollisionChecker;
import br.com.killaliens.util.font.FontCache;
import br.com.killaliens.util.image.TextureCache;
import br.com.killaliens.util.mouse.TouchAndMouseState;
import br.com.killaliens.util.text.TextMetrics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Button extends Actor {

    private TextureRegion image = null;

    private Rectangle limits = new Rectangle();
    
    private BitmapFont font = FontCache.getFont("buttonFont.fnt");
    private float textWidth = 0f;
    private float textHeight = 0f;

    public Button() {
        this.image = TextureCache.getTextureRegion(this.getImageName());
        this.setName(this.getButtonName());

        this.setX(0);
        this.setY(0);
        this.setWidth(this.image.getRegionWidth());
        this.setHeight(this.image.getRegionHeight());
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        this.setBounds(this.getX(), this.getY(), this.getWidth(),
                this.getHeight());
        
        TextMetrics textMetrics = new TextMetrics();
        textMetrics.setText(font, getButtonName());
        this.textWidth = textMetrics.getWidth();
        this.textHeight = textMetrics.getHeight();
        
        this.font.setColor(Color.BLACK);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        this.limits.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        this.limits.setY(y);
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        this.limits.setWidth(width);
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        this.limits.setHeight(height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        TouchAndMouseState touch = TouchAndMouseState.getInstance();
        if (touch.isOneClickDown()
                && CollisionChecker.check(this.limits, touch.getPoint().x,
                        touch.getPoint().y)) {
            this.action();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(this.image, this.getX(), this.getY());
        
        this.font.draw(batch, this.getName(), this.getX() + this.getOriginX()
                - this.textWidth / 2, this.getY() + this.getOriginY()
                + this.textHeight / 2);
    }

    protected abstract void action();

    protected abstract String getImageName();

    protected abstract String getButtonName();
}
