package Primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import notmine.Light;
import peripherals.hangingspotlight.CeilingLamp;

/**
 * Created by vasily on 02/12/15.
 */
public class HorizontalPlane extends Plane {

    private final double height;

    public HorizontalPlane(double height, double width, double depth, int xSlices, int zSlices, int[] normals, Texture t) {
        this.height = height;
        int quadOffset = 0;
        double startX = -width/2.0;
        double startZ = -depth/2.0;
        double stepX = width/(xSlices-1);
        double stepZ = depth/(zSlices-1);

        int quadsSize = (xSlices-1)*(zSlices-1);
        quads = new Quad[quadsSize];

        for(int i=0; i<xSlices-1; i++) {
            for (int j = 0; j < zSlices-1; j++) {
                Vertex v1 = new Vertex(startX + (i * stepX), 0, startZ + ((j + 1) * stepZ));
                Vertex v2 = new Vertex(startX + ((i + 1) * stepX), 0, startZ + ((j + 1) * stepZ));
                Vertex v3 = new Vertex(startX + ((i + 1) * stepX), 0, startZ + (j * stepZ));
                Vertex v4 = new Vertex(startX + (i * stepX), 0, startZ + (j * stepZ));

                quads[quadOffset] = new Quad(v1, v2, v3, v4, normals, t);

                quadOffset++;
            }
        }
    }

    public HorizontalPlane(double height,
                           double width, double depth,
                           int xSlices, int zSlices, int[] normals, Texture t,
                           Light spotlight1, Light spotlight2) {
        this(height, width, depth, xSlices, zSlices, normals, t);

        //left Ceiling Light
        CeilingLamp ceilingLamp1 = new CeilingLamp(-(width / 4), height, 0, width, spotlight1);
        addChild(ceilingLamp1);

        //right Ceiling Light
        CeilingLamp ceilingLamp2 = new CeilingLamp((width / 4), height, 0, width, spotlight2);
        addChild(ceilingLamp2);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        if(this.height > 0) {
            gl.glRotated(180, 1, 0, 0);
            gl.glTranslated(0, -height, 0);
        }
    }
}
