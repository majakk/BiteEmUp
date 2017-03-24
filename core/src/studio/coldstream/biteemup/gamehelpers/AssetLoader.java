package studio.coldstream.biteemup.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;

/**
 * Created by majac on 2014-10-10.
 */
public class AssetLoader {

    public static Texture logoTexture, img;
    public static TextureRegion logo;
    public static Pixmap pm, pA;
    public static Sound wavSound1, wavSound2, wavSound3;
    public static BitmapFont font, font2, fontA;

    //public static TextureRegion[] fontA;

    private static Preferences prefs;

    public static void load() {
        logoTexture = new Texture(Gdx.files.internal("gfx/new3_big.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo = new TextureRegion(logoTexture, 0, 0, 128, 128);

        img = new Texture("gfx/ginger1.png");
        pm = new Pixmap(Gdx.files.internal("gfx/ginger1.png"));

        wavSound1 = Gdx.audio.newSound(Gdx.files.internal("sounds/bite1.wav"));
        wavSound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/bite2.wav"));
        wavSound3 = Gdx.audio.newSound(Gdx.files.internal("sounds/bite3.wav"));

        font2 = new BitmapFont(Gdx.files.internal("fonts/impact_72.fnt"),false);
        font = new BitmapFont();
        //font.setScale(2.0f,2.0f);
        font.getData().setScale(2.0f);

        /*FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/tktngtle.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 128;
        param.characters = "A";
        fontA = generator.generateFont(param);

        pm = fontA.getRegion().getTexture().getTextureData().consumePixmap();

        generator.dispose();
        */
        //pm = new Pixmap(fontA[0].getRegionWidth(), fontA[0].getRegionHeight(), Pixmap.Format.RGBA8888);
        //pm = fontA[0].getTexture().getTextureData().consumePixmap();

        //pm = new Pixmap(fontA[0].Width(), fontA[0].getRegionHeight(), Pixmap.Format.RGBA8888);
        //fontA[1].getTexture().draw(pA, fontA[1].getRegionWidth(), fontA[1].getRegionHeight());
                //pA.drawPixmap(textureAtlasPixmap, x, y, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
        //fontA[0].getTexture().getTextureData().prepare();
        //fontA[0].getTexture().

        //pm = new Pixmap(font.getRegion(0).getRegionWidth(), font.getRegion(0).getRegionHeight(), Pixmap.Format.RGBA8888);
        //font.getRegion(0).getTexture().getTextureData().prepare();
        //pm = font.getRegion(0).getTexture().getTextureData().consumePixmap();
        //PixmapTextureData adam;
        //adam.font.getRegion(0).getTexture()


        //font20=generator.generateFont(20);
        //font10=generator.generateFont(10);


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
        pA.dispose();

        wavSound1.dispose();
        wavSound2.dispose();
        wavSound3.dispose();

        font.dispose();
        font2.dispose();


    }

}
