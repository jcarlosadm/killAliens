package br.com.killaliens.util.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CheckVisibleOnCamera {
    
    private Rectangle recActor = new Rectangle();
    
    private Rectangle recCamera = new Rectangle();
    
    public CheckVisibleOnCamera(Actor actor) {
        this.recActor.setX(actor.getX());
        this.recActor.setY(actor.getY());
        this.recActor.setWidth(actor.getWidth());
        this.recActor.setHeight(actor.getHeight());
    }
    
    public boolean actorIsVisible(Camera camera){
        this.buildRectCamera(camera);
        return (!this.outOfLimits());
    }

    private boolean outOfLimits() {
        return ((this.recActor.getX() + this.recActor.getWidth() <= this.recCamera.getX()) ||
                (this.recActor.getX() >= this.recCamera.getX() + this.recCamera.getWidth()) ||
                (this.recActor.getY() + this.recActor.getHeight() <= this.recCamera.getY()) ||
                (this.recActor.getY() >= this.recCamera.getY() + this.recCamera.getHeight()));
    }

    private void buildRectCamera(Camera camera) {
        this.recCamera.setX(camera.position.x - camera.viewportWidth/2);
        this.recCamera.setWidth(camera.viewportWidth);
        this.recCamera.setY(camera.position.y - camera.viewportHeight/2);
        this.recCamera.setHeight(camera.viewportHeight);
    }
}
