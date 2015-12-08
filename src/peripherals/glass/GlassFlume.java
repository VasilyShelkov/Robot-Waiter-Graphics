package peripherals.glass;

import scene.SceneGraph;

/**
 * Created by vasily on 01/12/15.
 */
public class GlassFlume extends SceneGraph{
    double zPos;
    double xPos;

    public GlassFlume(double x, double z, double forearmRadius, int slices) {
        this.xPos = x;
        this.zPos = z;

        double xPosition = forearmRadius*5.5-xPos*forearmRadius*3;
        double zPosition = forearmRadius*5.5-zPos*forearmRadius*3;
        addChild(new Base(xPosition, forearmRadius*0.1, zPosition, forearmRadius*1.5, forearmRadius*0.6, slices));
    }
}
