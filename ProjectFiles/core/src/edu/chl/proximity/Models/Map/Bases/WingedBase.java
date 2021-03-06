package edu.chl.proximity.Models.Map.Bases;

import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-22
 *
 * A representation of the base for the Lady Luck faction
 */
public class WingedBase extends Base{
    public WingedBase(Path path, ParticleManager particleManager){
        super(path, new Image(Constants.FILE_PATH + "Bases/wingedbase.png"), particleManager.getBaseDamageEffect(), particleManager.getBaseCracksEffect());

    }
}
