package ica.sugarwars.spawners;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ica.sugarwars.Player;
import ica.sugarwars.Spawner;
import ica.sugarwars.Enemy;
import ica.sugarwars.ReflectUtil;
import ica.sugarwars.SugarWars;
import ica.sugarwars.enemies.CookieEnemy;
import ica.sugarwars.enemies.DeepfryerEnemy;
import ica.sugarwars.enemies.DoritoEnemy;
import ica.sugarwars.enemies.DroprollEnemy;
import ica.sugarwars.enemies.EnergydrinkEnemy;

/**
 * @author Sijmen
 */
public class EnemySpawner extends Spawner {

    /**
     * A list with all available enemy classes.
     */
    public static final ArrayList<Class<? extends Enemy>> avalableEnemys = new ArrayList<>();

    /**
     * Here is the last enemy of every level saved. A new level is started when
     */
    private Enemy lastEnemy = null;

    //init a list of all enemy's. arrr.
    static{
        avalableEnemys.add(CookieEnemy.class);
        avalableEnemys.add(DeepfryerEnemy.class);
        avalableEnemys.add(DoritoEnemy.class);
        avalableEnemys.add(DroprollEnemy.class);
        avalableEnemys.add(EnergydrinkEnemy.class);
    }

    /**
     * This list shows the current level with current execution.
     * @see {@link SugarWars#levels}
     */
    private ArrayList<String> waveItems = new ArrayList<>();

    /**
     * Creates a new EnemySpawner.
     * After 1 tick the game will start and the first enemy's will be spawn.
     * @param theGame the game where to spawn the enemy's.
     */
    public EnemySpawner(SugarWars theGame){
        super(theGame);
        initLevel(theGame.getLevel());
    }


    /**
     * Initializes a new level to the Game.
     *
     * @param newLevel the level to load.
     * @return whether or not the level was loaded.
     */
    public boolean initLevel(int newLevel){
        newLevel--;
        if(newLevel >= SugarWars.levels.length || newLevel < 0) {
            Log.e(SugarWars.class.getName(), "Tryed to init a level that does not exist: " + newLevel);
            return false;
        }
        waveItems.clear();
        waveItems.addAll(Arrays.asList(SugarWars.levels[newLevel]));

        Player player = (Player)theGame.getPlayer();
        player.setHealth(player.getMaxHealth());
        return true;
    }

    /**
     * Create a new enemy from its name
     * @param name The name including the package name.
     * @return The newly created enemy or null when failed.
     */
    private Enemy createNewEnemy(String name){
        for(Class<? extends Enemy> enemyClass : avalableEnemys){
            if(enemyClass.getName().equals(name)){
                Object newInstance =  ReflectUtil.createInstanceWhereArgIsSugarWars(enemyClass, theGame);
                if(! (newInstance instanceof  Enemy)) {
                    Log.e(SugarWars.class.getName(), "Could not laod enemy " + name + " because its not a enemy.");
                    return null;
                }
                return (Enemy) newInstance;
            }
        }
        Log.e(SugarWars.class.getName(), "Could not laod enemy " + name + " because not found.");
        return null;
    }


    /**
     * Spawn the next enemy or do the next waiting step.
     * In shot: executes the next step inside waveItems list. When no more items,
     * it wil call {@link #initLevel(int)}
     */
    @Override
    public void spawn() {
        if(waveItems.size() <= 0) {
            //wait until the last enemy is dead.
            if(lastEnemy != null){
                if(lastEnemy.isActive()){
                    spawnAlarm.setTime(1);
                    spawnAlarm.restartAlarm();
                    return;
                }else{
                    lastEnemy = null;
                }
            }

            theGame.setLevel(theGame.getLevel()+1);
            if(!initLevel(theGame.getLevel())){
                theGame.setLevel(1);
                initLevel(theGame.getLevel());
                return;
            }
        }

        String curWaveItem = waveItems.remove(0);


        if(curWaveItem.endsWith("t")){
            String time = curWaveItem.substring(0, curWaveItem.length() - 1);
            spawnAlarm.setTime(Integer.valueOf(time));
        }else{
            int amount;
            String name;

            Pattern pattern = Pattern.compile("([0-9]+)([A-Za-z]+)");
            Matcher matcher = pattern.matcher(curWaveItem);
            if(matcher.matches()){
                amount = Integer.parseInt(matcher.group(1));
                name = matcher.group(2);
            }else{
                name = curWaveItem;
                amount = 1;
            }
            for(int i = 0; i < amount; i++){
                Enemy newEnemy = createNewEnemy("ica.sugarwars.enemies." + name);
                if(newEnemy == null){
                    Log.e(SugarWars.class.getName(), "Could not load enemy " + curWaveItem + " because creator returned null.");
                    return;
                }
                Log.d(SugarWars.class.getName(), "Loaded enemy " + newEnemy.getClass().getSimpleName());
                if(waveItems.size() == 0)
                    lastEnemy = newEnemy;
                theGame.addGameObject(newEnemy);
            }
            spawnAlarm.setTime(0);
        }
        spawnAlarm.restartAlarm();
    }
}
