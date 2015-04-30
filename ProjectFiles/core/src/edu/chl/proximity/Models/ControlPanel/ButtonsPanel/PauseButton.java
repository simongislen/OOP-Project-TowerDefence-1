package edu.chl.proximity.Models.ControlPanel.ButtonsPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna R�mer
 * @date 2015-04-29
 */
public class PauseButton extends BoardObject{
    public static Image upImage = new Image(Constants.filePath + "Buttons/PauseButton.png");
    public static Image downImage = new Image(Constants.filePath + "Buttons/DownPauseButton.png");
    private static int height=50;
    private static int width=50;

    /**
     * Create a new Pause button
     * @param position What position the button shall have
     */
    public PauseButton(Vector2 position){
        super(position,upImage,0,width,height);
    }

    /**
     * Sets image depending on what the current game-speed is
     */
    public void setRightImage(){
        if(GameData.getInstance().getGameSpeed()==0){
            super.setImage(downImage);
        }else{
            super.setImage(upImage);
        }
    }

    /**
     * Renders the button with what image it should currently have
     * @param batch what batch to render the PauseButton
     */
    public void render(SpriteBatch batch){
        setRightImage();
        super.render(batch);
    }

}