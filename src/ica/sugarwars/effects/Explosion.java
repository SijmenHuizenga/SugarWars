package ica.sugarwars.effects;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.gameengine.icadroids.objects.GameObject;

/**
 * @author Sijmen
 */
public class Explosion extends GameObject implements IAlarm{

    /**
     * The total time this that this explosion is shown
     */
    private int time;

    /**
     * The amount of frames in the explosion.png
     */
    private static final int EXPLOSION_FRAME_AMOUNT = 7;

    public Explosion(int x, int y, int time){
        this.time = time;

        this.setSprite("explosion", EXPLOSION_FRAME_AMOUNT);

        this.setStartPosition(x - getFrameWidth()/2, y - getFrameHeight()/2);
        this.jumpToStartPosition();

        this.getSprite().setAnimationSpeed(getTime()/EXPLOSION_FRAME_AMOUNT);
        this.getSprite().startAnimate();

        Alarm alarm = new Alarm(123, getTime(), this);
        alarm.startAlarm();
    }

    /**
     * On alarm trigger, should clear this explosion from the game.
     * @param alarmID
     */
    @Override
    public void triggerAlarm(int alarmID) {
        clearActive();
    }

    /**
     * Get the time that this explosion is shown.
     * @return the time in ticks
     */
    public int getTime() {
        return time;
    }

    /**
     * Set the time that this explosion is shown.
     * @param time The time in ticks
     */
    public void setTime(int time) {
        this.time = time;
    }

}
