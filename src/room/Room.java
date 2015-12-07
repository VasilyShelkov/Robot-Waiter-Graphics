package room;

import Primitives.HorizontalPlane;
import Primitives.VerticalXPlane;
import Primitives.VerticalZPlane;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import notmine.Light;
import peripherals.table.Table;
import scene.SceneGraph;
import scene.styling.Chrome;

import java.io.File;

/**
 * Created by vasily on 02/12/15.
 */
public class Room extends SceneGraph{

    private final int SLICES = 70;
    private VerticalXPlane wall;
    private final VerticalXPlane wall2;
    private final VerticalZPlane wall3;
    private final VerticalZPlane wall4;
    private HorizontalPlane floor;
    private final HorizontalPlane roof;

    public Room(GL2 gl, int width, int height, int depth, Light spotlight1, Light spotlight2) {
        super(0,0,0, new Chrome());

        //floor
        Texture floorTexture = loadTexture(gl, "textures" + File.separator + "marble10.bmp");
        this.floor = new HorizontalPlane(0, 0, 0, width, depth, SLICES, SLICES, floorTexture);
        addChild(floor);

        //roof
        this.roof = new HorizontalPlane(0, height, 0, width, depth, SLICES, SLICES, floorTexture, spotlight1, spotlight2);
        addChild(roof);

        Texture wallTexture = loadTexture(gl, "textures" + File.separator + "snowflake4.jpg");
        //wall1x
        this.wall = new VerticalXPlane(0, 0, 0-(depth/2), width, height, 20, 10, wallTexture);
        addChild(wall);

        //wall2x
        this.wall2 = new VerticalXPlane(0, 0, 0+(depth/2), width, height, 20, 10, wallTexture);
        addChild(wall2);

        //wall3z
        this.wall3 = new VerticalZPlane(0-(width/2), 0, 0, depth, height, 10, 10, wallTexture);
        addChild(wall3);

        //wall4z
        this.wall4 = new VerticalZPlane(0+(width/2), 0, 0, depth, height, 10, 10, wallTexture);
        addChild(wall4);

        //3 tables
        Texture tableLegTexture = loadTexture(gl, "textures" + File.separator + "plank01.bmp");
        addChild(new Table(0-(width/10), 0, 0-(depth/10), height, width, SLICES, tableLegTexture, tableLegTexture));

        addChild(new Table(0, 0, 0+(width/8), height, width, SLICES, tableLegTexture, tableLegTexture));

        addChild(new Table(0+(width/10), 0, 0-(depth/10), height, width, SLICES, tableLegTexture, tableLegTexture));
    }

    public void initialiseDisplayLists(GL2 gl){
        floor.initializeDisplayList(gl);
        roof.initializeDisplayList(gl);
        wall.initializeDisplayList(gl);
        wall2.initializeDisplayList(gl);
        wall3.initializeDisplayList(gl);
        wall4.initializeDisplayList(gl);
    }
}
