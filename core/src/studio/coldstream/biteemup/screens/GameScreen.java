package studio.coldstream.biteemup.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import studio.coldstream.biteemup.gamehelpers.InputHandler;
import studio.coldstream.biteemup.gameworld.GameRenderer;
import studio.coldstream.biteemup.gameworld.GameWorld;

/**
 * Created by majac on 2014-10-10.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = Gdx.graphics.getWidth(); //Set this to the wanted width
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointX = (int) (gameWidth / 2);
        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointX, midPointY);
        renderer = new GameRenderer(world, midPointX, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }



    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
        //FPS check
        //Gdx.app.log("GameScreen FPS", (1/delta) + "");
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }




}
