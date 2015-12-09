package notmine; /**
 * A class for a light. Floats are used since the gl light functions use floats.
 * If we used doubles in this class, we would have to convert to floats 
 * for use by the gl light functions. 
 * Floats are fine for the accuracy required.
 *
 * @author    Dr Steve Maddock
 * @version   1.0 (26/07/2013)
 */
 
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.gl2.GLUT;

//Vasia Shelkov has made class smaller depedning on requirements. Code has only been added or removed, none of the existing
//code was edited.
public class Light implements Cloneable {

  public static final float[] DEFAULT_POSITION = {0.0f,12.0f,0.0f,1.0f};
  // (x,y,z,w) position of light.
  // If w is 0, then it is a directional light (at infinite distance in the given vector direction).
  // If w is 1, then it is a positional light (at the given position in the scene, and emitting in all directions).
  public static final float[] WHITE_LIGHT = {1.0f,1.0f,1.0f};
  public static final float[] DEFAULT_AMBIENT = {0.1f,0.1f,0.1f};
    private final boolean show;
    private final double lightRadius;

    private int index;
  private float[] position;
  private float[] ambient;
  private float[] diffuse;
  private float[] specular;
  private boolean switchedOn;
  
  private boolean spotlight = false;
  private float[] direction;
  private float angle;

  /**
   * Constructors
   */
  public Light(int i, float[] position, boolean show, double lightRadius) {
    this(i, position, DEFAULT_AMBIENT, WHITE_LIGHT, WHITE_LIGHT, true, show, lightRadius);
  }

  public Light(int i, float[] position, float[] ambient, float[] diffuse, float[] specular,
               boolean on, boolean show, double lightRadius) {
    index = i;
      this.show = show;
      this.position = position.clone();
    this.ambient = ambient.clone();
    this.diffuse = diffuse.clone();
    this.specular = specular.clone();
      this.lightRadius = lightRadius;
    switchedOn = on;
  } 

  public void makeSpotlight(float[] direction, float angle) {
    if (position[3]!=1) { 
	  System.out.println("Error. Position[3] needs to be 1 for a spotlight. Will now change.");
	  position[3]=1;
	}
    spotlight = true;
    this.direction = direction.clone();
    this.angle = angle;
  }
    
  public float[] getPosition() {
    return position.clone();
  }
  
  public float[] getAmbient() {
    return ambient.clone();
  }
  
  public float[] getDiffuse() {
    return diffuse.clone();
  }

  public float[] getSpecular() {
    return specular.clone();
  }
  
  public void setSwitchedOn(boolean on) {
    switchedOn = on;
  }
  
  public boolean getSwitchedOn() {
    return switchedOn;
  } 

  public void enable(GL2 gl) {
    gl.glEnable(index); 
  }
  
  public void disable(GL2 gl) {
    gl.glDisable(index); 
  }

  public void use(GL2 gl, GLUT glut) {
    if (switchedOn) {
      gl.glEnable(index); 
      // There is no glLightdv, so use glLightfv
      gl.glLightfv(index, GL2.GL_POSITION, position, 0);
      gl.glLightfv(index, GL2.GL_AMBIENT, ambient, 0);
      gl.glLightfv(index, GL2.GL_DIFFUSE, diffuse, 0);
      gl.glLightfv(index, GL2.GL_SPECULAR, specular, 0);
        if (spotlight) {
            gl.glLightf(index, GL2.GL_SPOT_CUTOFF, angle);
            gl.glLightfv(index, GL2.GL_SPOT_DIRECTION, direction, 0);
      }
      if (show) {
          displayPosition(gl, glut);
      }
    }
    else gl.glDisable(index);
  }
  
  private void displayPosition(GL2 gl, GLUT glut) {
    float[] matAmbientDiffuse = {0.1f, 0.1f, 0.1f, 1.0f};
    float[] matSpecular = {0.0f, 0.0f, 0.0f, 0.0f};
    float[] matShininess = {1.0f};
    float[] matEmission = {0.9f, 0.9f, 0.9f, 1.0f};

    // use glMaterialfv. There is no glMaterialdv
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matAmbientDiffuse, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, matSpecular, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, matShininess, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, matEmission, 0);
    gl.glPushMatrix();
      gl.glTranslated(position[0], position[1], position[2]);
      //Vasia changed it so that the radius of the lights can be defined
      glut.glutSolidSphere(this.lightRadius, 10,10);
    gl.glPopMatrix();
  }
}
