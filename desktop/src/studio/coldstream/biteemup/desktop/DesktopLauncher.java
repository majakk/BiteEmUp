package studio.coldstream.biteemup.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import studio.coldstream.biteemup.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "BiteEmUp";
        cfg.useGL30 = true;
        cfg.height = 720;
        cfg.width = 1280;

		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
