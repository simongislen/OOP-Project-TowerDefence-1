package edu.chl.proximity.Models.Map.Projectiles;

import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximitySound;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Simon Gislen
 * @date 18/05/15.
 *
 * A class representing a fast bullet that snipes creeps
 */
public class SniperBullet extends Projectile {
    private static Image img = new Image(Constants.FILE_PATH + "Projectiles/blast.png");
    private static ProximitySound sound = new ProximitySound(Constants.FILE_PATH + "Sounds/poof.ogg");
    private static int baseHealth = 1;

    private static int speed = 30;
    /**
     * create a new bullet projectile
     *  @param position where to create the bullet projectile
     * @param angle    what angle the image & movement should start at
     */
    public SniperBullet(ProximityVector position, double angle, ParticleManager particleManager) {
        //Arguments: ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, ProximityVector position, double angle, Creep target
        super(particleManager.getSniperBulletEffect(), baseHealth, speed, sound, img, position, angle);
    }

    @Override
    public void reAngle() { //bullets keep their angle
    }

    @Override
    public void attack(Creep creep) {
        if (creep != null){
            creep.devolve();
        }

    }
}
