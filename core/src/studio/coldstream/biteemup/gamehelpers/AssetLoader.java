package studio.coldstream.biteemup.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by majac on 2014-10-10.
 */
public class AssetLoader {

    public static Texture img;
    public static Pixmap pm;

    public static Sound wavSound1, wavSound2, wavSound3;

    public static BitmapFont font, font2;

    public static void load() {
        img = new Texture("gfx/fig.png");
        pm = new Pixmap(Gdx.files.internal("gfx/fig.png"));

        wavSound1 = Gdx.audio.newSound(Gdx.files.internal("sounds/bite1.wav"));
        wavSound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/bite2.wav"));
        wavSound3 = Gdx.audio.newSound(Gdx.files.internal("sounds/bite3.wav"));

        font2 = new BitmapFont(Gdx.files.internal("fonts/impact_72.fnt"),false);
        font = new BitmapFont();
        font.setScale(2.0f,2.0f);
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
