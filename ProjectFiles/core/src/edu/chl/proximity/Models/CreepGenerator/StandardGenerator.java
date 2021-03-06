package edu.chl.proximity.Models.CreepGenerator;

import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Boss1;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Boss2;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line1;
import edu.chl.proximity.Models.Map.Creeps.ConcreteCreeps.Line2;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Waves.Wave;
import edu.chl.proximity.Models.Utils.ProximityRandom;

import java.util.ArrayList;

/**
 * @author Linda Evaldsson
 * @author Johan Swanberg (revised)
 * @author Simon Gislen
 * @date 2015-04-08
 *
 * A class that generates creep waves.
 *
 * ---
 * Unknown date modified by Johan Swanberg
 * 16/04 modified by Simon Gislen. Modified purpose of class to handle creep spawning logic.
 * 21/04 modified by Simon Gislen.
 */
public class StandardGenerator {
    private Map map;

    public StandardGenerator(Map map) {
        this.map = map;

    }

    /**
     * Method that generates creep waves
     *
     * @param waveIndex the wave number that the player is on
     * @return Wave instance describing creeps, cooldown and spawn intervals
     */
    public Wave generateWaveForWaveIndex(int waveIndex) {

        if (waveIndex < 1) {
            return null;
        }
        else if (waveIndex > 1000) {
            return null;
        }
        //Defaults
        double spawnInterval = 1;
        double cooldown = 4;
        ArrayList<Creep> creeps = new ArrayList<Creep>();

        //First 10 waves are standard, after this spawning follows an algorithm
        if (waveIndex <= 10) {

            for (int i = 0; i < 5; i++) {
                creeps.add(new Line1(1, map.getParticleManager(), map.getPath()));
            }

            switch (waveIndex) {
                case 2: {
                    spawnInterval = 0.75;
                }
                break;
                case 3: {
                    for (int i = 0; i < 5; i++) {
                        creeps.add(new Line1(2, map.getParticleManager(), map.getPath()));
                    }
                }
                break;
                case 4: {
                    for (int i = 0; i < 5; i++) {
                        creeps.add(new Line1(2, map.getParticleManager(), map.getPath()));
                    }
                    spawnInterval = 0.5;
                }
                break;
                case 5: {
                    for (int i = 0; i < 5; i++) {
                        creeps.add(new Line1(3, map.getParticleManager(), map.getPath()));
                    }
                    spawnInterval = 1;
                }
                break;
                case 6: {
                    for (int i = 0; i < 7; i++) {
                        creeps.add(new Line2(1, map.getParticleManager(), map.getPath()));
                        creeps.add(new Line1(4, map.getParticleManager(), map.getPath()));
                    }
                    spawnInterval = 0.9;
                }
                break;
                case 7: {
                    for (int i = 0; i < 15; i++) {
                        creeps.add(new Line2(2, map.getParticleManager(), map.getPath()));
                    }
                    for (int i = 0; i < 5; i++) {
                        creeps.add(new Line1(4, map.getParticleManager(), map.getPath()));
                    }
                    spawnInterval = 1.0;
                }
                break;
                case 8: {
                    for (int i = 0; i < 12; i++) {
                        creeps.add(new Line2(3, map.getParticleManager(), map.getPath()));
                        creeps.add(new Line1(5, map.getParticleManager(), map.getPath()));
                    }
                    spawnInterval = 1.0;
                }
                break;
                case 9: {
                    for (int i = 0; i < 8; i++) {
                        creeps.add(new Line2(4, map.getParticleManager(), map.getPath()));
                        creeps.add(new Line1(6, map.getParticleManager(), map.getPath()));
                        creeps.add(new Line1(5, map.getParticleManager(), map.getPath()));
                    }
                    spawnInterval = 0.8;
                }
                break;
                default:
                break;

            }

        } else {
            //Some endless algorithm
            double r = ProximityRandom.getRandomDouble()*5;
            for (int i = 0; i < Math.max(0, waveIndex/2f - r); i++) {
                creeps.add(new Line1(6, map.getParticleManager(), map.getPath()));
            }
            for (int i = 0; i < waveIndex/5; i++) {
                creeps.add(new Line1(6, map.getParticleManager(), map.getPath()));
            }
            for (int i = 0; i < waveIndex/5; i++) {
                creeps.add(new Line2(7, map.getParticleManager(), map.getPath()));
            }
            for (int i = 0; i < waveIndex/5; i++) {
                creeps.add(new Line2(6, map.getParticleManager(), map.getPath()));
            }
            for (int i = 0; i < waveIndex/5; i++) {
                creeps.add(new Line1(5, map.getParticleManager(), map.getPath()));
            }

            //Spawn boss creeps
            if (waveIndex >= 20) {
                if (waveIndex % 5 == 0) {
                    int bossCount = (((waveIndex - 20) / 5) * 2) + 1;
                    for (int i = 0; i < bossCount; i++) {
                        creeps.add(new Boss1(map.getParticleManager(), map.getPath()));
                    }
                }
                if (waveIndex >= 36) {
                    if (waveIndex == 39 || waveIndex % 7 == 0) {
                        int bossCount = (((waveIndex - 35) / 7) * 2);

                        if (waveIndex >= 50) {
                            bossCount = (int) Math.pow(bossCount, 2);
                        }
                        for (int i = 0; i < bossCount; i++) {
                            creeps.add(new Boss2(map.getParticleManager(), map.getPath()));
                        }
                    }
                }
            }

            r = ProximityRandom.getRandomDouble()*0.4;
            spawnInterval = Math.max(r, 0.08);

        }
        return new Wave(creeps, spawnInterval, cooldown);
    }

}