package peripherals.table;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import scene.SceneGraph;

/**
 * Created by vasily on 05/12/15.
 */
public class TableLeg extends SceneGraph {
    private final double legRadius;
    private final double legHeight;
    private final int slices;
    private final GLUquadric quadric;
    private final Texture tableLegTexture;

    public TableLeg(double x, double y, double z, double legHeight, double legRadius, int slices, Texture t) {
        super(x,y,z);
        this.legRadius = legRadius;
        this.legHeight = legHeight;
        this.slices = slices;
        this.tableLegTexture = t;

        quadric = glu.gluNewQuadric();
        glu.gluQuadricTexture(quadric, true);
        glu.gluQuadricNormals(quadric, glu.GLU_SMOOTH);
    }

    @Override
    public void draw(GL2 gl) {
        tableLegTexture.enable(gl);
        tableLegTexture.bind(gl);
        glu.gluCylinder(quadric, legRadius, legRadius, legHeight, slices, slices);
        tableLegTexture.disable(gl);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(-90, 1, 0, 0);
    }
}
