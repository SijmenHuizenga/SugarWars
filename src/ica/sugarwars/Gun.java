package ica.sugarwars;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.gameengine.icadroids.objects.MoveableGameObject;

/**
 * Created by Stefan Mudde on 20-3-2015.
 */
public abstract class Gun implements IAlarm {

    /**
     * The game where this gun exist
     */
    protected SugarWars theGame;

    /**
     * The clock that times the cooldown
     */
    private Alarm cooldownClock;

    /**
     * The boolean to keep track of cooldown
     */
    private boolean isInCooldown = false;

    /**
     * Initialed a new Gun
     * @param theGame the game where this gun exists
     * @param cooldown the cooldown in ms between shots
     */
    public Gun(SugarWars theGame, int cooldown) {
        this.theGame = theGame;

        cooldownClock = new Alarm(0, cooldown, this);
    }

    /**
     * This function is called when the gun will shoot. Most implementation will create
     * a new {@link ica.sugarwars.Projectile} and add the projectile to the game
     */
    public abstract void fire(MoveableGameObject shooter);

    /**
     * This will check if the gun is in cooldown mode
     *
     * @return whether or not in cooldown
     */
    protected boolean isInCooldown() {
        return isInCooldown;
    }

    /**
     * Start the cooldown. You can check if the cooldown is done
     * using {@link Gun#isInCooldown()}
     */
    protected void startCooldown(){
        cooldownClock.restartAlarm();
        isInCooldown = true;
    }

    /**
     * When overriding, call the super if you want to use build-in cooldown
     * @param alarmID id 0 is used for cooldown
     */
    @Override
    public void triggerAlarm(int alarmID) {
        if(alarmID == 0)
            isInCooldown = false;
    }
}
