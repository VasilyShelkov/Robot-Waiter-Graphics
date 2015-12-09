package Primitives;

import com.jogamp.opengl.util.texture.Texture;

/**
 * Created by vasily on 05/12/15.
 */
public class VerticalXPlane extends Plane {
    public VerticalXPlane(double centreX, double centreY, double centreZ,
                           double width, double height, int xSlices, int ySlices, int[] normals,
                          Texture t, double textureUSlices, double textureVSlices) {
        int quadOffset = 0;
        double startX = centreX-(width/2);
        double startY = centreY;
        double stepX = width/(xSlices-1);
        double stepY = height/(ySlices-1);
        double txStepU = textureUSlices/(xSlices);
        double txStepV = textureVSlices/(ySlices);

        int quadsSize = (xSlices-1)*(ySlices-1);
        quads = new Quad[quadsSize];

        for(int i=0; i<xSlices-1; i++) {
            for (int j = 0; j < ySlices-1; j++) {
                double u = (i * txStepU);
                double u1 = ((i + 1) * txStepU);
                double v5 = (j * txStepV);
                double v = ((j + 1) * txStepV);
                Vertex v1 = new Vertex(startX + (i * stepX), startY + ((j + 1) * stepY), centreZ, u, v);
                Vertex v2 = new Vertex(startX + ((i + 1) * stepX), startY + ((j + 1) * stepY), centreZ, u1, v);
                Vertex v3 = new Vertex(startX + ((i + 1) * stepX), startY + (j * stepY), centreZ, u1, v5);
                Vertex v4 = new Vertex(startX + (i * stepX), startY + (j * stepY), centreZ, u, v5);

                if(centreZ < 0){
                    quads[quadOffset] = new Quad(v4, v3, v2, v1, normals, t);
                }else{
                    quads[quadOffset] = new Quad(v1, v2, v3, v4, normals, t);
                }
                quadOffset++;
            }
        }
    }
}
