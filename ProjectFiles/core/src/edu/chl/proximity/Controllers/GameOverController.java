package edu.chl.proximity.Controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.MenuModels.FactionChooser.FactionChooser;
import edu.chl.proximity.Models.MenuModels.MapSelect.MapSelect;
import edu.chl.proximity.Models.MenuModels.StartButton;
import edu.chl.proximity.Models.WonLostModels.GameOver;
import edu.chl.proximity.Utilities.ProximityVector;

/**
 * @author Hanna R�mer
 * @date 2015-05-15
 */
public class GameOverController implements InputProcessor{
    private Viewport viewport;
    private GameOver gameOver;

    public GameOverController(Viewport v, GameOver go){
        viewport=v;
        gameOver=go;
    }

    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        Vector2 pos = viewport.unproject(new Vector2(x,y));
        ProximityVector translatedPosition = new ProximityVector(pos.x,pos.y);
        gameOver.pressedButtonOnPosition(translatedPosition);
        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved (int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }
}