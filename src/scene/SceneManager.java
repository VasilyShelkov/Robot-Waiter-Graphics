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
    private RobotAnimationManager robotAnimationManger1, robotAnimationManger2;

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
        float[] direction2 = {0f,0f,-1f};    //direction for spotlight
        spotLight1.makeSpotlight(direction2, 20f);

        spotLight2 = new Light(GL2.GL_LIGHT4, spotlightInitialPosition, true, 0.3);
        float[] direction3 = {0f,0f,-1f};    //direction for spotlight
        spotLight2.makeSpotlight(direction3, 20f);

        //create robot Spotlight
        robot1light = new Light(GL2.GL_LIGHT5, spotlightInitialPosition, true, 0.5);
        float[] direction4 = {0f,-1f,1f};    //direction for spotlight
        robot1light.makeSpotlight(direction4, 20f);

        //create robot Spotlight
        robot2light = new Light(GL2.GL_LIGHT6, spotlightInitialPosition, true, 0.5);
        float[] direction5 = {0f,-1f,1f};    //direction for spotlight
        robot2light.makeSpotlight(direction5, 20f);
    }

    public void createGraph(GL2 gl) {
        Room room = new Room(gl, 40, 20, 60, spotLight1, spotLight2);
        room.addLight(mainLight1);
        room.addLight(mainLight2);
        room.initialiseDisplayLists(gl);
        root.addChild(room);

        robotAnimationManger1 = createRobot1AnimationManager();
        Robot robot1 = new Robot(gl, 3, 15, 0, 20, 230, robot1light, robotAnimationManger1);
        root.addChild(robot1);

//        robotAnimationManger2 = createRobot2AnimationManager();
//        Robot robot2 = new Robot(gl, 3, -15, 0, -20, 50, robot2light, robotAnimationManger2);
//        UpperBody robot2UpperBody = robot2.getUpperBody();
//        robot2UpperBody.setMaterial(new Ruby());
//        robot2UpperBody.getHead().setMaterial(new Ruby());
//        robot2.getLowerBody().setBodyTexture(robot2.loadTexture(gl, "textures" + File.separator + "firerivets.jpg"));
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

    public RobotAnimationManager getRobotAnimationManger1() {
        return robotAnimationManger1;
    }

    public RobotAnimationManager getRobotAnimationManger2() {
        return robotAnimationManger2;
    }

    private RobotAnimationManager createRobot1AnimationManager() {
        ArrayList<KeyFrame> robotAnimationKeyFrames =  new ArrayList<>();
        robotAnimationKeyFrames.add(new KeyFrame(15, 20, 230, true,1));

        //first table stop
        robotAnimationKeyFrames.add(new KeyFrame(8, 13, 260, true,0.8));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, true,0.6));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, true,0.4));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, true,4, true));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, true,0.5));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, false,1));

        robotAnimationKeyFrames.add(new KeyFrame(10, 19, 220, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(11, 16, 190, true, 1.2));
        robotAnimationKeyFrames.add(new KeyFrame(12, 5, 160, true, 1.2));

        //second table stop
        robotAnimationKeyFrames.add(new KeyFrame(13, -3, 190, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(13, -6, 220, true, 0.4));
        robotAnimationKeyFrames.add(new KeyFrame(13, -6, 220, true, 0.4));
        robotAnimationKeyFrames.add(new KeyFrame(13, -6, 220, true, 4, true));
        robotAnimationKeyFrames.add(new KeyFrame(13, -6, 220, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(13, -5, 220, false, 1));

        robotAnimationKeyFrames.add(new KeyFrame(16, 0, 180, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(16, -8, 195, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(14, -16, 235, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(10, -20, 260, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-3, -22, 300, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-10, -20, 360, true, 0.6));
        robotAnimationKeyFrames.add(new KeyFrame(-13, -19, 370, true, 0.5));


        //third table stop
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, true, 0.3));
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, true, 4, true));
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, false, 1));

        robotAnimationKeyFrames.add(new KeyFrame(-15, -20, 410, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-15, -16, 345, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-16, -6, 410, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-12, 0, 510, true, 1));

        //first table other side
        robotAnimationKeyFrames.add(new KeyFrame(-8, -2, 540, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 4, true));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, false, 2));
        robotAnimationKeyFrames.add(new KeyFrame(-9, -2, 530, false, 2));

        robotAnimationKeyFrames.add(new KeyFrame(-14, 6, 450, true, 2));

        //second table other side
        robotAnimationKeyFrames.add(new KeyFrame(-8, 8, 435, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, true, 4, true));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, false, 1.5));
        robotAnimationKeyFrames.add(new KeyFrame(-8, 9, 465, false, 1.5));

        robotAnimationKeyFrames.add(new KeyFrame(-13, 12, 495, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-9, 8, 495, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-5, 4, 495, true, 1));


        //third table other side
        robotAnimationKeyFrames.add(new KeyFrame(2, -1, 500, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, true, 4, true));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, false, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(2, -3, 480, false, 1.2));

        robotAnimationKeyFrames.add(new KeyFrame(0, 0, 450, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(4, 2, 430, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(6, 4, 410, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(11, 7, 380, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(10, 14, 330, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(5, 17, 260, false, 1));
        robotAnimationKeyFrames.add(new KeyFrame(15, 20, 230, true, 1));
        return new RobotAnimationManager(robotAnimationKeyFrames);
    }

    private RobotAnimationManager createRobot2AnimationManager() {
        ArrayList<KeyFrame> robotAnimationKeyFrames =  new ArrayList<>();
        robotAnimationKeyFrames.add(new KeyFrame(-15, -20, 410, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-15, -16, 345, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-16, -6, 410, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-12, 0, 510, true, 1));

        //first table
        robotAnimationKeyFrames.add(new KeyFrame(-8, -2, 540, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 3));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-8, -4, 540, false, 2));
        robotAnimationKeyFrames.add(new KeyFrame(-9, -2, 530, false, 2));

        robotAnimationKeyFrames.add(new KeyFrame(-14, 6, 450, true, 2));

        //second table
        robotAnimationKeyFrames.add(new KeyFrame(-8, 8, 435, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, true, 3));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-7, 8, 435, false, 1.5));
        robotAnimationKeyFrames.add(new KeyFrame(-8, 9, 465, false, 1.5));

        robotAnimationKeyFrames.add(new KeyFrame(-13, 12, 495, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-9, 8, 495, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-5, 4, 495, true, 1));


        //third table
        robotAnimationKeyFrames.add(new KeyFrame(2, -1, 500, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, true, 3));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(4, -5, 505, false, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(2, -3, 480, false, 1.2));

        robotAnimationKeyFrames.add(new KeyFrame(0, 0, 450, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(4, 2, 430, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(6, 4, 410, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(11, 7, 380, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(10, 14, 330, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(5, 17, 260, false, 1));
        robotAnimationKeyFrames.add(new KeyFrame(15, 20, 230, true, 1));

        //first table stop other side
        robotAnimationKeyFrames.add(new KeyFrame(8, 13, 260, true,0.8));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, true,0.6));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, true,0.4));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, true,3));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, true,0.5));
        robotAnimationKeyFrames.add(new KeyFrame(7, 12, 260, false,1));

        robotAnimationKeyFrames.add(new KeyFrame(10, 19, 220, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(11, 16, 190, true, 1.2));
        robotAnimationKeyFrames.add(new KeyFrame(12, 5, 160, true, 1.2));

        //second table stop other side
        robotAnimationKeyFrames.add(new KeyFrame(13, -3, 190, true, 0.7));
        robotAnimationKeyFrames.add(new KeyFrame(13, -6, 220, true, 0.4));
        robotAnimationKeyFrames.add(new KeyFrame(13, -6, 220, true, 0.4));
        robotAnimationKeyFrames.add(new KeyFrame(13, -6, 220, true, 3));
        robotAnimationKeyFrames.add(new KeyFrame(13, -6, 220, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(13, -5, 220, false, 1));

        robotAnimationKeyFrames.add(new KeyFrame(16, 0, 180, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(16, -8, 195, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(14, -16, 235, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(10, -20, 260, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-3, -22, 300, true, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-10, -20, 360, true, 0.6));
        robotAnimationKeyFrames.add(new KeyFrame(-13, -19, 370, true, 0.5));


        //third table stop other side
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, true, 0.3));
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, true, 3));
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, true, 0.5));
        robotAnimationKeyFrames.add(new KeyFrame(-13.5, -17.5, 405, false, 1));
        robotAnimationKeyFrames.add(new KeyFrame(-15, -20, 410, true, 1));
        return new RobotAnimationManager(robotAnimationKeyFrames);
    }
}
