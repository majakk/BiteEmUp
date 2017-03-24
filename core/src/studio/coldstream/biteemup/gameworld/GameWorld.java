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
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointX, int midPointY){
        currentState = GameState.MENU;
        //currentState = GameState.READY;
        //edible = new Edible(AssetLoader.pm, midPointX,midPointY,512,512);
        edible = new Edible(AssetLoader.pm, midPointX,midPointY,256,256);
    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        switch (currentState){
        case MENU:
        case READY:
            updateReady(delta);
            break;
        case RUNNING:
            updateRunning(delta);
            break;
        default:
            break;
        }


    }

    //Gamestate loop
    private void updateRunning(float delta) {
        edible.update(delta);

        if(edible.getCurrentPoints() == 0 && currentState == GameState.RUNNING) {
            endTime = System.currentTimeMillis();
            currentState = GameState.GAMEOVER;

            if (endTime > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(endTime);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    private void updateReady(float delta) {

    }

    public void start() {
        currentState = GameState.RUNNING;
        startTime = System.currentTimeMillis();
    }

    public void ready() {
        currentState = GameState.READY;
    }

    public void restart() {
        edible.onRestart();
        currentState = GameState.READY;
    }

    public Edible getEdible(){
        return edible;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }



}
