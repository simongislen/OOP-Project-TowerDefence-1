package edu.chl.proximity.Models.Map.Spells;

import com.badlogic.gdx.graphics.Color;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Holdables.Holdable;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.PointCalculations;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg
 * @date 2015-04-28
 *
 * An abstract class for all spells, all spells are holdable objects (can be created by cursor, "hand")
 * and are persistent objects, their effect lasts during a couple of frames.
 *
 * 03/05 modified by Simon Gislen. Spell has a area range, could be infinite.
 * 07/05 modified by Linda Evaldsson. Spell has a control panel Image.
 * 15/5 modified by Johan Swanberg, spells now have a cooldown pattern
 * 18/05 modified by Linda Evaldsson. Removed Map and added towers and creep-lists instead.
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public abstract class Spell extends PersistentObject implements Holdable {

    private Image controlPanelImage;
    private List<Creep> creeps;
    private List<Tower> towers;
    private ParticleManager particleManager;
    private int healthChange = 0;
    private Resources resourcesChange=new Resources(0,0,0);
    private Resources cost = new Resources(0, 0, 0);
    private boolean isPlaced = false;
    private Cooldown cooldown;
    private String name;

    public Spell(Image icon, String name, int counter, Cooldown cooldown, ParticleManager particleManager) {
        super(null, null, counter);
        this.cooldown = cooldown;
        this.name = name;
        controlPanelImage = icon;
        this.particleManager = particleManager;

    }

    public void resetCooldown() {
        cooldown.reset();
    }

    public boolean canBePlacedOnPath() {
        return true;
    }

    public String getHelpInfo() {
        return getName() + "\n" +
                "Cooldown: " + cooldown.getCooldownInSeconds() + "s\n" +
                "\n" +
                getDescription();
    }

    public String getDescription() {
        return "No description";
    }

    public String getName() {
        return name;
    }

    public boolean isReadyToCast() {
        return cooldown.isReady();
    }

    public Cooldown getCooldown() {
        return cooldown;
    }

    /**
     * decrease or increase the player health
     * @param newHealthChange how much health the player should recieve
     */
    public void setHealthChange(int newHealthChange) {
            healthChange = newHealthChange;
    }
    public int getHealthChange() {
        return healthChange;
    }

    @Override
    public void preparePlacing(ProximityVector position) {
        if (isReadyToCast()){
            cooldown.startCooldown();
            this.setPosition(position);
            this.start();
            playParticleEffect(); //important that this is after setPosition
            isPlaced = true;
        }
    }

    public List<Creep> getCreepsWithinDistance(ProximityVector position, double range) {
        List<Creep> creepsWithinRange = new ArrayList<Creep>();
        if (range <= 0 || position == null){return creepsWithinRange;}
        if (creeps != null){
            for (Creep creep : creeps) {
                if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), position) < range * range) {
                    creepsWithinRange.add(creep);
                }
            }
        }

        return creepsWithinRange;
    }


    /**
     * Get a list of all towers within range(radius) of the position
     * @param pos What position to search around
     * @param range What range (radius) to search around
     * @return A list of towers within the range specified
     */
    public List<Tower> getTowersWithinDistance(ProximityVector pos, double range){
        List<Tower> towersInRange = new ArrayList<Tower>();
        if(range <=0 || pos==null){return towersInRange;}
        for(Tower tower: towers){
            if(PointCalculations.distanceBetweenNoSqrt(tower.getCenter(),pos) < range*range){
                towersInRange.add(tower);
            }
        }
        return towersInRange;
    }

    public abstract void playParticleEffect();
    public abstract double getRange();


    public Image getControlPanelImage() {
        return controlPanelImage;
    }

    public List<Creep> getCreeps() {
        return creeps;
    }

    public void setCreeps(List<Creep> creeps) {
        this.creeps = creeps;
    }

    public ParticleManager getParticleManager() {
        return particleManager;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }

    public Resources getCost() {
        return cost;
    }

    public Color getColor() {
        if (cooldown.isReady()){
            return new Color(0.2f, 0.9f, 0.2f, 0.2f);
        }
        return new Color(0.9f, 0.2f, 0.2f, 0.2f);
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setResourcesChange(int points, int lines, int polygons){resourcesChange.setResources(points,lines,polygons);}

    public Resources getResourcesChange(){return resourcesChange;}
}
