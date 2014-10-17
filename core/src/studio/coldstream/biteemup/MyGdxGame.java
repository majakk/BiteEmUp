package studio.coldstream.biteemup;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import studio.coldstream.biteemup.gamehelpers.AssetLoader;
import studio.coldstream.biteemup.screens.GameScreen;
import studio.coldstream.biteemup.screens.SplashScreen;

public class MyGdxGame extends Game {

	@Override
	public void create () {
        Gdx.app.log("MyGdxGame", "created");
        AssetLoader.load();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}
