package edu.chl.proximity.Models.Map.Particles;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-11
 * A class that keeps track of all effects that can be displayed, and has the ability to render them
 *
 */
public class ParticleManager {

    List<ProximityEffect> allEffects = new ArrayList();

    /**
     * create all the particle pools (currently only explosion)
     */
    public ParticleManager() {
        allEffects.add(new ProximityEffect("explosion3", 100));
        allEffects.add(new ProximityEffect("creepdies", 1000));
        allEffects.add(new ProximityEffect("bulleteffect", 100));
        allEffects.add(new ProximityEffect("baseDamage", 15));
        allEffects.add(new ProximityEffect("baseGlitter", 20));
        allEffects.add(new ProximityEffect("frostBlast", 10));
        allEffects.add(new ProximityEffect("frostField", 1));
        allEffects.add(new ProximityEffect("lightningCreepEffect", 100));
        allEffects.add(new ProximityEffect("lightningOriginSpellEffect", 1));
        allEffects.add(new ProximityEffect("fireField", 1));
        allEffects.add(new ProximityEffect("fireCreepEffect", 100));
        allEffects.add(new ProximityEffect("dirtSmokeEffect", 100));
        allEffects.add(new ProximityEffect("wallOfStoneSpell2", 1));
        allEffects.add(new ProximityEffect("BloodPool", 1));
        allEffects.add(new ProximityEffect("BloodPoolCreepEffect", 1));
        allEffects.add(new ProximityEffect("BloodCarnage", 1));
        allEffects.add(new ProximityEffect("BloodCarnageCreepEffect", 1));
    }

    /**
     * render all active effects that the particleManager keeps track of
     * @param batch what batch should be used to render the effects
     */
    public void renderAllParticles(SpriteBatch batch){
        for(ProximityEffect effect:allEffects){
            effect.renderAllActiveEffects(batch);
        }
    }

    public ProximityEffect getExplosionEffect() {return allEffects.get(0);}
    public ProximityEffect getCreepDiesEffect() {return allEffects.get(1);}
    public ProximityEffect getBulletEffect() {return allEffects.get(2);}
    public ProximityEffect getBaseDamageEffect() {return allEffects.get(3);}
    public ProximityEffect getBaseCracksEffect() {return allEffects.get(4);}
    public ProximityEffect getFrostBlastEffect() {return allEffects.get(5);}
    public ProximityEffect getFrostField() {return allEffects.get(6);}
    public ProximityEffect getLightningCreepEffect() {return allEffects.get(7);}
    public ProximityEffect getLightningOriginSpellEffect() {return allEffects.get(8);}
    public ProximityEffect getFireFieldEffect() {return allEffects.get(9);}
    public ProximityEffect getFireCreepEffect() {return allEffects.get(10);}
    public ProximityEffect getDirtSmokeEffect() {return allEffects.get(11);}
    public ProximityEffect getWallOfStone() {return allEffects.get(12);}
    public ProximityEffect getBloodPoolEffect() {return allEffects.get(13);}
    public ProximityEffect getBloodPoolCreepEffect() {return allEffects.get(14);}
    public ProximityEffect getBloodCarnageEffect() {return allEffects.get(15);}
    public ProximityEffect getBloodCarnageCreepEffect() {return allEffects.get(16);}

}


