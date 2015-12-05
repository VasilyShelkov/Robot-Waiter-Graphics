package peripherals.table;

import scene.SceneGraph;

/**
 * Created by vasily on 05/12/15.
 */
public class Table extends SceneGraph {
    public Table(double x, double y, double z, double height, double width) {
        super(x, y, z);

        double tableHeight = height*0.2;
        double tableWidth = width*0.1;


    }
}
