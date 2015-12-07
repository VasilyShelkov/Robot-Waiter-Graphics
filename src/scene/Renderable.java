package scene;

import com.jogamp.opengl.GL2;

/**
 * Created by vasily on 03/12/15.
 */
public interface Renderable {

    void render(GL2 gl);

    void transform(GL2 gl);

    void draw(GL2 gl);

    void update(GL2 gl);
}
