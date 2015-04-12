package edu.chl.proximity.Models.Backgrounds;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;

/**
 * Created by Johan on 2015-04-12.
 */
public class Background extends BoardObject {
    /**
     * create a new background object
     *
     * @param img      the image of the object
     */
    public Background(Image img) {
        super(new Vector2(-700,-300), img, 0);
    }

    /**
     * make the map rotate slowly
     */
    public void rotate(){
        rotate(1);
    }

}