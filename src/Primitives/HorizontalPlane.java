package Primitives;

import com.jogamp.opengl.GL2;

/**
 * Created by vasily on 02/12/15.
 */
public class HorizontalPlane {

    private Quad[] quads;
    private int dlist;

    public HorizontalPlane(int centreX, int centreZ, double width, double depth, int xSlices, int zSlices) {
        int quadOffset = 0;
        double startX = centreX-(width/2.0);
        double startZ = centreZ-(depth/2.0);
        double stepX = width/(xSlices-1);
        double stepZ = width/(zSlices-1);

        int quadsSize = (xSlices)*(zSlices);
        quads = new Quad[quadsSize];

        for(int i=0; i<xSlices; i++) {
            for (int j = 0; j < zSlices; j++) {
                Vertex v1 = new Vertex(startX + (i * stepX), 0, startZ + ((j + 1) * stepZ));
                Vertex v2 = new Vertex(startX + ((i + 1) * stepX), 0, startZ + ((j + 1) * stepZ));
                Vertex v3 = new Vertex(startX + ((i + 1) * stepX), 0, startZ + (j * stepZ));
                Vertex v4 = new Vertex(startX + (i * stepX), 0, startZ + (j * stepZ));

                quads[quadOffset] = new Quad(v1, v2, v3, v4);
                quadOffset++;
            }
        }
    }

    public void initializeDisplayList(GL2 gl){
        dlist = gl.glGenLists(1);
        gl.glNewList(dlist, GL2.GL_COMPILE);
        for (Quad quad:quads) {
            quad.render(gl);
        }
        gl.glEndList();
    }

    public void render(GL2 gl) {
        gl.glCallList(dlist);
    }
}
