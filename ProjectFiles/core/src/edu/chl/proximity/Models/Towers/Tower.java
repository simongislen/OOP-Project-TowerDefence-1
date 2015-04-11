package edu.chl.proximity.Models.Towers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Projectiles.Projectile;
import edu.chl.proximity.Models.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Utilities.PointCalculations;


/**
 * Created by Johan on 4/2/2015.
 */
public abstract class Tower extends BoardObject {

    private int bulletSpeed = 10;
    private int reloadTime;
    private int currentReload = 0;

    private Projectile projectileType;
    private TargetingMethod targetingMethod;
    private double range;
    private Creep currentTarget;

    /**
     * Create a new type of tower
     * @param pos where the tower should be placed
     * @param image what image the tower should have
     * @param range what range the tower should have
     * @param targetingMethod how the tower should decide what target to shoot
     * @param reloadTime how long it takes the tower to shoot another bullet (in frames)
     */
    public Tower(Vector2 pos, Image image, double range, TargetingMethod targetingMethod, int reloadTime){
        //arguments: Position, texture, image rotation-angle
        super(pos, image, 0);
        this.range = range;
        this.targetingMethod = targetingMethod;
        this.reloadTime = reloadTime;
    }


    /**
     * create a projectile at the towers location, if the tower can shoot (aka is not reloading)
     * if the tower shoots, start the reload time
     */
    public void shoot(){
        if(currentReload < 1 && currentTarget != null){
            GameData.getInstance().getMap().addProjectile(createProjectile());
            currentReload = reloadTime;
        }
    }

    /**
     * Targets the closest creep, if one is in range.
     */
    public void target(){
        currentTarget = targetingMethod.getTarget(getPosition(), range);
        if (currentTarget != null) {
            this.setAngle(PointCalculations.getVectorAngle(this.getPosition(), currentTarget.getPosition()) - 90); //-90 because all asset images are created facing upwards
        }
    }

    public Creep getTarget(){
        return currentTarget;
    }

    /**
     * create the projectile-type this tower should fire
     * @return a projectile corresponding to this towers type
     */
    public abstract Projectile createProjectile();

    /**
     * progress the towers reload, tower can shoot when reload is at 0
     */
    public void reload(){
        if(currentReload > 0){
            currentReload --;
        }
    }

}