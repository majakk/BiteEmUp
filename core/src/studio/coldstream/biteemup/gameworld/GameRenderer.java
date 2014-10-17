package studio.coldstream.biteemup.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import studio.coldstream.biteemup.gamehelpers.AssetLoader;
import studio.coldstream.biteemup.gamehelpers.InputHandler;
import studio.coldstream.biteemup.gameobjects.Edible;
import studio.coldstream.biteemup.tweenaccessors.Value;
import studio.coldstream.biteemup.tweenaccessors.ValueAccessor;
import studio.coldstream.biteemup.ui.SimpleButton;

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

    // Game Objects
    private Edible edible;

    // Tween stuff
    private TweenManager manager;
    private Value alpha = new Value();

    // Buttons
    private List<SimpleButton> menuButtons;

    public GameRenderer(GameWorld world, int midPointX, int midPointY) {
        myWorld = world;
        this.midPointX = midPointX;
        this.midPointY = midPointY;

        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
                .getMenuButtons();

        batcher = new SpriteBatch();
        shape = new ShapeRenderer();
        //font = new BitmapFont();

        initGameObjects();
        initAssets();
        setupTweens();
    }

    private void setupTweens() {
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
                .start(manager);
    }

    private void drawEdible(float runTime) {
        //Draw the shadow(s) first
        shadowSprite.draw(batcher);

        for(int i = 0; i < 4 * edible.getBlackBleed(); i++) {
            shadowSprite.setPosition(edible.getX() + (int)(edible.getBlackBleed() * Math.cos(i * (Math.PI / (2*edible.getBlackBleed())))),
                    edible.getY() + (int)(edible.getBlackBleed() * Math.sin(i * (Math.PI / (2*edible.getBlackBleed())))));
            shadowSprite.draw(batcher);
        }

        //Draw the edible on top
        edSprite.draw(batcher);
    }

    private void drawReady() {
        AssetLoader.font2.draw(batcher, "READY?!", 800, 1000);
    }

    private void drawScore() {
        AssetLoader.font2.draw(batcher, "TIME: " + String.valueOf((myWorld.endTime - myWorld.startTime)/1000.0f), 800, 1000);
    }

    private void drawMenuShape() {
        for (SimpleButton button : menuButtons) {
            button.drawShape(shape);
        }
    }

    private void drawMenuText() {
        /*batcher.draw(AssetLoader.zbLogo, 136 / 2 - 56, midPointY - 50,
                AssetLoader.zbLogo.getRegionWidth() / 1.2f,
                AssetLoader.zbLogo.getRegionHeight() / 1.2f);*/

        for (SimpleButton button : menuButtons) {
            button.drawText(batcher, "Play");
        }
    }

    private void drawDebug(float runTime) {
        AssetLoader.font.draw(batcher, "runTime: " + String.valueOf(runTime), 50, 50);
        AssetLoader.font.draw(batcher, "currentPoints: " + String.valueOf(edible.getCurrentPoints()) + " / " + String.valueOf(edible.getFullPoints()), 50, 100);
    }

    public void render(float delta, float runTime) {
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

        //Draw Shapes
        if (myWorld.isMenu()) {
            drawMenuShape();
            //drawScore();
        }

        //Draw sprites
        batcher.begin();

        //Debug text
        drawDebug(runTime);

        //States dependent drawing
        if (myWorld.isRunning()) {
            drawEdible(runTime);
            //drawScore();
        } else if (myWorld.isReady()) {
            drawEdible(runTime);
            drawReady();
        } else if (myWorld.isMenu()) {
            drawEdible(runTime);
            drawMenuText();
        } else if (myWorld.isGameOver()) {
            drawEdible(runTime);
            drawScore();
        } else if (myWorld.isHighScore()) {
            drawEdible(runTime);
            drawScore();
        }

        batcher.end();

        //Smooth transition of this screen at the very start
        drawTransition(delta);
    }


    private void initGameObjects() {
        edible = myWorld.getEdible();
    }

    private void initAssets() {
        edSprite = new Sprite(AssetLoader.img);
        shadowSprite = new Sprite(AssetLoader.img);
    }

    private void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
            Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(0, 0, 0, alpha.getValue());
            shape.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shape.end();
            Gdx.gl.glDisable(Gdx.gl20.GL_BLEND);

        }
    }

}