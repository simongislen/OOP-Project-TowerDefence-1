package edu.chl.proximity.Models.ControlPanel.PropertiesPanel;

import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-04-23
 *
 * 24/05 modified by Linda Evaldsson. Made this more general and renamed it to PropertisPanelButton. It replaces former MainMenuButton.
 */
public class PropertiesPanelButton extends BoardObject{
    private static Image image=new Image(Constants.FILE_PATH + "Buttons/PropertiesButtonBackground.png");
    private ProximityFont font;

    public PropertiesPanelButton(ProximityVector position, String s){
        super(position,image,0);
        float x = position.x + (getWidth() - s.length()*17) / 2f;
        float y = position.y + (getHeight() - 20) / 2f;
        font = new ProximityFont(new ProximityVector(x, y), s);
        font.setSize(30);
    }

    public void render(ProximityBatch batch) {
        super.render(batch);
        font.draw(batch);
    }

}