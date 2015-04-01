package ica.sugarwars;

import android.gameengine.icadroids.input.OnScreenButtons;
import android.view.View;

import ica.sugarwars.guns.BananaGun;

/**
 * @author Sijmen
 */
public class Player extends Entity{

    /**
     * The current gun of the player. This is where the player
     * shoots with
     */
    private Gun gun;

    /**
     * The x speed of the player. When the button is pressed, this is the speed that the player is set to.
     */
    private static final int XSPEED = 20;

    /**
     * Initialezes a new Player
     * @param theGame the game where this {@link Player} is the player.
     */
    public Player(SugarWars theGame){
        super(theGame);
        this.maxHealth = 700;
        setHealth(700);

        this.gun = new BananaGun(theGame);
        this.theGame = theGame;
        this.setSprite("winkelwagen");
        this.setStartPosition(theGame.getMapWidth()/2-getFrameWidth()/2, theGame.getMapHeight()-100-this.getSprite().getFrameHeight());
        jumpToStartPosition();
    }

    /**
     * Every Player update, input should be read and handled. This happens here.
     */
    @Override
    public void update() {
        super.update();
        if (OnScreenButtons.buttonA == true){
            this.gun.fire(this);
        }
        if(OnScreenButtons.dPadLeft == true){
            this.setxSpeed(-XSPEED);
        }else if(OnScreenButtons.dPadRight == true){
            this.setxSpeed(XSPEED);
        }else{
            this.setxSpeed(0);
        }

        if((getCenterX() <= 0 && getxSpeed() < 0) || (getCenterX() >= theGame.getMapWidth() && getxSpeed() > 0))
            setxSpeed(0);


    }

    /**
     * When the player dies, the game should end. This is implemented here.
     */
    @Override
    public void onDie() {
        theGame.deleteGameObject(this);
        clearActive();
        theGame.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                theGame.gameOverOverlay.setVisibility(View.VISIBLE);
                try {
                    //yes, this is ugly, but could not find an other solution.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                theGame.pause();
            }
        });

    }

    /**
     * colisions with {@link Enemy} is handled in the Enemy collision event.
     * collision with {@link Projectile} is handeled in the Projectile collision event.
     * collision with {@link PowerUp} is handeled in the PowerUp collision event.
     * This method is empty because everything is handled by other events.
     * @param collidingObj The ignored argument ;)
     */
    @Override
    public void collisionOccurred(CollidableGameObject collidingObj) {    }

    /**
     * A player is friendly to a player. How obvieus. XD
     * @return true
     */
    @Override
    public boolean isFriendly() {
        return true;
    }

    /**
     * Sets the gun of the player.
     * @param gun The gun.
     */
    public void setGun(Gun gun) {
        this.gun = gun;
    }
}
