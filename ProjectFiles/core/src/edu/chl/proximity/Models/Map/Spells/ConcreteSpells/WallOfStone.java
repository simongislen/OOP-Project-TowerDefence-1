package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Cooldown;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

import java.util.List;

/**
 * @author Johan Swanberg
 * @date 2015-04-25
 *
 * Creates a circle where all creeps inside and who collide in it has to stop. Seen as 100% slow.
 *
 * 03/05 modified by Simon Gislen. Spells have range.
 * 10/05 modified by Johan Swanberg. Makes spell work again, was broken by change in Map interface
 * 15/5 modified by Johan Swanberg, spells now have a cooldown pattern
 * 18/05 modified by Linda Evaldsson. Removed Map.
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class WallOfStone extends Spell {

    //Spell stats
    private static double range = 60f;
    private static int duration = 120;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/wallofstone.png");
    private static final int maxCooldown = 60*10;
    private static int currentCooldown = 0;

    public WallOfStone(ParticleManager particleManager) {
        super(image, "Wall of Stone", duration, new Cooldown(maxCooldown), particleManager); //600 frames = 10 seconds @ 60 fps
    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creepsWithinRange = getCreepsWithinDistance(getPosition(),range);

        for (Creep creep : creepsWithinRange) {
            creep.slowDown(100, 1);
            if (getParticleManager() != null) {
                getParticleManager().getDirtSmokeEffect().createEffect(creep.getCenter());
            }
        }
    }

    @Override
    public String getDescription() {
        return "Makes creep in the area come to a halt for a short time.";
    }


    @Override
    public void playParticleEffect() {
        if (getParticleManager() != null)
            getParticleManager().getWallOfStone().createEffect(getPosition());
    }
    @Override
    public double getRange() {
        return range;
    }
}
