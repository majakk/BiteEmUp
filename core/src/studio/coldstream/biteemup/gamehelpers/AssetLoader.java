package studio.coldstream.biteemup.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by majac on 2014-10-10.
 */
public class AssetLoader {

    public static Texture logoTexture, img;
    public static TextureRegion logo;
    public static Pixmap pm;
    public static Sound wavSound1, wavSound2, wavSound3;
    public static BitmapFont font, font2;

    private static Preferences prefs;

    public static void load() {
        logoTexture = new Texture(Gdx.files.internal("gfx/new3_big.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo = new TextureRegion(logoTexture, 0, 0, 128, 128);

        img = new Texture("gfx/fig.png");
        pm = new Pixmap(Gdx.files.internal("gfx/fig.png"));

        wavSound1 = Gdx.audio.newSound(Gdx.files.internal("sounds/bite1.wav"));
        wavSound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/bite2.wav"));
        wavSound3 = Gdx.audio.newSound(Gdx.files.internal("sounds/bite3.wav"));

        font2 = new BitmapFont(Gdx.files.internal("fonts/impact_72.fnt"),false);
        font = new BitmapFont();
        font.setScale(2.0f,2.0f);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("BiteEmUp");

        if (!prefs.contains("bestTime")) {
            prefs.putLong("bestTime", 0);
        }
    }

    public static void setHighScore(long val) {
        prefs.putLong("bestTime", val);
        prefs.flush();
    }

    public static long getHighScore() {
        return prefs.getLong("bestTime");
    }


    public static void dispose() {
        // We must dispose of the texture when we are finished.
        img.dispose();
        pm.dispose();

        wavSound1.dispose();
        wavSound2.dispose();
        wavSound3.dispose();

        font.dispose();
        font2.dispose();
    }

}
