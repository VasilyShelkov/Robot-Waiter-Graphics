package peripherals.TopHat;

import com.jogamp.opengl.GL2;
import notmine.Light;
import scene.SceneGraph;
import scene.styling.Obsidian;

/**
 * Created by vasily on 07/12/15.
 */
public class TopHat extends SceneGraph {
    public TopHat(double headHeight, int slices, Light l) {
        super(0, headHeight*0.7, 0, new Obsidian());
        double hatRadius = headHeight * 1.1;
        double brimHeight = (hatRadius*1.3)*0.03;
        addChild(new Brim(hatRadius*1.3, slices));

        addChild(new HatTop(brimHeight, hatRadius, headHeight*1.3, slices, l));
    }
}
