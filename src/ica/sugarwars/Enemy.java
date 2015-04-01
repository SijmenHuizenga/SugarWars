package ica.sugarwars;

import ica.sugarwars.effects.Explosion;

/**
 * @author Stefan Mudde
 */
public abstract class Enemy extends Entity {

    /**
     * The damage this player does when the Enemy hits a player
     */
    private int impactDamage;

    /**
     * This initializes a new Enemy
     *
     * @param theGame      The game where this enemy is
     * @param impactDamage The impact damage this enemy does when hitting the player
     * @param startHealth  The start health of the enemy
     */
    public Enemy(SugarWars theGame, int impactDamage, int startHealth) {
        super(theGame);
        this.impactDamage = impactDamage;
        this.theGame = theGame;
        setMaxHealth(startHealth);
        setHealth(startHealth);
    }

    /**
     * The friendlyness of an Enemy. The answer is false
     * The enemy is the bad-guy and is unfriendly so this function will return false
     * @return false
     */
    @Override
    public boolean isFriendly() {
        return false;
    }

    /**
     * This method is called autmaticly when the enemy dies
     * Here the enemy is removed from the game
     */
    @Override
    public void onDie() {
        theGame.deleteGameObject(this);
        theGame.addGameObject(new Explosion((int)getCenterX(), (int)getCenterY(), 60));
    }

    /**
     * If an enemy hits an other CollidableGameObject like a Player, it might interact
     * Here is the interaction between enemy and {@link ica.sugarwars.Player} handled
     * The player will suffer damage and the enemy will die
     * @param collidingObj The colliding object
     */
    @Override
    public void collisionOccurred(CollidableGameObject collidingObj) {
        if (collidingObj instanceof Player) {
            Player player = (Player) collidingObj;
            player.setHealth(player.getHealth() - this.getImpactDamage());
            onDie();
        }
    }

    /**
     * Gets the impact damage against players of this Enemy
     * @return The impact damage
     */
    public int getImpactDamage() {
        return impactDamage;
    }

    /**
     * If the enemy goes offscreen, the enemy dies
     */
    @Override
    public void outsideWorld() {
        theGame.deleteGameObject(this);
    }
}

