package studio.coldstream.biteemup.gameobjects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by majac on 2014-10-10.
 */
public class Bite {
    private static int numOfTeeth = 20;
    private static int toothSize = 20;
    private static int blackBleed = toothSize / 4;
    private static int mouthR = (int)((numOfTeeth * toothSize) / 3.14f);

    private int bitePosX;
    private int bitePosY;
    private Vector2 position;
    private Vector2 centre;
    private double biteDirection;
    private Vector2 farPoint;

    private Pixmap pm;

    //Constructor
    public Bite(Pixmap pix){
        this.pm = pix;
        position = new Vector2();
        centre = new Vector2();
        farPoint = new Vector2();
    }

    public boolean doBite(int x, int y, Vector2 pos, Vector2 cen){
        this.bitePosX = x;
        this.bitePosY = y;
        this.position = pos;
        this.centre = cen;
        this.biteDirection = getBiteDirection(x, y, cen);

        //Debug line
        //pm.setColor(1,1,1,1);
        //pm.drawLine(pm.getHeight() / 2, pm.getWidth() / 2,(pm.getHeight() / 2) - (int)(200*Math.sin(biteDirection)),(pm.getWidth() / 2) - (int)(200*Math.cos(biteDirection)));

        //Some stepping code here
        int stepcount = 0;
        int directHit = 0;
        //calculate a far point
        this.farPoint.x = cen.x - 2*Math.abs(cen.x - pos.x)*(float)Math.sin(biteDirection);
        this.farPoint.y = cen.y - 2*Math.abs(cen.x - pos.x)*(float)Math.cos(biteDirection);

        //set a deltaStep?
        float deltaStep = 4;

        //pm.fillCircle((int)farPoint.x,(int)farPoint.y,10);
        //pm.setColor(1,0,0,1);
        while(getBiteCoverage((int)this.farPoint.x,(int)this.farPoint.y) < 5 && directHit == 0 && stepcount < 150){
            stepcount++;
            this.farPoint.x += deltaStep*(float)Math.sin(biteDirection);
            this.farPoint.y += deltaStep*(float)Math.cos(biteDirection);
            directHit = (Math.abs(pm.getPixel((int)farPoint.x, (int)farPoint.y) >> 8) > 1 ? 1 : 0);
            //pm.fillCircle((int)farPoint.x,(int)farPoint.y,2);
        }

        if(stepcount >= 150){
            farPoint.x = bitePosX;
            farPoint.y = bitePosY;
        }

        //Alter the pixmap to remove the bite
        pm.setColor(1, 1, 1, 0);
        pm.setBlending(Pixmap.Blending.None);
        for (int i = 0; i < numOfTeeth; i++) {
            pm.fillCircle((int)farPoint.x - (int) position.x + (int) (mouthR * Math.cos(i * ((2 * Math.PI) / numOfTeeth))),
                    (int)farPoint.y - (int) position.y + (int) (mouthR * Math.sin(i * ((2 * Math.PI) / numOfTeeth))), toothSize + 1);
        }
        pm.fillCircle((int)farPoint.x  - (int) position.x, (int)farPoint.y  - (int) position.y, mouthR);

        return true;

    }

    public double getBiteDirection(int x, int y, Vector2 cen){
        return Math.atan2(cen.x - x,cen.y - y);
    }

    public int getBiteCoverage(int x, int y){
        int toothPix = 0;
        for(int i = 0; i < numOfTeeth; i++) {
            toothPix += (Math.abs(pm.getPixel(x - (int)position.x + (int)(mouthR * Math.cos(i * ((2*Math.PI) / numOfTeeth))),
                    y - (int)position.y + (int)(mouthR * Math.sin(i * ((2*Math.PI) / numOfTeeth)))) >> 8) > 1 ? 1 : 0);
        }
        return toothPix;
    }

    public void setToothSize(int ts){
        toothSize = ts;
    }

    public int getToothSize(){
        return toothSize;
    }

    public int getBlackBleed(){
        return blackBleed;
    }

    public int getMouthR(){
        return mouthR;
    }

}
