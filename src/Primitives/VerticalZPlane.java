package Primitives;

import com.jogamp.opengl.util.texture.Texture;

/**
 * Created by vasily on 05/12/15.
 */
public class VerticalZPlane extends Plane {
    public VerticalZPlane(double centreX, double centreY, double centreZ,
                          double depth, double height, int zSlices, int ySlices, Texture t) {
        int quadOffset = 0;
        double startZ = centreZ-(depth/2.0);
        double startY = centreY;
        double stepZ = depth/(zSlices-1);
        double stepY = height/(ySlices-1);

        int quadsSize = (zSlices)*(ySlices);
        quads = new Quad[quadsSize];

        for(int i=0; i<zSlices; i++) {
            for (int j = 0; j < ySlices; j++) {
                Vertex v1 = new Vertex(centreX, startY + ((j + 1) * stepY), startZ + (i * stepZ));
                Vertex v2 = new Vertex(centreX, startY + ((j + 1) * stepY), startZ + ((i + 1) * stepZ));
                Vertex v3 = new Vertex(centreX, startY + (j * stepY), startZ + ((i + 1) * stepZ));
                Vertex v4 = new Vertex(centreX, startY + (j * stepY), startZ + (i * stepZ));

                if(centreX > 0){
                    quads[quadOffset] = new Quad(v4, v3, v2, v1, t);
                }else{
                    quads[quadOffset] = new Quad(v1, v2, v3, v4, t);
                }
                quadOffset++;
            }
        }
    }
}
