package ica.sugarwars.guns;

import android.gameengine.icadroids.objects.MoveableGameObject;

import ica.sugarwars.Gun;
import ica.sugarwars.Projectile;
import ica.sugarwars.SugarWars;

/**
 * @author Stefan Mudde
 * Gun characteristics: Normal damage, normal speed and normal shooting interval.
 */
public class HamburgerGun extends Gun {

    private static final int ySpeed = 10;
    private static final int shootInterval = 50;
    private static final int damage = 50;

    /**
     *
     * @param game sets the game this gun will be in
     * Cooldown is handeled by the shooter
     */
    public HamburgerGun(SugarWars game) {
        super(game, shootInterval);
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
        Projectile fire = new Projectile(damage, false);
        fire.setSprite("hamburger");
        fire.setySpeed(ySpeed);
        theGame.addGameObject(fire, (int)shooter.getCenterX(), (int)shooter.getCenterY());
        this.startCooldown();

    }

}
