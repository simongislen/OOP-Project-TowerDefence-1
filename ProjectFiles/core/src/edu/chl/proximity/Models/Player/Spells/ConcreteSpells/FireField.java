package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.List;


/**
 * A spell which creates a circle of flames, where creeps slowly devolve, but inside this field the
 * creeps run slightly faster.
 * @author by Johan on 2015-04-24.
 *
 * 03-05-2015 Modified by Simon Gislen. Spells have range.
 */
public class FireField extends Spell {

    //Spell stats
    private static double range = 60f;
    private static int duration = 120;
    private static Image image = new Image(Constants.FILE_PATH + "Creeps/Line3/6.png");

    public FireField(Map map){
        super(map, image, duration); //600 frames = 10 seconds @ 60 fps

    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = getMap().getCreeps();
        for (Creep creep:creeps){
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                getMap().getParticleManager().getFireCreepEffect().createEffect(creep.getCenter());
                //creep.slowDown(-20, 1); //speeds up creeps
                if(counter % 20 ==0)

                    creep.devolve();
            }

        }
    }

    @Override
    public void playParticleEffect() {
        getMap().getParticleManager().getFireFieldEffect().createEffect(getPosition());
    }
    @Override
    public double getRange() {
        return range;
    }
}

