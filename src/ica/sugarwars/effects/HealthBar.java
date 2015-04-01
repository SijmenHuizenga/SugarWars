package ica.sugarwars.effects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * @author Sijmen
 */
public class HealthBar {

    /**
     * The default height of a health bar.
     */
    public static final int DEFAULT_WIDTH = 150;

    /**
     * The default height of a health bar.
     */
    public static final int DEFAULT_HEIGHT = 50;

    /**
     * The default color of a health bar.
     */
    public static final int DEFAULT_COLOR = Color.RED;

    /**
     * Left top position of the HealthBar
     */
    private Point leftTop;

    /**
     * The width of the HealthBar
     */
    private int width;

    /**
     * The height of the HealthBar
     */
    private int height;

    /**
     * The color of the health bar
     */
    private int color;

    /**
     * Constructs a new HealthBar. Everything will be initalized with default values.
     * @see {@link ica.sugarwars.effects.HealthBar#DEFAULT_COLOR}
     * @see {@link ica.sugarwars.effects.HealthBar#DEFAULT_HEIGHT}
     * @see {@link ica.sugarwars.effects.HealthBar#DEFAULT_WIDTH}
     */
    public HealthBar(){
        this.leftTop = new Point(0, 0);
        this.color = DEFAULT_COLOR;
        this.height = DEFAULT_HEIGHT;
        this.width = DEFAULT_WIDTH;
    }

    /**
     * Draws the healthBar on the {@link Canvas}
     * @param canvas the canvas to draw upon.
     * @param persentage The persentage of the healthbar that is filled.
     */
    public void draw(Canvas canvas, int persentage){
        Paint paint = new Paint();
        paint.setColor(this.color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        canvas.drawRect(leftTop.x, leftTop.y, leftTop.x+this.width, leftTop.y+this.height, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(leftTop.x, leftTop.y, leftTop.x+(int)(((float)this.width)/100f*persentage), leftTop.y+this.height, paint);
    }

    /**
     * Set the x location
     * @param x the x in pixels
     */
    public void setX(int x) {
        leftTop.x = x;
    }

    /**
     * Set the y location
     * @param y the y in pixels
     */
    public void setY(int y) {
        leftTop.y = y;
    }

    /**
     * Get the X
     * @return the x location
     */
    public int getX() {
        return leftTop.x;
    }

    /**
     * Get the Y
     * @return the y location in pixels
     */
    public int getY() {
        return leftTop.y;
    }

    /**
     * Set the width location
     * @param width the width in pixels
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Set the height location
     * @param height the height in pixels
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get the width
     * @return the width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the heighht
     * @return height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the color location
     * @param color the color
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Get the color of the HealthBar
     * @return the color.
     */
    public int getColor() {
        return color;
    }
}