package ica.sugarwars;

import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.MoveableGameObject;

import java.util.ArrayList;

/**
 * @author Sijmen
 * Extend this class instead of MoveableGameObject if you want to be notefied
 * when a collision occears with another MoveableGameObject.
 */
public abstract class CollidableGameObject extends MoveableGameObject {

    /**
     * Every update should collisions be checked.
     * <b>When ovveriding this method, please call super.update()!!</b>
     */
    @Override
    public void update(){
        super.update();
        updateCollisions();
    }

    /**
     * Test for collisions and call {@link CollidableGameObject#collisionOccurred(CollidableGameObject)} by doing: <br>
     * Go through all colliding game objects. If one of the game objects
     * is CollidableGameObject than CollidableGameObject.collisionOccurred is called.
     */
    protected void updateCollisions(){
        ArrayList<GameObject> objects = getCollidedObjects();
        if(objects == null)
            return;
        for(GameObject obj :objects){
            if(!this.isActive())
                return;
            if(!obj.isActive())
                continue;
            if(obj instanceof CollidableGameObject){
                CollidableGameObject gameObj = (CollidableGameObject) obj;
                gameObj.collisionOccurred(this);
            }
        }
    }


    /**
     * This method is called when a other {@link ica.sugarwars.CollidableGameObject} collides with
     * this object.
     * @param collidingObj The colliding {@link ica.sugarwars.CollidableGameObject}
     */
    public abstract void collisionOccurred(CollidableGameObject collidingObj);

}
