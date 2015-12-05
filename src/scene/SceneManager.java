package scene;

import com.jogamp.opengl.GL2;
import robot.Robot;
import room.Room;
import scene.SceneGraph;

/**
 * Created by vasily on 03/12/15.
 */
public class SceneManager {

    private SceneGraph root;

    public SceneManager(){
        root = new SceneGraph();
    }

    public void createGraph(GL2 gl) {
        root.addChild(new Robot(3, 5, 0, 0));
        Room room = new Room(50, 15, 30);
        room.initialiseDisplayLists(gl);
        root.addChild(room);
    }

    public void renderGraph(GL2 gl) {
        root.render(gl);
    }
}
