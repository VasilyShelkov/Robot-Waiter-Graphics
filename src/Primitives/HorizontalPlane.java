package Primitives;

import com.jogamp.opengl.util.texture.Texture;
import notmine.Light;
import peripherals.hangingspotlight.CeilingLamp;

/**
 * Created by vasily on 02/12/15.
 */
public class HorizontalPlane extends Plane {

    public HorizontalPlane(double centreX, double centreY, double centreZ,
                           double width, double depth, int xSlices, int zSlices, Texture t) {
        int quadOffset = 0;
        double startX = centreX-(width/2.0);
        double startZ = centreZ-(depth/2.0);
        double stepX = width/(xSlices-1);
        double stepZ = depth/(zSlices-1);

        int quadsSize = (xSlices)*(zSlices);
        quads = new Quad[quadsSize];

        for(int i=0; i<xSlices; i++) {
            for (int j = 0; j < zSlices; j++) {
                Vertex v1 = new Vertex(startX + (i * stepX), centreY, startZ + ((j + 1) * stepZ));
                Vertex v2 = new Vertex(startX + ((i + 1) * stepX), centreY, startZ + ((j + 1) * stepZ));
                Vertex v3 = new Vertex(startX + ((i + 1) * stepX), centreY, startZ + (j * stepZ));
                Vertex v4 = new Vertex(startX + (i * stepX), centreY, startZ + (j * stepZ));

                if(centreY > 0){
                    quads[quadOffset] = new Quad(v4, v3, v2, v1, t);
                }else{
                    quads[quadOffset] = new Quad(v1, v2, v3, v4, t);
                }
                quadOffset++;
            }
        }
    }

    public HorizontalPlane(double centreX, double centreY, double centreZ,
                           double width, double depth,
                           int xSlices, int zSlices, Texture t,
                           Light spotlight1, Light spotlight2) {
        this(centreX, centreY, centreZ, width, depth, xSlices, zSlices, t);

        //left Ceiling Light
        CeilingLamp ceilingLamp1 = new CeilingLamp(centreX - (width / 4), centreY, centreZ, width, spotlight1);
        addChild(ceilingLamp1);

        //right Ceiling Light
        CeilingLamp ceilingLamp2 = new CeilingLamp(centreX + (width / 4), centreY, centreZ, width, spotlight2);
        addChild(ceilingLamp2);
    }
}
