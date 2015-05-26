package edu.chl.proximity.Models.Map.Spells.ConcreteSpells;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityRandom;

import java.util.List;

/**
 * @author Hanna Romer
 * @date 2015-05-22
 *
 * 24/05 modified by Linda Evaldsson. Removed spell cooldown implementation, moved it to Cooldown class instead.
 */
public class SpeedGamble extends Spell{
    private static double range = 100f;
    private static int duration = 240;
    private static Image image = new Image(Constants.FILE_PATH + "Spells/TarotCards.png");
    private static final int maxCooldown = 60 * 5;
    private static int currentCooldown = 0;
    private int effect;

    public SpeedGamble(ParticleManager particleManager) {
        super(image, duration, new Cooldown(maxCooldown), particleManager);

    }

    public void performEffect(int counter) {
        List<Creep> creeps = getCreepsWithinDistance(this.getPosition(),range);
        for(Creep c: creeps) {
            switch (effect) {
                case 0:
                    c.slowDown(70,60*3);
                    break;
                case 1:
                    c.slowDown(60,1);
                    break;
                case 2:
                    c.slowDown(45,60*3);
                    break;
                case 3:
                    c.slowDown(45,1);
                    break;
                case 4:
                    c.slowDown(30,60*3);
                    break;
                case 5:
                    c.slowDown(-40,60*3);
                    break;
                case 6:
                    c.slowDown(-70,60*3);
                    break;
                default: break;
            }
        }
    }


    public void playParticleEffect() {
        if (getParticleManager() != null)
            getParticleManager().getFireFieldEffect().createEffect(getPosition());
    }

    public double getRange() {
        return range;
    }

    @Override
    public void start(){
        super.start();
        Double dEffect=ProximityRandom.getRandomDoubleBetween(0,7);
        effect=dEffect.intValue();
    }

}

