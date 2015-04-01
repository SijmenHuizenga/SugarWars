package ica.sugarwars;

/**
 * @author Stefan Mudde
 */
public class Projectile extends CollidableGameObject{

    /**
     * the amount of damage this projectile gives.
     */
    private int damage;

    /**
     * Wether or not tis projectile is friendly to the {@link Player}
     */
    private boolean isFriendly;

    /**
     * Constructor to initialize the projectile.
     * @param damage  The damage this projectile does to the entity.
     * @param isFriendly Wether or not this projectile is shot by {@link Player} or {@link Enemy}
     */
    public Projectile(int damage, boolean isFriendly){
        this.damage = damage;
        this.isFriendly = isFriendly;
    }

    /**
     * When the projectile is outisde the world, than delete the projectile from the game.
     */
    @Override
    public void outsideWorld() {
        removeMe();
    }

    /**
     * removes the projectile from the game.
     */
    private void removeMe(){
        //explosions are handeled at Enemy.onDie()
        this.clearActive();
    }

    /**
     * When a collision occeurs with an Entity, and the entity's friendlyness is different
     * from the friendlyness of this Projecile, than the entity will take damage.
     * @param collidingObj
     */
    @Override
    public void collisionOccurred(CollidableGameObject collidingObj) {
        if(collidingObj instanceof Entity){
            Entity entity = (Entity) collidingObj;
            if(entity.isFriendly() != this.isFriendly){
                entity.setHealth(entity.getHealth()-damage);
                removeMe();
            }
        }
    }
}
