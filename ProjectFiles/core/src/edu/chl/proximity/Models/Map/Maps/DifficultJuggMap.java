package edu.chl.proximity.Models.Map.Maps;


import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.ConcretePaths.DifficultJuggPath;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan Swanberg
 * @date 2015-05-22
 *
 * A class for handling a concrete map.
 */
public class DifficultJuggMap extends Map{

    private static Path path = new DifficultJuggPath();


    /**
     * creates the map instance
     */
    public DifficultJuggMap(ParticleManager particleManager) {
        super(path, new Background(new Image(Constants.FILE_PATH + "Backgrounds/simpleLineBackground.png")), "DifficultJugg", particleManager);
    }
    @Override
    public String getPreviousMapName() {
        return "Snake";
    }
    public Map getNew(){
        return new DifficultJuggMap(getParticleManager());
    }
}
