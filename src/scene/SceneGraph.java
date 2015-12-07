package scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;
import notmine.Light;
import scene.styling.Material;
import scene.styling.Silver;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by vasily on 03/12/15.
 */
public class SceneGraph implements Renderable {

    public GLU glu;
    public GLUT glut;

    double x, y, z;
    public ArrayList<SceneGraph> children;
    Material material;
    ArrayList<Light> lights;

    public SceneGraph() {
        this.glu = new GLU();
        this.glut = new GLUT();
        children = new ArrayList<>();
        x = y = z = 0;
        material = new Silver();
        lights = new ArrayList<>();
    }

    public SceneGraph(double x, double y, double z) {
        this();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SceneGraph(double x, double y, double z, Material m) {
        this();
        this.x = x;
        this.y = y;
        this.z = z;
        this.material = m;
    }

    public void addChild(SceneGraph node){
        children.add(node);
    };

    public void addLight(Light light){
        this.lights.add(light);
    }

    @Override
    public void render(GL2 gl) {
        gl.glPushMatrix();
            transform(gl);

            material.applyMaterial(gl);
            draw(gl);

            for (SceneGraph child: children) {
                child.render(gl);
            }
        gl.glPopMatrix();
    }

    public void renderLights(GL2 gl) {
        gl.glPushMatrix();
        transform(gl);
        for(Light light: lights) {
            light.use(gl, glut, true);
        }
        for (SceneGraph child: children) {
            child.renderLights(gl);
        }
        gl.glPopMatrix();
    }

    @Override
    public void draw(GL2 gl) {}

    @Override
    public void transform(GL2 gl) {
        gl.glTranslated(x, y, z);
    }

    @Override
    public void update(GL2 gl) {
        for (SceneGraph child: children){
            child.update(gl);
        }
    }

    public Texture loadTexture(GL2 gl, String filename) {
        Texture tex = null;
        try {
            File f = new File(filename);

            // The following line results in a texture that is flipped vertically (i.e. is upside down)
            // due to the OpenGL and Java (0,0) coordinate position being different,
            // one using the bottom left of an image as (0,0) and the other using the top left as (0,0):
            //   tex = TextureIO.newTexture(new File(filename), false);

            // So, instead, use the following three lines which flip the image vertically:
            BufferedImage img = ImageIO.read(f);
            ImageUtil.flipImageVertically(img);
            tex = AWTTextureIO.newTexture(GLProfile.getDefault(), img, false);

            tex.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
            tex.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
        }
        catch(Exception e) {
            System.out.println("Error loading texture " + filename);
        }
        return tex;
    }
}
