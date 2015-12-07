package scene;

import com.jogamp.opengl.GL2;
import notmine.Light;
import robot.Robot;
import room.Room;
import scene.SceneGraph;

/**
 * Created by vasily on 03/12/15.
 */
public class SceneManager {

    private SceneGraph root;
    private Light mainLight1, mainLight2, spotLight1, spotLight2, robot1light, robot2light;

    public SceneManager(){
        root = new SceneGraph();

        // Create a default lights
        float[] position = {-12, 15, 0, 1};
        mainLight1 = new Light(GL2.GL_LIGHT0, position);
        float[] position1 = {12, 15, 0, 1};
        mainLight2 = new Light(GL2.GL_LIGHT1, position1);

        // Create room Spotlights
        float[] spotlightPosition = {0,0,0,1};       // Initially place spotlight at origin
        spotLight1 = new Light(GL2.GL_LIGHT3, spotlightPosition);
        float[] direction2 = {0f,0f,-1f};    // Initial direction for spotlight
        spotLight1.makeSpotlight(direction2, 20f);

        spotLight2 = new Light(GL2.GL_LIGHT4, spotlightPosition);
        float[] direction3 = {0f,0f,-1f};    // Initial direction for spotlight
        spotLight2.makeSpotlight(direction3, 20f);

        //create robot Spotlight
        robot1light = new Light(GL2.GL_LIGHT5, spotlightPosition);
        float[] direction4 = {0f,-1f,1f};    // Initial direction for spotlight
        robot1light.makeSpotlight(direction4, 20f);
    }

    public void createGraph(GL2 gl) {
        Room room = new Room(gl, 40, 15, 55, spotLight1, spotLight2);
        room.addLight(mainLight1);
        room.addLight(mainLight2);
        root.addChild(new Robot(gl, 3, 15, 0, 20, 230, robot1light));
        room.initialiseDisplayLists(gl);
        root.addChild(room);
    }

    public void renderGraph(GL2 gl) {
        root.renderLights(gl);
        root.render(gl);
    }

    public void updateGraph(GL2 gl) { root.update(gl); }

    public Light getMainLight1() {
        return mainLight1;
    }

    public Light getMainLight2() {
        return mainLight2;
    }

    public Light getSpotLight1() {
        return spotLight1;
    }

    public Light getSpotLight2() {
        return spotLight2;
    }

    public Light getRobot1light() {
        return robot1light;
    }

    public Light getRobot2light() {
        return robot2light;
    }
}
