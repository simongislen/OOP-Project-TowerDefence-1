package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingFactory;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Map.Towers.BulletTower;
import edu.chl.proximity.Models.Map.Towers.MissileTower;
import edu.chl.proximity.Models.Map.Towers.SlowTower;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PercentBar;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 *
 * The class managing the information to the right of the screen
 *
 * Unknown date modified by Johan Swanberg
 */
public class ControlPanel extends BoardObject{

    //The texts that are displayed
    private ProximityFont healthText;
    private ProximityFont lineText;
    private ProximityFont polygonText;
    private ProximityFont pointText;

    private PercentBar percentBar;

    //Width and heigh of the ControlPanel when it is initiated
    private static int width = 300;
    private static int height = Constants.GAME_WIDTH;

    private static Vector2 position = new Vector2(Constants.GAME_WIDTH - width, 0);

    //The towers that are rendered on the ControlPanel
    private List<ControlPanelTower> controlPanelTowerList = new ArrayList<ControlPanelTower>();

    //The amount of towers that is shown on one row in the ControlPanel
    private int towersPerRow = 1;

    //The background of the ControlPanel
    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/tweed.png");

    /**
     * Create a new instance of the controll panel
     */
    public ControlPanel(Map map) {
        super(map, position, background, 0, width, height);

        initiateText();
        initiateControlPanelTowers();

    }



    /**
     * Initiates all the texts of this ControlPanel
     */
    public void initiateText() {
        percentBar = new PercentBar(new Vector2(position.x + 30, position.y + 30), width - 60, 32, Color.WHITE, Color.BLACK, Color.RED);

        healthText = createFont(30, 10, "null");
        lineText = createFont(30, 80, "null");
        polygonText = createFont(30, 100, "null");
        pointText = createFont(30, 120, "null");
    }

    /**
     * Initiates the towers that are rendered in this controlPanel
     */
    public void initiateControlPanelTowers() {
        TargetingFactory targetFactory = new TargetingFactory(getMap());
        controlPanelTowerList.add(new ControlPanelTower(getMap(), new Vector2(0, 0), new BulletTower(getMap(), new Vector2(0, 0), targetFactory.getTargetClosest())));
        controlPanelTowerList.add(new ControlPanelTower(getMap(), new Vector2(0, 0), new MissileTower(getMap(), new Vector2(0, 0), targetFactory.getTargetClosest())));
        controlPanelTowerList.add(new ControlPanelTower(getMap(), new Vector2(0, 0), new SlowTower(getMap(), new Vector2(0, 0), targetFactory.getTargetClosest())));

        for(int i = 0; i < controlPanelTowerList.size(); i++) {
            System.out.println("In controllpanel: Towers per row: " + i % towersPerRow);
            System.out.println("In controlpanel: i/towers per row "+ i/towersPerRow);
            controlPanelTowerList.get(i).setPosition(new Vector2(getPosition().x + 30 + 50 * (i % towersPerRow), 150 + 50 * (i/towersPerRow)));
        }
    }


    public void setHealth(int percent){
        percentBar.setPercent(percent);
        healthText.setText("Life: ");
    }

    public void setResources(Resources resources){
        lineText.setText("Lines: " + resources.getLines());
        polygonText.setText("Polygons: " + resources.getPolygons());
        pointText.setText("Points: " + resources.getPoints());

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


    /**
     * Create a new text within the controlpanel
     * @param x x coordinate within the control panel
     * @param y y coordinate within the control panel
     * @param s what the string should say
     * @return a ProximityFont object corresponding to this information
     */
    private ProximityFont createFont(float x, float y, String s){
        return new ProximityFont(new Vector2(getPosition().x + x, y), s);
        //return new ProximityFont(new Vector2(width + x, y), s);
    }

    /**
     * Render the Controlpanel
     * @param batch what batch to render the controlpanel
     */
    public void render(SpriteBatch batch) {

        background.getTexture().setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch.draw(background.getTexture(), position.x, position.y, background.getTexture().getWidth(), background.getTexture().getHeight(), width, height);

        healthText.draw(batch);
        lineText.draw(batch);
        pointText.draw(batch);
        polygonText.draw(batch);
        for(ControlPanelTower cpTower : controlPanelTowerList) {
            cpTower.render(batch);
        }
    }

    public void renderShapes(ShapeRenderer renderer) {
        percentBar.render(renderer);
    }


}
