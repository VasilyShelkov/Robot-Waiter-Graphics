package Primitives;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

/**
 * Created by vasily on 03/12/15.
 */
public class Quad {
    private
    int[] normalCoords;
    private Vertex[] vertices = new Vertex[4];
    private double[] normal = new double[4];
    private Texture texture;

    public Quad() {
        vertices[0] = vertices[1] = vertices[2] = vertices[3] = new Vertex(0,0,0);
        normal[0] = normal[1] = normal[2] = normal[3] = 0;
        normalCoords = new int[]{0, 1, 0};
    }

    public Quad(Vertex v1, Vertex v2, Vertex v3, Vertex v4, int[] normals, Texture t) {
        this();
        vertices[0] = v1;
        vertices[1] = v2;
        vertices[2] = v3;
        vertices[3] = v4;
        this.normalCoords = normals;
        this.texture = t;
    }

    public void render(GL2 gl){
        texture.enable(gl);
        texture.bind(gl);
        texture.setTexParameteri(gl, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);

        gl.glBegin(GL2.GL_QUADS);
            gl.glNormal3d(normalCoords[0],normalCoords[1],normalCoords[2]);
            gl.glTexCoord2f(0, 0);
            gl.glVertex3d(vertices[0].getX(), vertices[0].getY(), vertices[0].getZ());

            gl.glTexCoord2f(0.5f, 0);
            gl.glVertex3d(vertices[1].getX(), vertices[1].getY(), vertices[1].getZ());

            gl.glTexCoord2f(0.5f, 0.5f);
            gl.glVertex3d(vertices[2].getX(), vertices[2].getY(), vertices[2].getZ());

            gl.glTexCoord2f(0, 0.5f);
            gl.glVertex3d(vertices[3].getX(), vertices[3].getY(), vertices[3].getZ());
        gl.glEnd();

        texture.disable(gl);
    }


}
