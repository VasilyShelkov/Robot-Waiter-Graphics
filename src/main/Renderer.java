package main;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;

import notmine.Camera;
import scene.SceneManager;

/**
 * Created by vasily on 24/11/15.
 */
public class Renderer implements GLEventListener {

    private int width, height;
    private static final float NEAR_CLIP=0.1f;
    private static final float FAR_CLIP=100.0f;

    private GLU glu;
    private Camera camera;
    private SceneManager scene;
    private boolean continuousAnimation;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        this.glu = new GLU();

        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glCullFace(GL2.GL_BACK);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_NORMALIZE);

        double radius = 50.0;
        double theta = Math.toRadians(-45);
        double phi = Math.toRadians(30);


        camera = new Camera(theta, phi, radius);
        scene = new SceneManager();
        scene.createGraph(gl);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT|GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        camera.view(glu);
        if(continuousAnimation){
            scene.updateGraph(gl);
        }
        scene.renderGraph(gl);
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

    public SceneManager getScene() {
        return scene;
    }

    public Camera getCamera() {
        return camera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setContinuousAnimation(boolean continuousAnimation) {
        this.continuousAnimation = continuousAnimation;
    }
}
