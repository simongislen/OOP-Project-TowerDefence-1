package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Hanna on 2015-04-23.
 */
public class SpeedButton extends BoardObject{
    private static Image twoArrows=new Image(Constants.filePath + "Buttons/PauseButton");
    private static Image threeArrows=new Image(Constants.filePath + "Buttons/PauseButton");
    private static Image oneArrow=new Image(Constants.filePath + "Buttons/PauseButton");
    private static int height=50;
    private static int width=50;

    public SpeedButton(Vector2 position){
        super(position,twoArrows,0,width,height);
    }

    public void toogle(){
        if(super.getImage().equals(twoArrows)){
            super.setImage(threeArrows);
        }else if(super.getImage().equals(threeArrows)){
            super.setImage(oneArrow);
        }else{
            super.setImage(twoArrows);
        }
    }
}