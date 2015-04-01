package ica.sugarwars;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;

import ica.sugarwars.SugarWars;

/**
 * @author Sijmen
 */
public abstract class Spawner implements IAlarm {

    /**
     * The game. Used to tell where to spawn new things.
     */
    protected SugarWars theGame;

    /**
     * The alarm to measure time between spawning items.
     * Whe timer is triggered, it will call {@link #spawn()}
     */
    protected Alarm spawnAlarm;

    /**
     * Create a new Spawner. We do need the game to know
     * where to spawn stuff.
     * @param mightyParent The game.
     */
    public Spawner(SugarWars mightyParent){
        this.theGame = mightyParent;
        spawnAlarm = new Alarm(0, 1, this);
        spawnAlarm.startAlarm();
    }

    /**
     * Called when {@link #spawnAlarm} is triggered.
     */
    public abstract void spawn();

    /**
     * We are called when triggered.
     * @param alarmID The id. Typically this is 0.
     */
    @Override
    public void triggerAlarm(int alarmID) {
        if(alarmID == 0)
            spawn();
    }
}
