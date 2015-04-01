package ica.sugarwars.guns;

import android.gameengine.icadroids.objects.MoveableGameObject;

import ica.sugarwars.Gun;
import ica.sugarwars.Projectile;
import ica.sugarwars.SugarWars;

/**
 * @author Stefan Mudde
 * Gun characteristics: Low damage, high speed and low shooting interval.
 */
public class EggGun extends Gun {

    private static final int cooldown = 10;
    private static final int speed = -50;
    private static final int damage = 8;
    private static final int xSpeed = 7;

    /**
     *
     * @param game sets the game this gun will be in
     */
    public EggGun(SugarWars game) {
        super(game, cooldown);
    }

    /**
     * Shoots gun and sets up projectiles
     * @param shooter owner of the gun
     * Shoots three projectiles spreading out
     */
    @Override
    public void fire(MoveableGameObject shooter){
        if(this.isInCooldown())
            return;

        for(int i = -1; i <= 1; i++){  //-1, 0, 1
            Projectile fire = new Projectile(damage, true);
            fire.setSprite("ei");
            fire.setxSpeed(i*xSpeed);
            fire.setySpeed(speed);
            int center = (int)shooter.getCenterX()-fire.getFrameWidth()/2;
            theGame.addGameObject(fire,center, shooter.getY());
        }
        this.startCooldown();
    }

}
