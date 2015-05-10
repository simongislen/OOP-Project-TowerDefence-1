package edu.chl.proximity.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Utilities.PointCalculations;
import java.lang.CloneNotSupportedException;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-02
 *
 * An object that can be placed on the board.
 *
 * ---
 * 02/04 modified by Simon Gislén. Added properties.
 * 08/04 modified by Linda Evaldsson. Getters for width and height created.
 * 23/04 modified Simon Gislén. Added empty constructor.
>*/
public abstract class BoardObject implements Cloneable {
    /**
     * Position on the gameboard
     */
    private ProximityVector position;
    /**
     * Image that is to be rendered
     */
    private edu.chl.proximity.Models.Utils.Image image;
    /**
     * Rotation property
     */
    private double angle;
    //The map that the board object is placed on
    private Map map;

    private int width = 0;
    private int height = 0;


    /**
     * create a new board object
     * @param position where the object will be created
     * @param img the image of the object
     * @param angle the rotation of the object (in degrees)
     */
    public BoardObject(Map map, ProximityVector position, edu.chl.proximity.Models.Utils.Image img, double angle){
        this.position = position;
        this.image = img;
        this.map = map;
        this.angle = angle;
        if(img != null) {
            width = img.getTexture().getWidth();
            height = img.getTexture().getHeight();
        }
    }
    public BoardObject() {
        //Empty constructor for subclasses
    }

    public BoardObject(Map map, ProximityVector position, edu.chl.proximity.Models.Utils.Image img, double angle, int width, int height) {
        this(map, position, img, angle);
        this.width = width;
        this.height = height;
    }

    public Map getMap() { return map; }
    public void setMap(Map map) {this.map = map;}

    //Getters and Setters
    public ProximityVector getPosition() {
        return position;
    }

    public void setPosition(ProximityVector position) {
        this.position = position;
    }

    public edu.chl.proximity.Models.Utils.Image getImage() {
        return image;
    }

    public void setImage(edu.chl.proximity.Models.Utils.Image img) {
        this.image = img;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void rotate(double a) {
        angle += a;
    }
    public void render(SpriteBatch batch) {
        if(image != null) {
            image.render(batch, position, angle);
        }
    }

    public void renderShapes(ShapeRenderer shapeRenderer) {

    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * checks whether bullet has traveled outside the frame
     * x-y val
     *
     * @return true if not visible
     */
    public boolean isOutsideView() {
        //1280 = game width
        //720 = game height
        if (position == null){
            return true;
        }
        if (getCenter().x <= 1280 && getCenter().y <= 720 && getCenter().x >= 0 && getCenter().y >= 0) {
            return false;
        }
        return true;
    }

    /**
     * re-adjusts the projectiles angle to face the given point
     *
     * @param v what point the projectile should travel towards
     */
    public void faceTarget(ProximityVector v) {
        if (v != null) {
            angle = (PointCalculations.getVectorAngle(getCenter(), v));
            //angle remains unchanged, if there's no new point to look at.
        }
    }

    /**
     * Gets the center of this object
     * @return the vector in center of this BoardObject
     */
    public ProximityVector getCenter() {
        return new ProximityVector(this.getPosition().x+(this.getWidth()/2), this.getPosition().y + (this.getHeight()/2));
    }

    public void setCenter(ProximityVector pos) {
        setPosition(new ProximityVector(pos.x - this.getWidth()/2, pos.y - this.getHeight()/2));
    }

    public boolean containsPoint(ProximityVector point) {
        return PointCalculations.isPointInObject(point, this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        BoardObject clone = (BoardObject)super.clone();
        clone.setPosition(new ProximityVector(position.x, position.y));
        if (image != null){
            clone.setImage((edu.chl.proximity.Models.Utils.Image)image.clone());
        }

        return clone;
    }
}
