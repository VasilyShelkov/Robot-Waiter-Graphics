package Primitives;

import com.jogamp.opengl.util.texture.Texture;

/**
 * Created by vasily on 05/12/15.
 */
public class VerticalZPlane extends Plane {
    public VerticalZPlane(double centreX, double centreY, double centreZ,
                          double depth, double height, int zSlices, int ySlices, int[] normals,
                          Texture t, double textureUSlices, double textureVSlices) {
        int quadOffset = 0;
        double startZ = centreZ-(depth/2.0);
        double startY = centreY;
        double stepZ = depth/(zSlices-1);
        double stepY = height/(ySlices-1);
        double txStepU = textureUSlices/(zSlices);
        double txStepV = textureVSlices/(ySlices);

        int quadsSize = (zSlices-1)*(ySlices-1);
        quads = new Quad[quadsSize];

        for(int i=0; i<zSlices-1; i++) {
            for (int j = 0; j < ySlices-1; j++) {
                double u = (i * txStepU);
                double u1 = ((i + 1) * txStepU);
                double v5 = (j * txStepV);
                double v = ((j + 1) * txStepV);
                Vertex v1 = new Vertex(centreX, startY + ((j + 1) * stepY), startZ + (i * stepZ), u, v);
                Vertex v2 = new Vertex(centreX, startY + ((j + 1) * stepY), startZ + ((i + 1) * stepZ), u1, v);
                Vertex v3 = new Vertex(centreX, startY + (j * stepY), startZ + ((i + 1) * stepZ), u1, v5);
                Vertex v4 = new Vertex(centreX, startY + (j * stepY), startZ + (i * stepZ), u, v5);

                if(centreX > 0){
                    quads[quadOffset] = new Quad(v4, v3, v2, v1, normals, t);
                }else{
                    quads[quadOffset] = new Quad(v1, v2, v3, v4, normals, t);
                }
                quadOffset++;
            }
        }
    }
}
