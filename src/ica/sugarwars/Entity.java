package ica.sugarwars;

import android.graphics.Canvas;
import android.util.Log;

import ica.sugarwars.effects.HealthBar;

/**
 * @author Sijmen
 */
public abstract class Entity extends CollidableGameObject {

    /**
     * The health points of the entity.
     */
    private int health;

    /**
     * Default a entity has a maximum of 100 health points.
     * There might be entitys with more ore less max health.
     */
    protected int maxHealth = 100;

    /**
     * The game where this enemy is angry.
     */
    protected SugarWars theGame;

    /**
     * The healthbar of this enemy. To disable set healthBar to null.
     */
    protected HealthBar healthBar;

    /**
     * constructor to create a new Entity
     * @param theGame The game where this enemy lives.
     */
    public Entity(SugarWars theGame){
        this.theGame = theGame;
        this.healthBar = new HealthBar();
    }

    /**
     * Set the health of the entithy. If health si below 0, the entity will die.
     * If health above the max it will show an error and set the health to maxhealth.
     * @param hp The new health.
     */
    public void setHealth(int hp){
        health = hp;
        if(health <= 0) {
            health = 0;
            onDie();
        }
        if(health > maxHealth){
            health = maxHealth;
            Log.e(SugarWars.class.getName(), "Tryed to set health of " + this.toString() +
                    " above maximum. max: " + maxHealth + " given: " + hp);
        }
    }

    /**
     * Set the maximum health of this entity
     * @param maxHealth the maximum health
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Get the maximum healht of this entity
     * @return the maximum health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Draws custom stuff on the screen.
     */
    @Override
    public void drawCustomObjects(Canvas canvas) {
        if(healthBar == null)
            return;
        healthBar.setX(getX());
        healthBar.setY(getY());
        healthBar.draw(canvas, (int)(health/(float)maxHealth*100));
    }

    /**
     * Get the current health points. This value is never below 0 or above {@link Entity#maxHealth}
     * @return the current health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * This method is automaticly called when health is or below 0.
     * The entity should be removed from the game.
     */
    public abstract void onDie();

    /**
     * Check if this entity is friendly to {@link Player}
     * @return wether or not this entity is friendly.
     */
    public abstract boolean isFriendly();
}
