package main;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by vasily on 23/11/15.
 */
public class MainFrame extends Frame implements MouseMotionListener, ItemListener{
    public final static int CANVAS_WIDTH = 640;
    public final static int CANVAS_HEIGHT = 400;
    public final static int FRAME_WIDTH = 800;
    public final static int FRAME_HEIGHT = 800;
    public final static int FPS = 30;

    private final Checkbox checkMainLight, checkSpotLight, checkRobotLight;
    private GLCanvas canvas;

    private Renderer renderer;
    private Point lastpoint;            // used with mouse routines

    public MainFrame() {
        super("Robot Waiter");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);

        canvas = new GLCanvas(caps);
        canvas.setSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        add(canvas, "Center");
        renderer = new Renderer(CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.addGLEventListener(renderer);
        canvas.addMouseMotionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Panel p = new Panel(new GridLayout(2,1));
        Panel p1 = new Panel(new GridLayout(5,1));
        checkMainLight = addCheckbox(p1, "MainLights on", this);
        checkSpotLight = addCheckbox(p1, "Spotlights on", this);
        checkRobotLight = addCheckbox(p1, "Robotlight on", this);
        p.add(p1);
//        p1 = new Panel(new GridLayout(4,1));
//        Button rotate = new Button("Rotate light");
//        rotate.setActionCommand("Rotate");
//        rotate.addActionListener(this);
//        p1.add(rotate);
//        startAnim = new Button("Start animation");
//        startAnim.setActionCommand("StartAnim");
//        startAnim.addActionListener(this);
//        p1.add(startAnim);
//        pauseAnim = new Button("Pause animation");
//        pauseAnim.setActionCommand("PauseAnim");
//        pauseAnim.addActionListener(this);
//        p1.add(pauseAnim);
//        resetScene = new Button("Reset scene");
//        resetScene.setActionCommand("ResetScene");
//        resetScene.addActionListener(this);
//        p1.add(resetScene);
//        p.add(p1);
        add(p, "East");

        //not needed if repainting transformations
        FPSAnimator animator = new FPSAnimator(canvas, FPS);
        animator.start();
    }

    private Checkbox addCheckbox(Panel p, String s, ItemListener a) {
        Checkbox c = new Checkbox(s, true);
        c.addItemListener(a);
        p.add(c);
        return c;
    }

    public static void main(String[] args) {
        MainFrame gl = new MainFrame();
        gl.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if (source == checkMainLight){
            renderer.getScene().getMainLight1().setSwitchedOn(checkMainLight.getState());
            renderer.getScene().getMainLight2().setSwitchedOn(checkMainLight.getState());
            canvas.repaint();
        } else if(source == checkSpotLight){
            renderer.getScene().getSpotLight1().setSwitchedOn(checkSpotLight.getState());
            renderer.getScene().getSpotLight2().setSwitchedOn(checkSpotLight.getState());
            canvas.repaint();
        } else if(source == checkRobotLight){
            renderer.getScene().getRobot1light().setSwitchedOn(checkRobotLight.getState());
//            renderer.getScene().getSpotLight2().setSwitchedOn(checkSpotLight.getState());
            canvas.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point ms = e.getPoint();

        float dx=(float) (ms.x-lastpoint.x)/ renderer.getWidth();
        float dy=(float) (ms.y-lastpoint.y)/ renderer.getHeight();

        Camera camera = renderer.getCamera();

        if (e.getModifiers()==MouseEvent.BUTTON1_MASK)
        {
            camera.updateThetaPhi(-dx*2.0f, dy*2.0f);
        }
        else if (e.getModifiers()==MouseEvent.BUTTON3_MASK)
            camera.updateRadius(-(dy*2));

        lastpoint = ms;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastpoint = e.getPoint();
    }
}