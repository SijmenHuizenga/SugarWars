package ica.sugarwars.spawners;

import java.util.HashMap;
import java.util.Random;

import ica.sugarwars.Spawner;
import ica.sugarwars.Gun;
import ica.sugarwars.PowerUp;
import ica.sugarwars.ReflectUtil;
import ica.sugarwars.SugarWars;
import ica.sugarwars.guns.BananaGun;
import ica.sugarwars.guns.EggGun;
import ica.sugarwars.guns.YakultGun;

/**
 * @author Sijmen
 */
public class PowerupSpawner extends Spawner {

    private final static int TIME_BETWEEN_SPAWNS_MIN = 500;
    private final static int TIME_BETWEEN_SPAWNS_MAX = 900;

    private final static int SPEED_X_MIN = 3;
    private final static int SPEED_X_MAX = 7;

    /**
     * When, what and where will the next powerup be spawned?
     */
    private Random random = new Random();

    /**
     * A list with all available gun classes.
     */
    public static HashMap<Class<? extends Gun>, String> avalableGuns = new HashMap<>();

    //init a list of all Guns.
    static{
        avalableGuns.put(BananaGun.class, "banaanpowerup");
        avalableGuns.put(EggGun.class, "eipowerup");
        avalableGuns.put(YakultGun.class, "yakultpowerup");
    }

    /**
     * Creates a new PowerupSpawner
     * @param theGame The game where the powerup spawner should spawn guns.
     */
    public PowerupSpawner(SugarWars theGame) {
        super(theGame);
        resetRandomTimer();
    }

    /**
     * Cool code :D
     * Creates a new random powerup at random location.
     */
    @Override
    public void spawn() {
        //random key from hashmap. Why so hard java?! Feature Request! :)
        Class<? extends Gun> gunClass = (Class<? extends Gun>)avalableGuns.keySet().toArray()[(random.nextInt(avalableGuns.size()))];
        Gun newGun = (Gun) ReflectUtil.createInstanceWhereArgIsSugarWars(gunClass, theGame);

        PowerUp powerup = new PowerUp(newGun);
        powerup.setSprite(avalableGuns.get(gunClass));
        Math.random();
        int startX = random.nextInt(theGame.getMapWidth()-powerup.getFrameWidth());
        powerup.setStartPosition(startX, -powerup.getFrameHeight());
        powerup.jumpToStartPosition();
        powerup.setySpeed(random.nextInt((SPEED_X_MAX - SPEED_X_MIN) + 1) + SPEED_X_MIN);

        theGame.addGameObject(powerup);
        resetRandomTimer();
    }

    /**
     * Reset the timer to a random number between {@link #TIME_BETWEEN_SPAWNS_MIN} and {@link #TIME_BETWEEN_SPAWNS_MAX}.
     */
    private void resetRandomTimer(){
        spawnAlarm.setTime(random.nextInt((TIME_BETWEEN_SPAWNS_MAX - TIME_BETWEEN_SPAWNS_MIN) + 1) + TIME_BETWEEN_SPAWNS_MIN);
        spawnAlarm.restartAlarm();
    }
}
