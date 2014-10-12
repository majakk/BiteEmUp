package studio.coldstream.biteemup.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import studio.coldstream.biteemup.gamehelpers.AssetLoader;
import studio.coldstream.biteemup.gameobjects.Edible;

/**
 * Created by majac on 2014-10-10.
 */
public class GameRenderer {
    private GameWorld myWorld;

    private SpriteBatch batcher;
    private Sprite edSprite;
    private Sprite shadowSprite;
    private ShapeRenderer shape;

    private int midPointX;
    private int midPointY;

    private Edible edible;


    public GameRenderer(GameWorld world, int midPointX, int midPointY) {
        myWorld = world;
        this.midPointX = midPointX;
        this.midPointY = midPointY;

        batcher = new SpriteBatch();
        shape = new ShapeRenderer();
        //font = new BitmapFont();

        initGameObjects();
        initAssets();
    }

    public void render(float runTime) {
        //Gdx.app.log("GameRenderer", "render");

        //Clear and draw background
        Gdx.gl.glClearColor(0.6f, 0.6f, 0.8f, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glEnable(Gdx.gl20.GL_BLEND);

        //Get Gameobjects
        edSprite.setPosition(edible.getX(),edible.getY());
        shadowSprite.setPosition(edible.getX(),edible.getY());

        //render to sprite(s) if edible has bittenflag! End by resetting this flag
        if(edible.isbitten()) {
            edSprite.setTexture(new Texture(AssetLoader.pm));
            shadowSprite.setTexture(new Texture(AssetLoader.pm));
            edible.resetBite();

            //finger
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(1, 1, 0, 1);
            shape.circle(Gdx.input.getX(),(Gdx.graphics.getWidth() / 2) - Gdx.input.getY(),100.0f);
            shape.end();
        }

        shadowSprite.setColor(0, 0, 0, 1);
        //shadowSprite.setScale(1.0f, 1.0f);

        //Draw shapes

        //Draw sprites
        batcher.begin();

        //Draw the shadow(s) first
        shadowSprite.draw(batcher);

        for(int i = 0; i < 4 * edible.getBlackBleed(); i++) {
            shadowSprite.setPosition(edible.getX() + (int)(edible.getBlackBleed() * Math.cos(i * (Math.PI / (2*edible.getBlackBleed())))),
                    edible.getY() + (int)(edible.getBlackBleed() * Math.sin(i * (Math.PI / (2*edible.getBlackBleed())))));
            shadowSprite.draw(batcher);
        }

        //Draw the edible on top
        edSprite.draw(batcher);

        //Debug text
        AssetLoader.font.draw(batcher, "runTime: " + String.valueOf(runTime), 50, 50);
        AssetLoader.font.draw(batcher, "currentPoints: " + String.valueOf(edible.getCurrentPoints()) + " / " + String.valueOf(edible.getFullPoints()), 50, 100);

        //State Text
        if(myWorld.isReady()){
            AssetLoader.font2.draw(batcher, "Touch 2 Start!", 800, 1000);
        }
        else if(myWorld.isGameOver()){
            AssetLoader.font2.draw(batcher, "TIME: " + String.valueOf((myWorld.endTime - myWorld.startTime)/1000.0f), 800, 1000);
        }


        batcher.end();
    }

    private void initGameObjects() {
        edible = myWorld.getEdible();
    }

    private void initAssets() {
        edSprite = new Sprite(AssetLoader.img);
        shadowSprite = new Sprite(AssetLoader.img);
    }

}