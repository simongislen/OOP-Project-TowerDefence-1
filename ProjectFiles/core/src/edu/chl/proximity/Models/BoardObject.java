package edu.chl.proximity.Models;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

/**
 * Created by Johan on 4/2/2015.
 */
public abstract class BoardObject {
    /**
     * Position on the gameboard
     */
    private Point position;
    /**
     * Image that is to be rendered
     */
    private Texture image;
    /**
     * Rotation property
     */
    private double angle;


    /**
     * create a new board object
     * @param position where the object will be created
     * @param texture the texture of the object
     * @param angle the rotation of the object (in degrees)
     */
    public BoardObject(Point position, Texture texture, double angle){
        this.position = position;
        this.image = texture;
        this.angle = angle;
    }


    //Getters and Setters
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Texture getTexture() {
        return image;
    }

    public void setTexture(Texture texture) {
        this.image = texture;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
