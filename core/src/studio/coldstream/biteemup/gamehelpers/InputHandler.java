package studio.coldstream.biteemup.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import studio.coldstream.biteemup.gameobjects.Edible;
import studio.coldstream.biteemup.gameworld.GameWorld;

/**
 * Created by majac on 2014-10-10.
 */
public class InputHandler implements InputProcessor {
    private GameWorld myWorld;
    private Edible myEdible;

    // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler(GameWorld inWorld) {
        // myEdible now represents the gameWorld's edible.
        this.myWorld = inWorld;
        myEdible = myWorld.getEdible();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("InputHandler", "touchDown");

        if (myWorld.isReady()) {
            myWorld.start();
        }

        myEdible.onClick(screenX, screenY);

        if (myWorld.isGameOver()) {
            // Reset all variables, go to GameState.READ
            myWorld.restart();
        }

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}