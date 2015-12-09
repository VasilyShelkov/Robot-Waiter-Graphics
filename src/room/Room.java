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
import scene.styling.Silver;

import java.io.File;

/**
 * Created by vasily on 02/12/15.
 */
public class Room extends SceneGraph{

    private final int SLICES = 40;
    private VerticalXPlane wall;
    private final VerticalXPlane wall2;
    private final VerticalZPlane wall3;
    private final VerticalZPlane wall4;
    private HorizontalPlane floor;
    private final HorizontalPlane roof;

    public Room(GL2 gl, double width, double height, double depth, Light spotlight1, Light spotlight2) {
        super(0,0,0, new Silver());

        //floor
        Texture floorTexture = loadTexture(gl, "textures" + File.separator + "marble03.bmp");
        this.floor = new HorizontalPlane(0, width, depth, SLICES*3, SLICES*3, new int[]{0,1,0},
                floorTexture, 2, 2);
        addChild(floor);

        //roof
        Texture ceilingTexture = loadTexture(gl, "textures" + File.separator + "plank01.bmp");
        this.roof = new HorizontalPlane(height, width, depth, SLICES, SLICES, new int[]{0,1,0},
                ceilingTexture, 5, 5,
                spotlight1, spotlight2);
        addChild(roof);

        Texture wallTexture = loadTexture(gl, "textures" + File.separator + "snowflake4.jpg");
        //wall1x
        this.wall = new VerticalXPlane(0, 0, 0-(depth/2), width, height, SLICES, SLICES, new int[]{0,0,1},
                wallTexture, 8, 3);
        addChild(wall);

        //wall2x
        this.wall2 = new VerticalXPlane(0, 0, 0+(depth/2), width, height, SLICES, SLICES, new int[]{0,0,-1},
                wallTexture, 8, 3);
        addChild(wall2);

        //wall3z
        this.wall3 = new VerticalZPlane(0-(width/2), 0, 0, depth, height, SLICES, SLICES, new int[]{1,0,0},
                wallTexture, 12, 3);
        addChild(wall3);

        //wall4z
        this.wall4 = new VerticalZPlane(0+(width/2), 0, 0, depth, height, SLICES, SLICES, new int[]{-1,0,0},
                wallTexture, 12, 3);
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
