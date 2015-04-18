package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Models.ProximityFont;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Towers.BulletTower;
import edu.chl.proximity.Models.Towers.MissileTower;
import edu.chl.proximity.Models.Towers.SlowTower;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Linda Evaldsson
 * @revised by Johan Swanberg
 * @date 2015-04-17
 * The class managing the information to the right of the screen
 */
public class ControlPanel {

    //The texts that are displayed
    private ProximityFont healthText;
    private ProximityFont lineText;
    private ProximityFont polygonText;
    private ProximityFont pointText;

    //Width and heigh of the ControlPanel
    private int width = 300;
    private int height = Gdx.graphics.getHeight();

    //Position of the ControlPanel on the display
    private Vector2 position;

    //The towers that are rendered on the ControlPanel
    private List<ControlPanelTower> controlPanelTowerList = new ArrayList<ControlPanelTower>();

    //The amount of towers that is shown on one row in the ControlPanel
    private int towersPerRow = 1;

    //The background of the ControlPanel
    private Image background = new Image(Constants.filePath + "Backgrounds/temporaryControlPanelBackground.png");

    /**
     * Create a new instance of the controll panel
     */
    public ControlPanel() {
        initiatePosition();
        initiateText();
        initiateControlPanelTowers();

    }

    /**
     * Initiates the position of this ControlPanel
     */
    public void initiatePosition() {
        this.position = new Vector2(Gdx.graphics.getWidth() - width, 0); //set the top left corner of the control panel;
    }

    /**
     * Initiates all the texts of this ControlPanel
     */
    public void initiateText() {
        healthText = createFont(30, 30, "null");
        lineText = createFont(30, 60, "null");
        polygonText = createFont(30, 80, "null");
        pointText = createFont(30, 100, "null");
    }

    /**
     * Initiates the towers that are rendered in this controlPanel
     */
    public void initiateControlPanelTowers() {
        controlPanelTowerList.add(new ControlPanelTower(new Vector2(0, 0), new BulletTower(new Vector2(0, 0))));
        controlPanelTowerList.add(new ControlPanelTower(new Vector2(0, 0), new MissileTower(new Vector2(0, 0))));
        controlPanelTowerList.add(new ControlPanelTower(new Vector2(0, 0), new SlowTower(new Vector2(0, 0))));

        for(int i = 0; i < controlPanelTowerList.size(); i++) {
            System.out.println(i % towersPerRow);
            System.out.println(i/towersPerRow);
            controlPanelTowerList.get(i).setPosition(new Vector2(position.x + 30 + 50 * (i % towersPerRow), 150 + 50 * (i/towersPerRow)));
        }
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) { this.height = height;}

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setHealth(int percent){
        healthText.setText("Liv: " + percent + "%");
    }

    public void setResources(Resources resources){
        lineText.setText("Lines: " + resources.getLines());
        polygonText.setText("Polygons: " + resources.getPolygons());
        pointText.setText("Points: " + resources.getPolygons());

    }

    /**
     * Returns the ControlPanelTower in the Vector2-position taken as parameter. If none returns null
     * @param position The position to check for objects on
     * @return The ControlPanelTower in the position taken as parameter. Null if there is none.
     */
    public ControlPanelTower getTowerOnPosition(Vector2 position) {
        for(ControlPanelTower cpTower : controlPanelTowerList) {
            if(PointCalculations.isPointInObject(position, cpTower))
                return cpTower;
        }
        return null;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Create a new text within the controlpanel
     * @param x x coordinate within the control panel
     * @param y y coordinate within the control panel
     * @param s what the string should say
     * @return a ProximityFont object corresponding to this information
     */
    private ProximityFont createFont(float x, float y, String s){
        return new ProximityFont(new Vector2(position.x + x, y), s);
        //return new ProximityFont(new Vector2(width + x, y), s);
    }

    /**
     * Render the Controlpanel
     * @param batch what batch to render the controlpanel
     */
    public void render(SpriteBatch batch) {
        background.render(batch, position, 0);
        healthText.draw(batch);
        lineText.draw(batch);
        pointText.draw(batch);
        polygonText.draw(batch);
        for(ControlPanelTower cpTower : controlPanelTowerList) {
            cpTower.render(batch);
        }
    }


}