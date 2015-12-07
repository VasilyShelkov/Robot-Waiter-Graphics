package Primitives;

import com.jogamp.opengl.GL2;
import scene.SceneGraph;

/**
 * Created by vasily on 05/12/15.
 */
public abstract class Plane extends SceneGraph {
    public Quad[] quads;
    private int dlist;

    public void initializeDisplayList(GL2 gl){
        dlist = gl.glGenLists(1);
        gl.glNewList(dlist, GL2.GL_COMPILE);
        for (Quad quad:quads) {
            quad.render(gl);
        }
        gl.glEndList();
    }

    public void draw(GL2 gl) {
        gl.glCallList(dlist);
    }
}
