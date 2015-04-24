package edu.chl.proximity.Models;

import com.badlogic.gdx.Game;
import edu.chl.proximity.Controllers.GameStates.GameScreen;
import edu.chl.proximity.Controllers.GameStates.MenuScreen;
import edu.chl.proximity.Models.ButtonsPanel.ButtonPanel;
import edu.chl.proximity.Models.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Factions.Faction;
import edu.chl.proximity.Models.Holdables.Hand;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Maps.StandardMap;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Players.Player;
import edu.chl.proximity.Models.PropertiesPanel.PropertiesPanel;
import edu.chl.proximity.Proximity;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-08
 *
 * This class keeps track of the collected data of this current game-instance,
 * A collection of the current map, played factions, gamespeed and misc information.
 *
 * 23/04 edited by Hanna R�mer. Added ButtonPanel, PropertiesPanel and sound volume of game.
 * 24/04 edited by Hanna R�mer. Added Proximity and MainMenu + their setters and getters
 */
public class GameData {

    private static GameData gameData;
    private Map map;
    private Player player;
    private Hand hand = new Hand();
    private MainMenu mainMenu;
    private ButtonPanel buttonPanel;
    private PropertiesPanel propertiesPanel;
    private int gameSpeed = 1;

    private Proximity proximity;

    public static float VOLUME= 0.1f;


    private GameData() {}


    public void setGame(Proximity proximity){
        this.proximity=proximity;
    }
    public Proximity getGame(){
        return proximity;
    }

    /**
     * set the speed in which the game logic updates
     * The method does nothing if input speedMultiplier is less than 0.
     * @param speedMultiplier how fast the game logic should update
     */
    public void setGameSpeed(int speedMultiplier){
        if(speedMultiplier >= 0){
            gameSpeed = speedMultiplier;
        }

    }

    /**
     * get the current game-speed multiplier
     * @return the current game speed multiplier
     */
    public int getGameSpeed(){return gameSpeed;}

    /**
     * get the current running game information
     * @return
     */
    public static GameData getInstance() {
        if(gameData == null) {
            gameData = new GameData();
        }
        return gameData;
    }

    /**
     * set the map of the current game
     * @param map what map to se the game to
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * get which map is currently active
     * @return (Map) which hold the current game-field data.
     */
    public Map getMap() {
        return map;
    }

    public Player getPlayer(){return player;}
    public void setPlayer(Player inputPlayer){player = inputPlayer;}

    public MainMenu getMainMenu(){ return mainMenu;}
    public void setMainMenu(MainMenu mainMenu){ this.mainMenu=mainMenu;}

    public ButtonPanel getButtonPanel(){ return buttonPanel;}
    public void setButtonPanel(ButtonPanel buttonPanel){this.buttonPanel=buttonPanel;}

    public PropertiesPanel getPropertiesPanel(){ return propertiesPanel;}
    public void setPropertiesPanel(PropertiesPanel propertiesPanel){ this.propertiesPanel=propertiesPanel;}

    public Hand getHand() { return hand; }

}
