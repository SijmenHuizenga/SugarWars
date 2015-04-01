package ica.sugarwars.enemies;

import java.util.Random;

import ica.sugarwars.Enemy;
import ica.sugarwars.SugarWars;

/**
 * @author Stefan Mudde
 * Enemy characteristics: average health, speed and damage
 */
public class CookieEnemy extends Enemy {

    private static Random random = new Random();
    private static final int xMax = 4, xMin = 1;
    private static final int health = 30;
    private static final int impactDamage = 30;
    private static final int ySpeed = 10;
    private static final int addScore = 75;
    private boolean leftToRight;

    /**
     *
     * Initializes an cookieenemy into enemy
     * Uses local parameters to properly place the enemy
     * @param theGame Sets the enemy into the current game
     */
    public CookieEnemy(SugarWars theGame) {

        super(theGame, impactDamage, health);
        this.leftToRight = random.nextBoolean();
        int xSpeed = random.nextInt((xMax - xMin) + 1) + xMin;
        setStartPosition(theGame.getMapWidth()/2, 0-getSprite().getFrameHeight());
        jumpToStartPosition();
        setxSpeed(leftToRight ?  xSpeed : -1*xSpeed);
        setySpeed(ySpeed);
        setSprite("cookie");
    }

    /**
     *
     * If this enemy runs out of health, the enemy dies
     * A score is also added to the current score
     */
    @Override
    public void onDie() {
        theGame.setScore(theGame.getScore()+ addScore);
        super.onDie();
    }

}

