package ica.sugarwars;

import android.GameAPI.ICA_DROID.R;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.gameengine.icadroids.dashboard.DashboardTextView;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.input.MotionSensor;
import android.gameengine.icadroids.input.OnScreenButton;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.objects.MoveableGameObject;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import ica.sugarwars.spawners.EnemySpawner;
import ica.sugarwars.spawners.PowerupSpawner;

/**
 * @author Sijmen
 */
public class SugarWars extends GameEngine{

    /**
     * All enemys in all levels. This is how it works:
     * Every array in this array is a level. Starting with level 1.
     * There are two types of thins.
     *   1. Enemys  The name of the enemy with optionally a number.
     *              This number sais how many enemys are spawn.
     *   2. time    a number with 't' after it. This number tells how much ticks to wait.
     *
     *   Everything is executed from left to right. So in this example:
     *    "2DroprollEnemy", "60t", "CookieEnemy"
     *   There will be spawned 2 DroprollEnemy, then 60 ticks waited, and then a CookieEnemy is spawn.
     */
    public static String[][] levels = new String[][]{
            {"DoritoEnemy", "120t", "2DroprollEnemy", "60t", "CookieEnemy", "60t", "5CookieEnemy", "EnergydrinkEnemy", "300t", "DeepfryerEnemy"},
            {"EnergydrinkEnemy", "180t", "3DoritoEnemy", "120t", "2CookieEnemy", "60t", "3DroprollEnemy", "EnergydrinkEnemy", "300t", "DeepfryerEnemy"},
            {"4CookieEnemy", "180t", "2DroprollEnemy", "120t", "5CookieEnemy", "60t", "3DoritoEnemy", "EnergydrinkEnemy", "300t", "DeepfryerEnemy"},
            {"2DroprollEnemy", "180t", "2DoritoEnemy", "80t", "EnergydrinkEnemy", "60t", "DoritoEnemy", "EnergydrinkEnemy", "300t", "DeepfryerEnemy"},
            {"DeepfryerEnemy", "200t", "EnergydrinkEnemy", "80t", "5CookieEnemy", "60t", "2DroprollEnemy", "DoritoEnemy", "300t", "DeepfryerEnemy"},
            {"DeepfryerEnemy", "200t", "EnergydrinkEnemy", "80t", "DeepfryerEnemy", "60t", "DoritoEnemy", "CookieEnemy", "100t", "DeepfryerEnemy"}
    };

    /**
     * The spawner of the enemy's.
     */
    EnemySpawner enemySpawner;

    /**
     * The spawner of the powerup's.
     */
    PowerupSpawner powerupSpawner;

    /**
     * The current level. Starting at 1 counting up. Updated by the {@link ica.sugarwars.spawners.EnemySpawner}
     */
    private int level = 1;

    /**
     * The current score in the game.
     */
    private int score = 0;

    /**
     * The dashboard of the game. This is a two line TextView that shows
     * the current level and score.
     */
    TextView dashboard;

    /**
     * The overlay that holds 'Game Over' text. This overlay always exists and is
     * always invisable.
     */
    TextView gameOverOverlay;

    /**
     * Initializes the game.
     */
    @Override
    protected void initialize() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setScreenLandscape(false);

        TouchInput.use = false;
        MotionSensor.use = false;
        OnScreenButtons.use = true;

        Player player = new Player(this);
        setPlayer(player);
        addGameObject(player);

        setBackground("gamebackground");

        enemySpawner = new EnemySpawner(this);
        powerupSpawner = new PowerupSpawner(this);

    }

    /**
     * {@link #dashboard} and {@link #gameOverOverlay} can only be initialized after
     * the OnScreenButtons are initialized. Thats why we do initializing after the game initalization.
     */
    @Override
    protected void initializeGameEngine(){
        super.initializeGameEngine();
        dashboard = (TextView) OnScreenButtons.getOnScreenButtonsView().findViewById(R.id.scoreTxt);
        gameOverOverlay = (TextView) OnScreenButtons.getOnScreenButtonsView().findViewById(R.id.gameOverTxt);
    }

    @Override
    public void update() {
        if(dashboard != null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dashboard.setText("Level: " + getLevel() + "\nScore: " + getScore());
                }
            });
        }

    }
    /**
     * Should only do calculateOutsideWorld when full object is outside field.
     * @param go The object to check.
     */
    @Override
    protected void calculateOutsideWorld(GameObject go) {
        if (go instanceof MoveableGameObject) {
            if (!go.isActive()) {
                return;
            }
            if (go.getX() + go.getFrameWidth() < 0 || go.getX() > getMapWidth()
                    || go.getY() + go.getFrameHeight() < 0
                    || go.getY() > getMapHeight()) {
                ((MoveableGameObject) go).outsideWorld();
            }
        }
    }

    /**
     * Get the current level
     * @return the current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the current level
     * @param level the new level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Set the score of this game
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get the score of this game
     * @return the current score
     */
    public int getScore() {
        return score;
    }
}
