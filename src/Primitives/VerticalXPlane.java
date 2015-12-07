package Primitives;

import com.jogamp.opengl.util.texture.Texture;

/**
 * Created by vasily on 05/12/15.
 */
public class VerticalXPlane extends Plane {
    public VerticalXPlane(double centreX, double centreY, double centreZ,
                           double width, double height, int xSlices, int ySlices, Texture t) {
        int quadOffset = 0;
        double startX = centreX-(width/2.0);
        double startY = centreY;
        double stepX = width/(xSlices-1);
        double stepY = height/(ySlices-1);

        int quadsSize = (xSlices)*(ySlices);
        quads = new Quad[quadsSize];

        for(int i=0; i<xSlices; i++) {
            for (int j = 0; j < ySlices; j++) {
                Vertex v1 = new Vertex(startX + (i * stepX), startY + ((j + 1) * stepY), centreZ);
                Vertex v2 = new Vertex(startX + ((i + 1) * stepX), startY + ((j + 1) * stepY), centreZ);
                Vertex v3 = new Vertex(startX + ((i + 1) * stepX), startY + (j * stepY), centreZ);
                Vertex v4 = new Vertex(startX + (i * stepX), startY + (j * stepY), centreZ);

                if(centreZ < 0){
                    quads[quadOffset] = new Quad(v4, v3, v2, v1, t);
                }else{
                    quads[quadOffset] = new Quad(v1, v2, v3, v4, t);
                }
                quadOffset++;
            }
        }
    }
}
