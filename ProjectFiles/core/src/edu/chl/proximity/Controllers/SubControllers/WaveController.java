package edu.chl.proximity.Controllers.SubControllers;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.WavePanel;
import edu.chl.proximity.Models.CreepGenerator.StandardGenerator;
import edu.chl.proximity.Models.Map.Creeps.Creep;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Waves.Wave;
import edu.chl.proximity.Models.Player.Players.GameData;

import java.util.List;

/**
 * @author Simon Gislen
 * @date 2015-04-16
 *
 * A controller class that handles creep spawning in waves.
 */
public class WaveController implements UpdateHandler {
    private StandardGenerator waveGenerator;
    private Map map;
    private int waveIndex = 1;
    private WavePanel wavePanel;

    private Wave currentWave;
    private List<Creep> creeps;
    private int creepIndex;

    private int spawnIntervalCounter = 0;
    private int cooldownCounter = 0;


    public WaveController(Map map) {
        this.waveGenerator = new StandardGenerator(map);
        wavePanel = new WavePanel();

        this.map = map;
    }

    public void update() {

        if (currentWave == null) {
            currentWave = waveGenerator.generateWaveForWaveIndex(waveIndex);
            creeps = currentWave.getCreeps();
            creepIndex = 0;
        }
        if (creepIndex < creeps.size()) {
            if (spawnIntervalCounter < currentWave.getSpawnInterval() * 60) {
                spawnIntervalCounter++;

                return;
            }
            spawnIntervalCounter = 0;
            Creep creep = creeps.get(creepIndex);
            map.add(creep);
            creepIndex++;
        }
        else {
            if (cooldownCounter < currentWave.getCooldownTimeInterval() * 60) {
                cooldownCounter++;
            }
            else {
                waveIndex++;
                currentWave = null;
                cooldownCounter = 0;
            }
        }
        wavePanel.updateWaves(waveIndex, GameData.getInstance().getPlayer().winCondition);

        GameData.getInstance().getPlayer().documentPlayerProgress(map.getName(), waveIndex); //tell the player to handle having reached a new wave


    }

    public BoardObject getWavePanel() { return wavePanel;}

}
