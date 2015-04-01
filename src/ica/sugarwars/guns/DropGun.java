package ica.sugarwars.guns;

import android.gameengine.icadroids.objects.MoveableGameObject;

import ica.sugarwars.Gun;
import ica.sugarwars.Projectile;
import ica.sugarwars.SugarWars;

/**
 * @author Stefan Mudde
 * Gun characteristics: Shoots three projectiles at once. Very low damage, average speed and average shooting interval.
 */
public class DropGun extends Gun {

    private static final int cooldown = 40;
    private static final int speed = 30;
    private static final int damage = 10;

    /**
     *
     * @param game sets the game this gun will be in
     */
    public DropGun(SugarWars game) {
        super(game, cooldown);
    }

    /**
     * Shoots gun and sets up projectiles
     * @param shooter owner of the gun
     * Shoots three projectiles with x variation
     */
    @Override
    public void fire(MoveableGameObject shooter){
        int variation = 0;
        if(this.isInCooldown())
            return;
        for (int i = 0; i <3; i++){
            Projectile fire = new Projectile(damage, false);
            fire.setSprite("dropje");
            if (i == 1) {
                variation = shooter.getFrameWidth() /2*-1;
            }
            if (i == 2) {
                variation = shooter.getFrameWidth() /2 - fire.getFrameWidth()/2;
            }
            theGame.addGameObject(fire, (int)shooter.getCenterX() - fire.getFrameWidth()/2 + variation, shooter.getY());
            fire.setySpeed(speed);
        }
        this.startCooldown();
    }

}
