package notmine;

import com.jogamp.opengl.glu.GLU;

/**
 * Created by vasily on 23/11/15.
 */
public class Camera {
    private double theta, phi, radius;

    private double[] eye = new double[3];
    private double[] lookAt = new double[3];
    private double[] upvec = new double[3];

    /**
     * Constructor.
     */
    public Camera(double theta, double phi, double radius) {
        this.theta = theta;
        this.phi = phi;
        this.radius = radius;
        calcEyePosition();
    }

    public void updateThetaPhi(double thetaInc, double phiInc) {
        theta += thetaInc;
        phi += phiInc;
        calcEyePosition();
    }

    public void updateRadius(double radiusInc) {
        radius += radiusInc;
        calcEyePosition();
    }

    public double getEyeX() { return eye[0]; }
    public double getEyeY() { return eye[1]; }
    public double getEyeZ() { return eye[2]; }

    public double getUpVecX() { return upvec[0]; }
    public double getUpVecY() { return upvec[1]; }
    public double getUpVecZ() { return upvec[2]; }


    private void calcEyePosition() {
        double cy, cz, sy, sz;
        cy = Math.cos(theta);
        sy = Math.sin(theta);
        cz = Math.cos(phi);
        sz = Math.sin(phi);

        eye[0] = radius*cy*cz;
        eye[1] = radius*sz;
        eye[2] = -radius*sy*cz;

        upvec[0] = -cy*sz;
        upvec[1] = cz;
        upvec[2] = sy*sz;

        // To keep the camera always pointing upwards, if the y-component
        // of the up vector is negative, invert the whole up vector
        if (upvec[1]<0) {
            upvec[0]=-upvec[0];
            upvec[1]=-upvec[1];
            upvec[2]=-upvec[2];
        }
    }

    public void view(GLU glu) {
        glu.gluLookAt(getEyeX(), getEyeY(), getEyeZ(),
                0.0f, 0.0f, 0.0f,
                getUpVecX(), getUpVecY(), getUpVecZ());
    }
}
