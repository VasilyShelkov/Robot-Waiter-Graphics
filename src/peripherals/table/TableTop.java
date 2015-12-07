package peripherals.table;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import scene.SceneGraph;

/**
 * Created by vasily on 05/12/15.
 */
public class TableTop extends SceneGraph {
    private final double tableLength;
    private final double tableWidth;
    private final GLUquadric quadric;
    private final int slices;
    private final Texture tableTopTexture;

    public TableTop(double x, double y, double z, double tableWidth, double tableLength, int slices, Texture t) {
        super(x,y,z);
        this.tableWidth = tableWidth;
        this.tableLength = tableLength;
        this.tableTopTexture = t;
        this.slices = slices;

        quadric = glu.gluNewQuadric();
        glu.gluQuadricTexture(quadric, true);
        glu.gluQuadricNormals(quadric, glu.GLU_SMOOTH);
    }

    @Override
    public void draw(GL2 gl) {
        tableTopTexture.enable(gl);
        tableTopTexture.bind(gl);
        gl.glScaled(2, 0.02, tableLength/2);
        glu.gluSphere(quadric, tableWidth/2, slices, slices);
        tableTopTexture.disable(gl);
    }
}
