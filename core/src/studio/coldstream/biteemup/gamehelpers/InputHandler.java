package studio.coldstream.biteemup.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.List;

import studio.coldstream.biteemup.gameobjects.Edible;
import studio.coldstream.biteemup.gameworld.GameWorld;
import studio.coldstream.biteemup.ui.SimpleButton;

/**
 * Created by majac on 2014-10-10.
 */
public class InputHandler implements InputProcessor {
    private GameWorld myWorld;
    private Edible myEdible;

    private List<SimpleButton> menuButtons;
    private SimpleButton playButton;


    // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler(GameWorld inWorld) {
        // myEdible now represents the gameWorld's edible.
        this.myWorld = inWorld;
        myEdible = myWorld.getEdible();

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
                200,500,
                400, 100);
        menuButtons.add(playButton);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("InputHandler", "touchDown");

        if (myWorld.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
        } else if (myWorld.isReady()) {
            myWorld.start();
        }

        if(myWorld.isRunning())
            myEdible.onClick(screenX, screenY);

        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            // Reset all variables, go to GameState.READY
            myWorld.restart();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                myWorld.ready();
                return true;
            }
        }
        return false;
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

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

}