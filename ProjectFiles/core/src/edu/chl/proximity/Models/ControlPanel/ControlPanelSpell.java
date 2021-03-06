package edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.MouseOver.MouseOverBox;
import edu.chl.proximity.Models.Map.Spells.Cooldown;
import edu.chl.proximity.Models.Map.Spells.Spell;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 *
 * ControlPanelSpell is a class that can display a Spell
 *
 * ---
 * 24/05 modified by Linda Evaldsson. This class can now handle a little bit more of cooldowns through the Cooldown class.
 */
public class ControlPanelSpell extends BoardObject {

    Spell spell;

    private Cooldown cooldown;
    private MouseOverBox hoverBox;

    public ControlPanelSpell(ProximityVector position, Spell spell) {
        super(position, spell.getControlPanelImage(), 0);

        this.spell = spell;
        cooldown = spell.getCooldown();
        spell.setPosition(position);
        hoverBox = new MouseOverBox(150, spell.getHelpInfo());
        spell.resetCooldown();
    }
    public int getCooldownPercent() {
        return cooldown.getCooldownPercent();
    }


    public void updateCooldown() {

        cooldown.update();

    }

    /**
     * Returns a copy of the tower in this ControlPanelTower
     * @return a copy of tower
     */
    public Spell getSpell(){

        try {
            return (Spell)spell.clone();
        } catch (CloneNotSupportedException e) {
        }
        return null;
    }

    public void hover(ProximityVector position) {
        hoverBox.enable(position);
    }

}