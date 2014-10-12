package studio.coldstream.biteemup.gameworld;

import com.badlogic.gdx.Gdx;

import studio.coldstream.biteemup.gamehelpers.AssetLoader;
import studio.coldstream.biteemup.gameobjects.Edible;

/**
 * Created by majac on 2014-10-10.
 */
public class GameWorld {

    private Edible edible;
    public long startTime, endTime;

    public GameState currentState;
    public enum GameState {
        READY, RUNNING, GAMEOVER
    }

    public GameWorld(int midPointX, int midPointY){
        currentState = GameState.READY;
        edible = new Edible(AssetLoader.pm, midPointX,midPointY,512,512);
    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        switch (currentState){
        case READY:
            updateReady(delta);
        case RUNNING:
        default:
            updateRunning(delta);
        }


    }

    private void updateRunning(float delta) {
        edible.update(delta);

        if(edible.getCurrentPoints() == 0 && currentState == GameState.RUNNING) {
            endTime = System.currentTimeMillis();
            currentState = GameState.GAMEOVER;
        }
    }

    private void updateReady(float delta) {

    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
        startTime = System.currentTimeMillis();
    }

    public void restart() {
        edible.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public Edible getEdible(){
        return edible;
    }

}
