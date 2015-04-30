package edu.chl.proximity.Models.Player.Spells.ConcreteSpells;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Player.Spells.Spell;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan on 2015-04-24.
 * A spell that creates an area of lightning, the lightning will "bounce" on nearby creeps
 */
public class ChainLightning extends Spell {
    private List<Vector2> alreadyHitPositions = new ArrayList<Vector2>();
    private double range = 100;
    private double bounceRange = 20;
    public ChainLightning() {
        super(null, 2); //600 frames = 10 seconds @ 60 fps

    }

    public ChainLightning(Vector2 position, List<Vector2> alreadyHitPositions) {
        super(null, 2); //600 frames = 10 seconds @ 60 fps
        range = 100;
        this.alreadyHitPositions = alreadyHitPositions;
        GameData.getInstance().getMap().getParticleManager().getLightningCreepEffect().createEffect(position); //create the spark effect
    }

    @Override
    public void performEffect(int counter) {
        List<Creep> creeps = GameData.getInstance().getMap().getCreeps();
        for (Creep creep : creeps) {
            if (PointCalculations.distanceBetweenNoSqrt(creep.getCenter(), getPosition()) < range * range) {
                creep.devolve();//devolve all creeps in range
                if (!alreadyHit(creep.getCenter())) { //Create a new spark from the hit creep, if the area has not already bounced
                    ChainLightning newSpark = new ChainLightning(creep.getCenter(), alreadyHitPositions);
                    alreadyHitPositions.add(creep.getCenter());
                }
            }
        }
    }

    /**
     * cyckle through all the already hit positions, and see if the new position is within range of an already hit position
     * @param position
     * @return
     */
    private boolean alreadyHit(Vector2 position){
        for (Vector2 hitPosition:alreadyHitPositions){
            if (PointCalculations.distanceBetweenNoSqrt(position, hitPosition) < bounceRange*bounceRange){ //bounceRange is the range where the lightning wont go again
                return true;
            }
        }
        return false;
    }

    @Override
    public void playParticleEffect() {
        GameData.getInstance().getMap().getParticleManager().getLightningOriginSpellEffect().createEffect(getPosition()); //create original lightning effect
    }
}