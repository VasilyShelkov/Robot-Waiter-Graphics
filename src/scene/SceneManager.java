package scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import robot.KeyFrame;
import notmine.Light;
import robot.Robot;
import robot.RobotAnimationManager;
import robot.parts.UpperBody;
import room.Room;
import scene.styling.Ruby;

import java.io.File;
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
        float[] position = {0, 7, 13, 1};
        mainLight1 = new Light(GL2.GL_LIGHT0, position, false, 0);
        float[] position1 = {0, 7, -13, 1};
        mainLight2 = new Light(GL2.GL_LIGHT1, position1, false, 0);

        // Create room Spotlights
        float[] spotlightInitialPosition = {0,0,0,1};       // Initially place spotlight at origin
        spotLight1 = new Light(GL2.GL_LIGHT3, spotlightInitialPosition, true, 0.3);
        float[] direction2 = {0f,0f,-1f};    // Initial direction for spotlight
        spotLight1.makeSpotlight(direction2, 20f);

        spotLight2 = new Light(GL2.GL_LIGHT4, spotlightInitialPosition, true, 0.3);
        float[] direction3 = {0f,0f,-1f};    // Initial direction for spotlight
        spotLight2.makeSpotlight(direction3, 20f);

        //create robot Spotlight
        robot1light = new Light(GL2.GL_LIGHT5, spotlightInitialPosition, true, 0.5);
        float[] direction4 = {0f,-1f,1f};    // Initial direction for spotlight
        robot1light.makeSpotlight(direction4, 20f);

        //create robot Spotlight
        robot2light = new Light(GL2.GL_LIGHT6, spotlightInitialPosition, true, 0.5);
        float[] direction5 = {0f,-1f,1f};    // Initial direction for spotlight
        robot2light.makeSpotlight(direction5, 20f);
    }

    public void createGraph(GL2 gl) {
        Room room = new Room(gl, 40, 15, 55, spotLight1, spotLight2);
        room.addLight(mainLight1);
        room.addLight(mainLight2);
        room.initialiseDisplayLists(gl);
        root.addChild(room);

        RobotAnimationManager robotAnimationManger1 = createRobot1AnimationManager();
        Robot robot1 = new Robot(gl, 3, 15, 0, 20, 230, robot1light, robotAnimationManger1);
        root.addChild(robot1);

//        RobotAnimationManager robotAnimationManger2 = createRobot2AnimationManager();
//        Robot robot2 = new Robot(gl, 3, -15, 0, -20, 50, robot2light, robotAnimationManger2);
//        UpperBody robot2UpperBody = robot2.getUpperBody();
//        robot2UpperBody.setMaterial(new Ruby());
//        robot2UpperBody.getHead().setMaterial(new Ruby());
//        robot2.getLowerLowerBody().setBodyTexture(robot2.loadTexture(gl, "textures" + File.separator + "firerivets.jpg"));
//        root.addChild(robot2);
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

    private RobotAnimationManager createRobot2AnimationManager() {
        ArrayList<KeyFrame> robotAnimationKeyFrames =  new ArrayList<>();
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

        return new RobotAnimationManager(robotAnimationKeyFrames);
    }
}
