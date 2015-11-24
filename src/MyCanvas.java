import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * Created by vasily on 24/11/15.
 */
public class MyCanvas extends GLCanvas implements GLEventListener {

    private GLU glu = new GLU();
    private GLUT glut = new GLUT();

    private float angle = 0;

    Room room;
    Robot robot;

    public MyCanvas(GLCapabilities capabilities) {
        super(capabilities);
        this.addGLEventListener(this);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
//        glu = new GLU();

        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glCullFace(GL2.GL_BACK);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);

        room = new Room();
        robot = new Robot();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        update();
//        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    }

    private void update() {
        angle += 0.01;
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        if (height == 0) height = 1;
        float fAspect=(float) width/height;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45.0, fAspect, 0.1, 100.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}
}
