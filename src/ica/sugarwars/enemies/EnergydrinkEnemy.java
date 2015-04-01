package ica.sugarwars.enemies;

import ica.sugarwars.CollidableGameObject;
import ica.sugarwars.Enemy;
import ica.sugarwars.Player;
import ica.sugarwars.SugarWars;

/**
 * @author Stefan Mudde
 * Lots of health, low speed en very high impact damage. If shot down player gets 150 health back.
 */
public class EnergydrinkEnemy extends Enemy {

    private static final int healthbonus = 150;
    private static final int addscore = 200;
    private static final int ySpeed = 5;
    private static final int health = 75;
    private static final int impactDamage = 80;

    /**
     * Initializes an energydrinkenemy into enemy
     * Uses local parameters to properly place the enemy
     * @param theGame Sets the enemy into the current game
     */
    public EnergydrinkEnemy(SugarWars theGame) {
        super(theGame, impactDamage, health);
        setStartPosition(theGame.getMapWidth()/2, 0-getSprite().getFrameHeight());
        jumpToStartPosition();
        setySpeed(ySpeed);
        setSprite("energydrink");
    }

    /**
     * If this enemy runs out of health, the enemy dies
     * A score is also added to the current score
     * Adds an healthbonus to the player
     */
    @Override
    public void onDie() {
        super.onDie();
        Player player = (Player)theGame.getPlayer();
        theGame.setScore(theGame.getScore()+ addscore);
        player.setHealth(player.getHealth() + healthbonus);
    }

    /**
     *
     * @param collidingObj The colliding object.
     * If the player destroys the energydrink, a healthboost is applied. But the same function is called if the energydrink collides with the player
     * To stop a player from gaining health after collision, we implented collisionOccurred in override without onDie to prevent this from happening
     */
    @Override
    public void collisionOccurred(CollidableGameObject collidingObj) {
        if (collidingObj instanceof Player) {
            Player player = (Player) collidingObj;
            player.setHealth(player.getHealth() - this.getImpactDamage());
            super.onDie();
        }
    }

}
