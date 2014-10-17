package studio.coldstream.biteemup.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import studio.coldstream.biteemup.gamehelpers.AssetLoader;

/**
 * Created by majac on 2014-10-14.
 */
public class SimpleButton {

    private float x, y, width, height;

    //private ShapeRenderer shape;
    //private TextureRegion buttonUp;
    //private TextureRegion buttonDown;

    private Rectangle bounds;

    private boolean isPressed = false;

    public SimpleButton(float x, float y, float width, float height/*,
                        TextureRegion buttonUp, TextureRegion buttonDown*/) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //this.buttonUp = buttonUp;
        //this.buttonDown = buttonDown;
        //shape = new ShapeRenderer();

        bounds = new Rectangle(x, y, width, height);

    }

    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }

    public void drawShape(ShapeRenderer shape) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(1, 1, 1, 0.3f);
        shape.rect(x, y, width, height);
        shape.end();
    }

    public void drawText(SpriteBatch batcher, String text) {

        if (isPressed) {
            //batcher.draw(buttonDown, x, y, width, height);
            batcher.setColor(1,1,1,1);
            AssetLoader.font2.draw(batcher, text, x, y + AssetLoader.font2.getCapHeight());
        } else {
            //batcher.draw(buttonUp, x, y, width, height);
            batcher.setColor(0.6f,0.6f,0.6f,1);
            AssetLoader.font2.draw(batcher, text, x, y + AssetLoader.font2.getCapHeight());
        }

    }

    public boolean isTouchDown(int screenX, int screenY) {

        if (bounds.contains(screenX, screenY)) {
            isPressed = true;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {

        // It only counts as a touchUp if the button is in a pressed state.
        if (bounds.contains(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }

        // Whenever a finger is released, we will cancel any presses.
        isPressed = false;
        return false;
    }

}
