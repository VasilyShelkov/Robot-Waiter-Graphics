package scene;

import com.jogamp.opengl.GL2;
import main.KeyFrame;
import main.RobotSimulationManager;
import notmine.Light;
import robot.Robot;
import robot.RobotAnimationManager;
import room.Room;

import java.util.ArrayList;

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
        room.initialiseDisplayLists(gl);
        root.addChild(room);

        RobotAnimationManager robotAnimationManger = createRobot1AnimationManager();
        root.addChild(new Robot(gl, 3, 15, 0, 20, 230, robot1light, robotAnimationManger));
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

    private RobotAnimationManager createRobot1AnimationManager() {
        ArrayList<KeyFrame> robotAnimationKeyFrames =  new ArrayList<>();
        robotAnimationKeyFrames.add(new KeyFrame(15, 20, 230, true));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, true));
        robotAnimationKeyFrames.add(new KeyFrame(10, 19, 200, false));
        robotAnimationKeyFrames.add(new KeyFrame(11, 16, 180, true));
        robotAnimationKeyFrames.add(new KeyFrame(13, 5, 160, true));
        robotAnimationKeyFrames.add(new KeyFrame(14, -6, 240, true));
        robotAnimationKeyFrames.add(new KeyFrame(16, 0, 180, true));
        robotAnimationKeyFrames.add(new KeyFrame(16, -8, 230, true));
        robotAnimationKeyFrames.add(new KeyFrame(14, -16, 260, true));
        robotAnimationKeyFrames.add(new KeyFrame(10, -19, 280, true));
        robotAnimationKeyFrames.add(new KeyFrame(-3, -20, 320, true));
        robotAnimationKeyFrames.add(new KeyFrame(-11, -17, 30, true));
        robotAnimationKeyFrames.add(new KeyFrame(-15, -20, 50, false));
        robotAnimationKeyFrames.add(new KeyFrame(-14, -16, 345, true));
        robotAnimationKeyFrames.add(new KeyFrame(-16, -6, 20, true));
        robotAnimationKeyFrames.add(new KeyFrame(-12, 0, 100, true));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -2, 180, true));
        robotAnimationKeyFrames.add(new KeyFrame(-14, 6, 90, false));
        robotAnimationKeyFrames.add(new KeyFrame(-6, 8, 75, true));
        robotAnimationKeyFrames.add(new KeyFrame(-13, 12, 135, false));
        robotAnimationKeyFrames.add(new KeyFrame(4, -3, 135, true));
        robotAnimationKeyFrames.add(new KeyFrame(0, 0, 90, false));
        robotAnimationKeyFrames.add(new KeyFrame(4, 2, 70, true));
        robotAnimationKeyFrames.add(new KeyFrame(6, 4, 50, true));
        robotAnimationKeyFrames.add(new KeyFrame(11, 7, 20, true));
        robotAnimationKeyFrames.add(new KeyFrame(10, 11, 330, true));
        robotAnimationKeyFrames.add(new KeyFrame(5, 17, 260, true));
        robotAnimationKeyFrames.add(new KeyFrame(15, 20, 230, false));
        return new RobotAnimationManager(robotAnimationKeyFrames);
    }

//    private RobotSimulationManager createRobot1SimulationManager() {
//        ArrayList<KeyFrame> robotSimulationKeyframes = new ArrayList<>();
//        robotSimulationKeyframes.add(new KeyFrame(-40));
//        robotSimulationKeyframes.add(new KeyFrame(3));
//        robotSimulationKeyframes.add(new KeyFrame(-20));
//        return new RobotSimulationManager(robotSimulationKeyframes);
//    }
}
