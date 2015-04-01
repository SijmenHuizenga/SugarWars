package ica.sugarwars.enemies;

import java.util.Random;

import ica.sugarwars.Enemy;
import ica.sugarwars.Gun;
import ica.sugarwars.SugarWars;
import ica.sugarwars.guns.DropGun;

/**
 * @author Stefan Mudde
 * Moves slowly, average health, shoots drop, high impact damage.
 */
public class DroprollEnemy extends Enemy {

    private Random random = new Random();
    private Gun gun;
    private static final int addScore = 100;
    private static final int impactDamage = 80;
    private static final int health = 50;
    private static final int ySpeed = 2;
    private static final int xSpeed = 2;
    private static boolean leftOrRight = true;

    /**
     * Initializes an droprollenemy into enemy
     * Uses local parameters to properly place the enemy
     * @param theGame Sets the enemy into the current game
     * If there are two enemy's spawned, they are placed left and right
     */
    public DroprollEnemy(SugarWars theGame) {
        super(theGame, impactDamage, health);
        gun = new DropGun(theGame);
        setSprite("droprol");

        int x, xv;

        if(leftOrRight){
            x = 0;
            xv = xSpeed;
        }else{
            x = theGame.getMapWidth()-getFrameWidth();
            xv = -1* xSpeed;
        }
        leftOrRight = !leftOrRight;
        setxSpeed(xv);
        setySpeed(ySpeed);
        setStartPosition(x, 0);
        jumpToStartPosition();
    }

    /**
     * Updates enemy
     * Fires the gun, and the gun has a cooldown
     */
    @Override
    public void update(){
        super.update();
        gun.fire(this);
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
