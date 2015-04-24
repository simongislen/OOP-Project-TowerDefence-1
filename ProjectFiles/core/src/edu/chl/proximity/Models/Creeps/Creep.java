package edu.chl.proximity.Models.Creeps;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Paths.Path;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Utilities.PointCalculations;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Point;
import java.util.Iterator;

/**
 * @author Linda Evaldsson
 * @date 2015-04-02
 *
 * ---
 * Unknown date modified by Johan Swanberg
 *
 * 08/04 modified by Linda Evaldsson. Refactoring to Creep instead of AbstractCreep. speed-variable changed to int instead of double.
 * 16/04 modified by Simon Gislén. Added support for creep devolution.
 * 23/04 Modified by Simon. Adding resources and XP when killing creeps
 * 24/04 modified by Johan, creeps now use their center when calculating movement instead of upper left corner
 * 
 * An abstract class for creeps. Concrete creeps extends this class.
 */
public abstract class Creep extends BoardObject {

    //private Vector2 nextWayPoint;
    private int nextWayPointID;
    private double distanceToNextWayPoint;
    private Path path;
    private Sound devolveSound;
    private double speed;
    private double backUpSpeed;
    private double moveAngle;
    private double randomRotation;
    private Vector2 velocity;
    private int slowDownTime;

    /**
     * create a new creep with an image and a speed
     * @param image what image the creep should have (it will rotate a random amount automatically)
     * @param speed what speed the creep will have
     */
    public Creep(Image image, double speed) {
        super(null, image, 0);
        setupCreep(speed);
        initiateMovement();
    }

    /**
     * @param image what image the creep should have (it will rotate a random amount automatically)
     * @param speed what speed the creep will have
     * @param oldCreep The old creep from which the location on the screen is taken.
     */
    public Creep(Image image, int speed, Creep oldCreep) {
        super(oldCreep.getPosition(), image, 0);
        setupCreep(speed);
        nextWayPointID = oldCreep.nextWayPointID;
        distanceToNextWayPoint = oldCreep.distanceToNextWayPoint;

        moveAngle = getAngleToNextPoint();
    }

    //Setup method that is common to constructors
    public void setupCreep(double speed) {
        this.speed = speed;
        this.backUpSpeed = speed;

        Map map = GameData.getInstance().getMap();
        path = map.getPath();
        randomRotation = (Math.random()*15) - 7.5;
    }

    /**
     * get the number of the waypoint this creep is currently traveling towards
     * Guaranteed to be >=0
     * @return (int) the number of the waypoint the creep is traveling towards
     *
     */
    public int getNextWayPointID(){
        return nextWayPointID;
    }

    /**
     * get the distance this creep has to the next waypoing (squared because of calculation optimization)
     * This value can be negative since for one frame the creep is considered to have "passed" the waypoint,
     * and not yet chosen the next waypoint.
     * @return (double) the distance this creep has to the next waypoint
     */
    public double getDistanceToNextWayPoint(){
        return distanceToNextWayPoint;
    }

    /**
     * Give the creep the first angle & direction to the first waypoint
     */
    private void initiateMovement() {
        this.setCenter(new Vector2(path.getWaypoint(0)));
        nextWayPointID = 0;
        aimTowardsNextWaypoint();
        distanceToNextWayPoint = Double.MAX_VALUE;

    }

    /**
     * show the "poof" particleEffect that creeps do when they die
     */
    public void displayDeathEffect(){
        Map map = GameData.getInstance().getMap();
        map.getParticleManager().getCreepDiesEffect().createEffect(this.getCenter());
    }

    /**
     * destroys the creep
     */
    public void destroy() {
        Map map = GameData.getInstance().getMap();
        displayDeathEffect();
        map.getRemoveStack().add(this);
    }
    /**
     * rotate the creeps image a random amount (a creep is assigned a random rotation amount on creation)
     */
    public void rotate() {
        this.rotate(randomRotation);
    }

    /**
     * Devolve this creep, aka "kill" / remove the creep, call this method when the creep has taken fatal damage
     */
    public abstract void devolve();

    /**
     * @return Resources the player gets when the player kills the creep.
     */
    public abstract Resources getCreepResource();

    /**
     * @return XP the player gets when the player kills the creep.
     */
    public abstract int getCreepExperiencePoints();

    /**
     * move the creep based on its speed
     * The movement direction is based on what waypoint is the next
     * step for the creep, the new movement angle to the next waypoint is calculated when
     * the creep is "on" its current waypoint //implementation comment, but relevant for using method
     */
    public void move() {
        //System.out.println(path.getWaypoint(nextWayPointID));
        if (reachedWaypoint(path.getWaypoint((nextWayPointID)))){
            distanceToNextWayPoint = Double.MAX_VALUE; //this is a way of resetting the lenght, to make sure that the creep doesn't misstake the old lenght when approaching a new waypoint - remove to see bug
            aimTowardsNextWaypoint();
        }
        repositionCreep();
        checkIfSpeedUp();

    }
    /**
     * Get the angle the creep requires to to travel from origin point to next
     * waypoint.
     *
     * This method is a wrapper method for PointCalculations.getVectorAngle so that this
     * method exists in Path.
     *
     * @return the angle the object needs to travel to travel from origin to
     * nextWaypoint in degrees.
     */
    public  double getAngleToNextPoint() {
        if (this.getPosition() != null && path.getWaypoint(nextWayPointID)!= null) {
            double angle = PointCalculations.getVectorAngle(this.getCenter(), path.getWaypoint(nextWayPointID));
            //System.out.println("angle:" + angle);
            return angle;

        }
        System.out.println("In Creep: Error in abstractCreep: trying to get angle to next point- invalid point, trying to calculate angle to null");
        //dont handle this as exception because try-catch takes resources & the error is not fatal, instead default to no rotation.
        return 0;
    }

    /**
     * the method that changes the value "position" of the creep, used in the
     * move() method.
     */
    private void repositionCreep(){
        Vector2 newPosition;
        //do not remove below comments, useful for misc debugging:
        //System.out.println("real x movement:" + (Math.cos(Math.toRadians(moveAngle)) * speed));
        //System.out.println("real y movement:" + (Math.sin(Math.toRadians(moveAngle)) * speed));


        //System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);

        float xLength = (float)(Math.cos(Math.toRadians(moveAngle)) * speed); //+0.5 to round to correct int aka 0.9 is 1
        float yLength = (float)(Math.sin(Math.toRadians(moveAngle)) * speed);
        velocity = new Vector2(xLength, yLength);

        //System.out.println(velocity);
        this.setPosition(new Vector2(getPosition().x + velocity.x, getPosition().y + velocity.y));

    }

    /**
     * Sets the moveAngle of the creep to face the next waypoint
     */
    private void aimTowardsNextWaypoint(){

        nextWayPointID++;
        if(nextWayPointID >= path.getWaypoints().size()) {
            destroy();
            GameData.getInstance().getMap().getBase().damage();

        }
        else {
            moveAngle = getAngleToNextPoint();
        }
    }

    /**
     * See if the creep has reached the next waypoint
     *
     * @param waypoint what waypoint to check for
     * @return true if within distance of waypoint
     */
    private boolean reachedWaypoint(Vector2 waypoint){

        double olddistanceToNextWayPoint = distanceToNextWayPoint;
        distanceToNextWayPoint = PointCalculations.distanceBetweenNoSqrt(getCenter(), waypoint);
        if (distanceToNextWayPoint > olddistanceToNextWayPoint){ //if you're no longer approaching the waypoint, you're leaving it
            return true;
        }
        return false;
    }

    /**
     * Slows down a creep by a % and a duration (number of frames)
     * @param percentage how many percent slower the creep should be
     * @param nbrOfTicks how many frames the creep should be slowed
     */
    public void slowDown(double percentage, int nbrOfTicks) {
        if(slowDownTime<0) {
            Double newSpeed = (1 - percentage / 100) * speed;
            speed = newSpeed.intValue();
            slowDownTime = nbrOfTicks;
        }
    }

    /**
     * Check if the creep has completed a slow duration and in that case return it to normal speed
     */
    public void checkIfSpeedUp(){
        if(slowDownTime>0){
            slowDownTime--;
        }else if(slowDownTime==0) {
            speed = backUpSpeed;
            slowDownTime = -1;
        }
    }
}
