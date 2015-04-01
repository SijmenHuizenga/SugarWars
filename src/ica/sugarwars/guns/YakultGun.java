package ica.sugarwars.guns;

import android.gameengine.icadroids.objects.MoveableGameObject;

import ica.sugarwars.Gun;
import ica.sugarwars.Projectile;
import ica.sugarwars.SugarWars;

/**
 * @author Stefan Mudde
 * Gun characteristics: High damage, low speed and high shooting interval.
 */
public class YakultGun extends Gun {

    private static final int cooldown = 35;
    private static final int speed = -30;
    private static final int damage = 60;

    /**
     *
     * @param game sets the game this gun will be in
     * Sets cooldown
     */
    public YakultGun(SugarWars game) {
        super(game, cooldown);
    }

    /**
     * Shoots gun and sets up projectiles
     * @param shooter owner of the gun
     * Shoots one projectile with interval
     */
    @Override
    public void fire(MoveableGameObject shooter){
        if(this.isInCooldown())
            return;
        Projectile fire = new Projectile(damage, true);
        fire.setSprite("yakult");
        theGame.addGameObject(fire, (int)shooter.getCenterX(), shooter.getY());
        fire.setySpeed(speed);
        this.startCooldown();
    }

}
