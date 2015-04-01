package ica.sugarwars;

/**
 * @author Sijmen
 */
public class PowerUp extends CollidableGameObject{

    /**
     * The gun that this PowerUp gives
     */
    private Gun newGun;

    /**
     * The powerup constructor.
     * @param newGun the gun to spawn
     */
    public PowerUp(Gun newGun){
        this.newGun = newGun;
    }

    /**
     * On collision with Player the player gets new gun :D
     * @param collidingObj The colliding {@link ica.sugarwars.CollidableGameObject}
     */
    @Override
    public void collisionOccurred(CollidableGameObject collidingObj) {
        if(collidingObj instanceof Player){
            Player player = (Player) collidingObj;
            player.setGun(newGun);
            this.clearActive();
        }
    }
}
