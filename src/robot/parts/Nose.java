package robot.parts;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import scene.SceneGraph;
import scene.styling.Gold;

/**
 * Created by vasily on 05/12/15.
 */
public class Nose extends SceneGraph {
    private final double noseBaseRadius;
    private final double noseHeight;
    private final int slices;
    private final GLUquadric quadric;
    private final Texture noseTexture;

    public Nose(double x, double y, double z, double noseBaseRadius, double noseHeight, int slices, Texture t) {
        super(x,y,z, new Gold());
        this.noseBaseRadius = noseBaseRadius;
        this.noseHeight = noseHeight;
        this.slices = slices;
        this.noseTexture = t;

        quadric = glu.gluNewQuadric();
        glu.gluQuadricTexture(quadric, true);
        glu.gluQuadricNormals(quadric, glu.GLU_SMOOTH);
    }

    @Override
    public void draw(GL2 gl) {
        noseTexture.enable(gl);
        noseTexture.bind(gl);
        glu.gluCylinder(quadric, noseBaseRadius, 0, noseHeight, slices, slices);
        noseTexture.disable(gl);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
    }
}
