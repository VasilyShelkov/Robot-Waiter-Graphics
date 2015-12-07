package peripherals.table;

import com.jogamp.opengl.util.texture.Texture;
import scene.SceneGraph;

/**
 * Created by vasily on 05/12/15.
 */
public class Table extends SceneGraph {
    public Table(double x, double y, double z, double height, double width, int slices,
                 Texture tableLegTexture, Texture tableTopTexture) {
        super(x, y, z);

        double tableHeight = height*0.3;
        double tableWidth = width*0.1;
        double tableLength = tableWidth*1.3;

        double legHeight = tableHeight*0.8;
        double legRadius = tableWidth*0.05;

        addChild(new TableLeg(x-(tableWidth/2), y, z-(tableLength/2), legHeight, legRadius, slices, tableLegTexture));

        addChild(new TableLeg(x-(tableWidth/2), y, z+(tableLength/2), legHeight, legRadius, slices, tableLegTexture));

        addChild(new TableLeg(x+(tableWidth/2), y, z-(tableLength/2), legHeight, legRadius, slices, tableLegTexture));

        addChild(new TableLeg(x+(tableWidth/2), y, z+(tableLength/2), legHeight, legRadius, slices, tableLegTexture));

        addChild(new TableTop(x, legHeight, z, tableWidth, tableLength, slices, tableTopTexture));
    }
}
