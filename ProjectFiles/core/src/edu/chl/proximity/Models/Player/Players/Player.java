package edu.chl.proximity.Models.Player.Players;

import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Player.PersistentSave.SaveManager;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.ProximityAudioPlayer;
import edu.chl.proximity.Models.Utils.Settings;

/**
 * @author Hanna Romer
 * @date 2015-04-15
 *
 * A class representing the player.
 *
 * ---
 * 23/04 Modified by Simon. Adding depth to leveling up
 * 28/04 Modified by Simon. Adding level
 * 11/5 Modified by Johan, enables saving information to harddrive && autosave on close
 * 22/05 Modified by Simon. Changed starting resources
 */
public class Player {
    private Resources resources;
    private Faction faction;
    private int experiencePoints;
    private double level;
    private Settings settings;
    private SaveManager saveManager;
    public static final int winCondition = 40;
    /**
     * Create a new player with starting resources of 300 points,
     * 300 lines and 0 polygons
     * @param faction the players faction
     */
    public Player(Faction faction){
        this.faction=faction;
        //Standard settings are set
        this.settings = new Settings();
        ProximityAudioPlayer.setSettings(settings);

        this.saveManager = new SaveManager();
        initiateNewMap();

        loadSaveData(1);
        createSaveHook(1);
    }

    /**
     * writes to save file if the player has won a map, and which wave he was on
     * @param name the name of the map
     * @param waveIndex the wave the player reached
     */
    public void documentPlayerProgress(String name, int waveIndex){
        float previousWon = 0;
        if (saveManager.get(name) != null){
            previousWon = saveManager.get(name);
        }
        if (waveIndex > previousWon){
            saveManager.write(name, (float)waveIndex);
        }
    }


    /**
     * check if the player has won a map
     * @param map which map to check
     * @return true if the player has reached a win condition (a certain wave index)
     */
    public boolean hasPlayerWonPreviousMap(Map map){
        if (map.getPreviousMapName() == null) return true;
        String previousMapName = map.getPreviousMapName();
        float wonNumber = 0;
        if (saveManager.get(previousMapName) != null){
            wonNumber = saveManager.get(previousMapName);
        }
        return (wonNumber >=winCondition);
    }

    /**
     * get how many waves a player has reached on a specific map
     * @param map what map to check
     * @return a double of the wave index the player has reached
     */
    public double howManyWavesHasPlayerReached(Map map){
        double progress = 0;
        if (map != null){
            if (saveManager.get(map.getName()) != null){
                progress = saveManager.get(map.getName());
            }
        }
        return progress;
    }
    /**
     * What should be loaded on start
     */
    private void loadSaveData(int saveFile){
        saveManager.loadSave(saveFile);
        if(saveManager.get("exp") != null)  this.experiencePoints = Math.round(saveManager.get("exp"));
        if(saveManager.get("lvl") != null)  this.level = saveManager.get("lvl");
    }

    /**
     * Specify what you want to save on shutdown
     */
    private void saveAllSpecifiedVariables(){
        saveManager.write("exp", (float) experiencePoints);
        saveManager.write("lvl", (float) level);
    }

    /**
     * create a new thread which will run on program shutdown, which saves the current player details to the disk
     * Do not edit this method if you simply want to save data, instead add a line to the methodd
     * "saveAllSpecifiedVariables()"
     *
     * @param saveFileNumber What savefile should be written to on shutdown
     */
    private void createSaveHook(final int saveFileNumber){

        Thread shutdownHook = new Thread("Proximity-Shutdown-Save-Logic" ) {
            public void run() {
                saveAllSpecifiedVariables();
                saveManager.save(saveFileNumber);
            }
        };
        Runtime.getRuntime().addShutdownHook( shutdownHook );
    }

    public void initiateNewMap() {
        resources = new Resources(150, 150, 0);
    }

    /**
     * Change the players faction
     * @param f the player's new faction
     */
    public void setFacton(Faction f){
        this.faction=f;
    }

    /**
     * Get the player's current faction
     * @return the player's faction
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Adds xp to the player. this method in turn handles leveling up
     */
    public void addExperiencePoints(int xp) {
        experiencePoints += xp;
        level = (Math.sqrt(100 * (2 * experiencePoints + 25)) + 50)/100;
    }

    /**
     * Get the player's instance of Resources
     * @return the player's Resources
     */
    public Resources getResources() {
        return resources;
    }

    public void addResources(Resources added) {
        resources.addResources(added);
    }
    //Helper method to check if the player can afford an item
    public boolean canPlayerAfford(Resources res) {
        boolean result = resources.getPoints() >= res.getPoints() && resources.getLines() >= res.getLines() && resources.getPolygons() >= res.getPolygons();
        return result;
    }

    //Returns XP
    public int getExperience() {
        return experiencePoints;
    }
    public double getLevel() {
        return level;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

}
