package ica.sugarwars.enemies;

import ica.sugarwars.Enemy;
import ica.sugarwars.Gun;
import ica.sugarwars.SugarWars;
import ica.sugarwars.guns.HamburgerGun;

/**
 * @author Stefan Mudde
 * Enemy characteristics: Lots of health, shoots hamburgers.
 */
public class DeepfryerEnemy extends Enemy {

    private Gun gun;
    private static final int xSpeed = 10;
    private static final int addScore = 500;
    private static final int health = 700;
    private static final int impactDamage = 3000;

    /**
     *
     * Initializes an deepfryerenemy into enemy
     * Uses local parameters to properly place the enemy
     * @param theGame Sets the enemy into the current game
     */
    public DeepfryerEnemy(SugarWars theGame) {
        super(theGame, impactDamage, health);
        this.gun = new HamburgerGun(theGame);
        setStartPosition(theGame.getMapWidth() / 2, getSprite().getFrameHeight());
        jumpToStartPosition();
        setSprite("frituurpan");
        setxSpeed(xSpeed);
    }

    /**
     * Function used to keep track of this enemy's movement
     * The enemy moves from the left to the right of the screen and back again
     * Also fires projectiles from it's gun
     */
    public void update(){
        super.update();
        if(getX() <= 0 ){
            setxSpeed(xSpeed);
        }else if(getX() >= theGame.getMapWidth() - getFrameWidth()){
            setxSpeed( -1 * xSpeed);
        }
        this.gun.fire(this);
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