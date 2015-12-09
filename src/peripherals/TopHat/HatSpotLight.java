package peripherals.TopHat;

import com.jogamp.opengl.GL2;
import notmine.Light;
import scene.SceneGraph;

/**
 * Created by vasily on 07/12/15.
 */
public class HatSpotLight extends SceneGraph {
    public HatSpotLight(double topRadius, double topHeight, Light l) {
        super(0, topHeight/2, topRadius*0.8);
        this.addLight(l);
    }
}
