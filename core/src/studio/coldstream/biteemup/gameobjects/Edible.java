package studio.coldstream.biteemup.gameobjects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

import studio.coldstream.biteemup.gamehelpers.AssetLoader;

/**
 * Created by majac on 2014-10-10.
 */
public class Edible {

    private Vector2 position;
    private Vector2 centre;
    //private float rotation;
    private int width;
    private int height;

    private Pixmap pm;

    private boolean biteFlag;
    private Bite bite;
    private int touches;

    private int fullPoints;
    private int currentPoints;
    private int previousPoints;

    public Edible(Pixmap pix, float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        this.position = new Vector2(x - width/2, y - height/2);
        this.centre = new Vector2(x,y);
        bite = new Bite(pix);
        this.pm = pix;
        fullPoints = currentPoints = calculatePoints();
        this.touches = 0;
        this.biteFlag = false;
    }

    private int calculatePoints() {
        int summer = 0;
        for(int i = 0; i <= pm.getHeight(); i+=8)
            for(int j = 0; j <= pm.getWidth(); j+=8)
                summer += (Math.abs(pm.getPixel(i,j) >> 8) > 1 ? 1 : 0);

        return summer;
    }

    public void update(float delta) {
        //Update its properties, rotate, scale, etc - its not being drawn here!

    }

    public void onClick(int x, int y) {
        //Run calculations on the pixmap


        //Bite it! (Transform the pixmap)
        biteFlag = true;
        biteSound();
        bite.doBite(x, y, position, centre);
        touches++; //touches will not be the same as bites later on as not all touches leads to bites
        previousPoints = currentPoints;
        currentPoints = calculatePoints();
    }

    public void biteSound(){
        //sounds a bite
        if(System.currentTimeMillis() % 3 == 0)
            AssetLoader.wavSound1.play();
        else if(System.currentTimeMillis() % 3 == 1)
            AssetLoader.wavSound2.play();
        else
            AssetLoader.wavSound3.play();
    }

    public boolean isbitten(){
        return biteFlag;
    }

    public void resetBite(){
        biteFlag = false;
    }

    public void onRestart(){
        AssetLoader.load();
        bite = new Bite(AssetLoader.pm);
        this.pm = AssetLoader.pm;
        fullPoints = currentPoints = calculatePoints();
        this.touches = 0;
        biteFlag = true;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    /*public float getRotation() {
        return rotation;
    }*/

    public float getBlackBleed(){
        return bite.getBlackBleed();
    }

    public int getCurrentPoints(){
        return currentPoints;
    }

    public int getFullPoints(){
        return fullPoints;
    }

    public int getDeltaPoints(){
        return previousPoints - currentPoints;
    }

    public int getCurrentTouches() {return touches; }


}
