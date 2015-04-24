package edu.chl.proximity.Models.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.Particles.ProximityEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-04-02
 *
 * 08/04 modified by Linda Evaldsson. Refactoring to Projectile instead of AbstractProjectile.
 * Unknown date modified by Linda Evaldsson
 * Unknown date modified by Johan Swanberg
 * 04-24 modified by Johan Swanberg, fixed projectile hitbox origin to center
 *
 * An abstract class for projectiles. All projectiles extend this class.
 */
public abstract class Projectile extends BoardObject implements Cloneable{
    private ProximityEffect effect;
    private int health;
    private int speed;
    private Sound sound;
    private RandomXS128 rndGenerator = new RandomXS128();
    /**
     * Create a new projectile type
     * @param particleEffect what effect should be played when the bullet hits
     * @param health how many hits can the bullet take before dissapearing
     * @param speed how fast does the bullet travel
     * @param sound what sound does the bullet make on collision
     * @param image what image does the bullet have
     * @param position where should the projectile be created
     * @param angle what angle should the image of the projectile have
     */
    public Projectile(ProximityEffect particleEffect, int health, int speed, Sound sound, Image image, Vector2 position, double angle){
        super(position, image, angle);
        this.effect=particleEffect;
        this.health=health;
        this.speed=speed;
        this.sound=sound;
    }


    /**
     * readjust the angle so the projectile is facing the current target
     */
    public abstract void reAngle();

    /**
     * Get whether the projectile point intersects another area
     *
     * @param creeppos what other position should be compared
     * @param hitbox how big the hitbox the projectile should check for. (the hitbox of the creep)
     * @return true if the projectile intersects the given area
     */
    public boolean collidesWith(Vector2 creeppos, int hitbox) {
        //+20 because the creep image is 40x40 pixels, and we want to check for the center of the image
        if (creeppos.y+20 - hitbox < getCenter().y && getCenter().y < creeppos.y+20 + hitbox) {
            if (creeppos.x+20 - hitbox < getCenter().x && getCenter().x < creeppos.x+20 + hitbox) {
                return true;
            }
        }
        return false;
    }

    /**
     *  Play all the logic the bullet does on collision.
     */
    public void collide(Creep creep){
        playParticleEffect();
        playSound();
        reAngle();
        decreaseProjectileHealth();
        attack(creep);
    }

    public void checkCollision() {
        List<Creep> creeps = new ArrayList<Creep>(GameData.getInstance().getMap().getCreeps());

        for (Creep creep : creeps){
        if(collidesWith(creep.getPosition(), 20)) {
            collide(creep);
        }
    }

    }

    /**
     * the logic that happens that is specific to this projectile, most often creep.devolve
     */
    public abstract void attack(Creep creep);

    /**
     * play the effect connected to this projectile (example, the explosion effect of the missile, smoke & circles))
     */
    public void playParticleEffect(){
        if (effect != null){
            effect.createEffect((int) getCenter().x, (int) getCenter().y); //display the effect this projectile has
        }
    }

    /**
     * play the sound connected to this projectile, example explosion sound.
     * Plays the sound relative to the creep position and gives a slight random putch
     */
    public void playSound(){
        if (sound != null){
            float xSoundPosition = getXSoundPosition();
            float rndPitch = (rndGenerator.nextFloat()/2) + 0.5f;
            sound.play(GameData.VOLUME, rndPitch, xSoundPosition);
        }
    }

    /**
     * get where the projectile is x-wise
     * @return a value between -1 and 1, where -1 is furthest to the left, 0 is in the middle of the map and 1 is to the right.
     */
    private float getXSoundPosition(){
        return ((getCenter().x / 800)-0.5f)*2;
        //getCenter/800 returns a value between 0 and 1, 0 if to left and 1 if to right.
        //-0.5 makes the value between -0.5,+0,5, *2 makes it -1,1 -> between left and right ear
    }
    /**
     * decrease the projectiles health by one, remove it if its 0
     */
    private void decreaseProjectileHealth(){
        health--;
        if (health <= 0){
            GameData.getInstance().getMap().getRemoveStack().add(this);
        }
    }

    /**
     * Moves the projectile a step forward based on the current angle and speed
     */
    public void move() {
        Vector2 newPosition;
        //System.out.println("real x movement:" + (Math.cos(Math.toRadians(angle)) * speed));
        //System.out.println("real y movement:" + (Math.sin(Math.toRadians(angle)) * speed));
        float xLenght = (float) ((Math.cos(Math.toRadians(getAngle())) * speed));
        float yLenght = (float) ((Math.sin(Math.toRadians(getAngle())) * speed));
        //System.out.println("x movement= " + xLenght + " y-momement:" + yLenght);
        newPosition = new Vector2(getPosition().x + xLenght, getPosition().y + yLenght);
        setPosition(newPosition);

    }

}
