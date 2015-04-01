package ica.sugarwars.enemies;

import ica.sugarwars.Enemy;
import ica.sugarwars.SugarWars;

/**
 * @author Stefan Mudde
 * Enemy characteristics: Low health and damage, high speed.
 */
public class DoritoEnemy extends Enemy {

    private static final int ySpeed = 10;
    private static final int xSpeed = 50;
    private static final int addScore = 50;
    private static final int health = 5;
    private static final int impactDamage = 20;


    /**
     * Initializes an doritoenemy into enemy
     * Uses local parameters to properly place the enemy
     * @param theGame Sets the enemy into the current game
     */
    public DoritoEnemy(SugarWars theGame) {

        super(theGame, impactDamage, health);
        setStartPosition(0,0);
        jumpToStartPosition();
        setxSpeed(xSpeed);
        setySpeed(ySpeed);
        setSprite("dorito");

    }

    /**
     * Function used to keep track of this enemy's movement
     * Enemy bounces from the left to the right of the screen while moving down
     */
    @Override
    public void update(){
        super.update();
        if (this.getX() + this.getFrameWidth() >= theGame.getMapWidth()) {
            setxSpeed(xSpeed*-1);
        }
        else if (this.getX() <= 0) {
            setxSpeed(xSpeed);
        }
    }

    /**
     * If this enemy runs out of health, the enemy dies
     * A score is also added to the current score
     */
    @Override
    public void onDie() {
        theGame.setScore(theGame.getScore()+ addScore);
        super.onDie();
    }
}
