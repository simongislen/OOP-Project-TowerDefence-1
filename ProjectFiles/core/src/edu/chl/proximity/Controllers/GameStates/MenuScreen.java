package edu.chl.proximity.Controllers.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sun.prism.image.ViewPort;
import edu.chl.proximity.Controllers.MainController;
import edu.chl.proximity.Controllers.MainMenuController;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Viewers.MenuRenderer;

/**
 * @author Johan Swanberg
 * @date 2015-04-07
 *
 * A class for handling the MenuScreen, the screen for the menu
 *
 * 08/04 Modified by Johan Swanberg. Switch to Screen from GameState.
 * 25/04 modified by Hanna R�mer. Added Game,MainMenu, SpriteBatch, ShapeRenderer,MenuRenderer,MainController, OrthographicCamera and FitViewport
 */
public class MenuScreen implements Screen {

    private Game game;
    private MainMenu mainMenu;
    private SpriteBatch batch = new SpriteBatch();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private MenuRenderer menuRenderer;
    private MainMenuController mainMenuController;
    private OrthographicCamera camera;
    private FitViewport viewport;




    public MenuScreen(Game g, MainMenu mainMenu, Map map, Player player){
        this.game = g;
        this.mainMenu=mainMenu;

        GameData.getInstance().setMainMenu(mainMenu);
        GameData.getInstance().setPlayer(player);
        //GameData.getInstance().setMap(map);

        menuRenderer=new MenuRenderer();
        fixCamera();


        mainMenuController=new MainMenuController();
        mainMenuController.setMainMenu(mainMenu);

        shapeRenderer.setAutoShapeType(true);
        Gdx.input.setInputProcessor(mainMenuController);

    }

    /**
     * Attatches a camera object that views the game-data and interprets it as visual information, and a viewport
     * that scales this information to fit the screen. These objects are part of the LibGDX library.
     */
    private void fixCamera(){
        camera = new OrthographicCamera();//Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        viewport.apply();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        menuRenderer.render(batch, shapeRenderer);
        batch.end();

        //stage.act(delta);
        //stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
