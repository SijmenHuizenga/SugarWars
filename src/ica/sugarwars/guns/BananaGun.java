package ica.sugarwars.guns;

import android.gameengine.icadroids.objects.MoveableGameObject;

import ica.sugarwars.Gun;
import ica.sugarwars.Projectile;
import ica.sugarwars.SugarWars;

/**
 * @author Stefan Mudde
 * Gun characteristics: average damage, average speed and average shooting interval.
 */

public class BananaGun extends Gun {

    private static final int cooldown = 20;
    private static final int speed = -20;
    private static final int damage = 40;

    /**
     *
     * @param game sets the game this gun will be in
     */
    public BananaGun(SugarWars game) {
        super(game, cooldown);
    }

    /**
     * Shoots gun and sets up projectiles
     * @param shooter owner of the gun.
     */
    @Override
    public void fire(MoveableGameObject shooter){
        if(this.isInCooldown())
            return;
        Projectile fire = new Projectile(damage, true);
        fire.setSprite("banaan");
        theGame.addGameObject(fire, (int)shooter.getCenterX(), shooter.getY());
        fire.setySpeed(speed);
        this.startCooldown();
    }
}
