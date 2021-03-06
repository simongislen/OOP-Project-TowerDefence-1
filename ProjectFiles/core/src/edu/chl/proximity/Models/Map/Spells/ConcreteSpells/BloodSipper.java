package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Cooldown;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * @author Hanna Romer
 * @date 2015-05-22
 *
 * A class for a concrete spell.
 *
 * ---
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class BloodSipper extends Spell {
    private static double range=80f;
    private static int duration=120;
    private static Image image=new Image(Constants.FILE_PATH + "Spells/blood-sipper.png");
    private static final int maxCooldown = 60;

    public BloodSipper(ParticleManager particleManager){
        super(image, "Blood Sipper", duration, new Cooldown(maxCooldown), particleManager);
    }

    public void performEffect(int counter){
        List<Creep> creeps=getCreepsWithinDistance(this.getPosition(), range);
        for(Creep c: creeps){
            if(counter%40==0){
                c.devolve();
            }
            c.slowDown(10,Integer.MAX_VALUE);
        }
    }


    public void playParticleEffect(){
        if (getParticleManager() != null)
            getParticleManager().getBloodSipperEffect().createEffect(getPosition());
    }

    @Override
    public String getDescription() {
        return "Creates a field for two seconds where creeps are slowed and sometimes damaged..";
    }


    public double getRange(){
        return range;
    }

}
