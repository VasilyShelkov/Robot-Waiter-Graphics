import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by vasily on 23/11/15.
 */
public class MainFrame extends Frame {
    public final static int CANVAS_WIDTH = 640;
    public final static int CANVAS_HEIGHT = 400;
    public final static int FRAME_WIDTH = 800;
    public final static int FRAME_HEIGHT = 800;
    public final static int FPS = 60;

    public MainFrame() {
        super("Robot Waiter");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);

        GLCanvas canvas = new MyCanvas(caps);
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        add(canvas, "Center");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //not needed if repainting transformations
        FPSAnimator animator = new FPSAnimator(canvas, FPS);
        animator.start();
    }

    public static void main(String[] args) {
        MainFrame gl = new MainFrame();
        gl.setVisible(true);
    }
}