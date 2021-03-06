package edu.chl.proximity.Models.Map.Particles;

import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-11
 *
 *  A class that keeps track of all effects that can be displayed, and has the ability to render them
 *
 */
public class ParticleManager {

    List<ProximityEffect> allEffects = new ArrayList();

    /**
     * create all the particle pools (currently only explosion)
     */
    public ParticleManager(Settings settings) {
        allEffects.add(new ProximityEffect("explosion3", 700));
        allEffects.add(new ProximityEffect("creepdies", 3000));
        allEffects.add(new ProximityEffect("bulleteffect", 500));
        allEffects.add(new ProximityEffect("baseDamage", 300));
        allEffects.add(new ProximityEffect("baseGlitter", 50));
        allEffects.add(new ProximityEffect("frostBlast", 1500));
        allEffects.add(new ProximityEffect("frostField", 10));
        allEffects.add(new ProximityEffect("lightningCreepEffect", 900));
        allEffects.add(new ProximityEffect("lightningOriginSpellEffect", 500));
        allEffects.add(new ProximityEffect("fireField", 50));
        allEffects.add(new ProximityEffect("fireCreepEffect", 1200));
        allEffects.add(new ProximityEffect("dirtSmokeEffect", 1200));
        allEffects.add(new ProximityEffect("wallOfStoneSpell2", 15));
        allEffects.add(new ProximityEffect("BloodPool", 10));
        allEffects.add(new ProximityEffect("BloodPoolCreepEffect", 100));
        allEffects.add(new ProximityEffect("BloodCarnage", 10));
        allEffects.add(new ProximityEffect("BloodCarnageCreepEffect", 300));
        allEffects.add(new ProximityEffect("Chomp", 10));
        allEffects.add(new ProximityEffect("fireburst", 400));
        allEffects.add(new ProximityEffect("dustCreepEffect", 400));
        allEffects.add(new ProximityEffect("bloodSipper", 20));
        allEffects.add(new ProximityEffect("luckQGood", 20));
        allEffects.add(new ProximityEffect("luckQBad", 20));
        allEffects.add(new ProximityEffect("luckWGood", 20));
        allEffects.add(new ProximityEffect("luckWBad", 20));
        allEffects.add(new ProximityEffect("explosionBig", 300));
        allEffects.add(new ProximityEffect("sniperBulletEffect", 300));

        for(ProximityEffect effect : allEffects) {
            effect.setSettings(settings);
        }
    }

    /**
     * render all active effects that the particleManager keeps track of
     * @param batch what batch should be used to render the effects
     */
    public void renderAllParticles(ProximityBatch batch){
        for(ProximityEffect effect:allEffects){
            effect.renderAllActiveEffects(batch);
        }
    }
    /**
     * Removes all particles from showing up on the screen, and puts them back in the pool
     */
    public void clearAllParticles(){
        for(ProximityEffect effect:allEffects){
            effect.clearEffects();
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
    public ProximityEffect getSacrificeEffect(){return allEffects.get(17);}
    public ProximityEffect getFireBurstEffect(){return allEffects.get(18);}
    public ProximityEffect getDustCreepEffect(){return allEffects.get(19);}
    public ProximityEffect getBloodSipperEffect(){return allEffects.get(20);}
    public ProximityEffect getLuckQGood(){return allEffects.get(21);}
    public ProximityEffect getLuckQBad(){return allEffects.get(22);}
    public ProximityEffect getLuckWGood(){return allEffects.get(23);}
    public ProximityEffect getLuckWBad(){return allEffects.get(24);}
    public ProximityEffect getExplosionBigEffect(){return allEffects.get(25);}
    public ProximityEffect getSniperBulletEffect(){return allEffects.get(26);}

}



