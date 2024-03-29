package peripherals.tray;

import peripherals.glass.GlassFlume;
import scene.SceneGraph;

/**
 * Created by vasily on 30/11/15.
 */
public class Tray extends SceneGraph{

    private int objectNum;

    public Tray(double forearmRadius, int slices) {
        super(0, forearmRadius*0.5, 0);
        this.objectNum = 6;

        addChild(new TraySurface(forearmRadius, slices));

        double zPos = -0.75;
        double xPos = 0.8;
        for (int i=0; i< objectNum; i++){
            if (i == 3) {
                zPos++;
                xPos = 0.8;
            }
            addChild(new GlassFlume(xPos, zPos, forearmRadius*1.5, slices));
            xPos++;
        }
    }
}