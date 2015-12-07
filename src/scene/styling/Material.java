package scene.styling;

import com.jogamp.opengl.GL2;

/**
 * Created by vasily on 04/12/15.
 */
public abstract class Material {

    float[] matAmbient;
    float[] matDiffuse;
    float[] matSpecular;
    float matShininess;
    float[] matEmission;

    public Material(float[] matAmbient, float[] matDiffuse, float[] matSpecular, float matShininess, float[] matEmission) {
        this.matAmbient = matAmbient;
        this.matDiffuse = matDiffuse;
        this.matSpecular = matSpecular;
        this.matShininess = matShininess;
        this.matEmission = matEmission;
    }

    public void applyMaterial(GL2 gl) {
        gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_AMBIENT, matAmbient, 0);
        gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_DIFFUSE, matDiffuse, 0);
        gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_SPECULAR, matSpecular, 0);
        gl.glMaterialf(GL2.GL_FRONT,GL2.GL_SHININESS, matShininess*128.0f);
        gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_EMISSION, matEmission, 0);
    }
}
