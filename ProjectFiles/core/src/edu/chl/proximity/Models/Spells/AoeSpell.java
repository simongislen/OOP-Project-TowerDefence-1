package edu.chl.proximity.Models.Spells;

import edu.chl.proximity.Models.Creeps.Creep;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Maps.Map;
import edu.chl.proximity.Models.Towers.Tower;
import edu.chl.proximity.Utilities.Area;

import java.util.List;

/**
 * @author Johan Swanberg
 * @date 2015-04-02
 *
 * A class that allows you to affect creeps/towers/projectiles in an area.
 */


public abstract class AoeSpell extends Spell{

    private Area area;
    public AoeSpell(int i, Area area){
        super(i);
        this.area =area;
    }


    /**
     * call this method if you want the concrete aoeSpell to devolve all creeps within a polygon
     * @param i how many steps the creeps should devolve. (1 to devolve each creep 1 step)
     */
    public void devolveCreepsInAoe(int i){
        List<Creep> creepList = GameData.getInstance().getMap().getCreeps();
        for(int times = 0; times<i; times++){
            for(Creep creep: creepList){
                if (area.contains(creep.getPosition())){ //point.getx returns a double, even though its stored as an int - bad java implementation
                    creep.devolve();
                }
            }
        }


    }

    /**
     * call this method if you want the concrete aoeSpell to slow all creeps in the polygon
     * @param amount how many % to slow the creep
     */
    public void slowCreepsInAoe(double amount, int frames){
        List<Creep> creepList = GameData.getInstance().getMap().getCreeps();

        for(Creep creep: creepList){
            if (area.contains(creep.getPosition())){ //point.getx returns a double, even though its stored as an int - bad java implementation
                creep.slowDown(amount,frames);
            }
        }

    }


    /**
     * Change the attackspeed of the towers in the area (by chaning the reload variable)
     * @param change how much % the attackspeed should be (1 would keep the attackspeed, 2 would make it double the speed.)
     */
    public void speedUpTowersInAoe(double change){
        List<Tower> towers = GameData.getInstance().getMap().getTowers();

        for(Tower tower : towers){
            if (area.contains(tower.getPosition())){
                //todo make reloadtime a variable and enable chaning it
            }

        }
    }
}
