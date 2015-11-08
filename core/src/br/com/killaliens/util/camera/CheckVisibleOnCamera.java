package br.com.killaliens.util.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CheckVisibleOnCamera {
    
    private Rectangle recActor = new Rectangle();
    private Rectangle recCamera = new Rectangle();
    
    private Actor actor = null;
    
    /**
     * Constructor
     * @param actor
     */
    public CheckVisibleOnCamera(Actor actor) {
        this.actor = actor;
    }
    
    /**
     * @return true if actor is visible on screen
     */
    public boolean actorIsVisible(){
        this.buildRectCamera(this.actor.getStage().getCamera());
        this.buildRectActor();
        return (!this.outOfLimits());
    }

    /**
     * true if actor is out of limits of the camera
     * @return
     */
    private boolean outOfLimits() {
        return (!this.recActor.overlaps(this.recCamera));
    }

    /**
     * build rectangle shape of the camera
     * @param camera
     */
    private void buildRectCamera(Camera camera) {
        this.recCamera.setX(camera.position.x - camera.viewportWidth/2);
        this.recCamera.setWidth(camera.viewportWidth);
        this.recCamera.setY(camera.position.y - camera.viewportHeight/2);
        this.recCamera.setHeight(camera.viewportHeight);
    }
    
    /**
     * build rectangle shape of the actor
     */
    private void buildRectActor() {
        this.recActor.setX(actor.getX());
        this.recActor.setY(actor.getY());
        this.recActor.setWidth(actor.getWidth());
        this.recActor.setHeight(actor.getHeight());
    }
}
