package br.com.killaliens.util.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CheckVisibleOnCamera {
    
    private Rectangle recActor = new Rectangle();
    private Rectangle recCamera = new Rectangle();
    
    private Actor actor = null;
    
    public CheckVisibleOnCamera(Actor actor) {
        this.actor = actor;
    }
    
    public boolean actorIsVisible(Camera camera){
        this.buildRectCamera(camera);
        this.buildRectActor();
        return (!this.outOfLimits());
    }

    private boolean outOfLimits() {
        return (!this.recActor.overlaps(this.recCamera));
    }

    private void buildRectCamera(Camera camera) {
        this.recCamera.setX(camera.position.x - camera.viewportWidth/2);
        this.recCamera.setWidth(camera.viewportWidth);
        this.recCamera.setY(camera.position.y - camera.viewportHeight/2);
        this.recCamera.setHeight(camera.viewportHeight);
    }
    
    private void buildRectActor() {
        this.recActor.setX(actor.getX());
        this.recActor.setY(actor.getY());
        this.recActor.setWidth(actor.getWidth());
        this.recActor.setHeight(actor.getHeight());
    }
}
