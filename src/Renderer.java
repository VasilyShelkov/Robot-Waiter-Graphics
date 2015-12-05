import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import notmine.Axes;
import robot.Robot;
import room.Room;

import java.awt.event.MouseListener;

/**
 * Created by vasily on 24/11/15.
 */
public class Renderer implements GLEventListener {

    private int width, height;
    private static final float NEAR_CLIP=0.1f;
    private static final float FAR_CLIP=100.0f;

    private GLU glu;
    private GLUT glut;

    private Camera camera;

    private Robot robot;
    private Room room;
    private Axes axes;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();
        glut = new GLUT();

        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glCullFace(GL2.GL_BACK);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
//        gl.glEnable(GL2.GL_LIGHTING);
//        gl.glEnable(GL2.GL_LIGHT0);
//        gl.glEnable(GL2.GL_NORMALIZE);

        double radius = 10.0;
        double theta = Math.toRadians(-45);
        double phi = Math.toRadians(30);


        camera = new Camera(theta, phi, radius);
        room = new Room(10,10,10);
        room.initialiseDisplayLists(gl);
        robot = new Robot(glut, glu, 6);
        axes = new Axes(2.2, 1.8, 1.6);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT|GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        camera.view(glu);
        axes.display(gl, glut);
        robot.render(gl);
        room.render(gl);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        this.width=width;
        this.height=height;

        if (height == 0) height = 1;
        float fAspect=(float) width/height;
        float fovy=60.0f;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        float top=(float) Math.tan(Math.toRadians(fovy*0.5))*NEAR_CLIP;
        float bottom=-top;
        float left=fAspect*bottom;
        float right=fAspect*top;

        gl.glFrustum(left, right, bottom, top, NEAR_CLIP, FAR_CLIP);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

    public Camera getCamera() {
        return camera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
