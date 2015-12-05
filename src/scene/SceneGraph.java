package scene;

import com.jogamp.opengl.GL2;

import java.util.ArrayList;

/**
 * Created by vasily on 03/12/15.
 */
public class SceneGraph extends Node {

    ArrayList<Node> children;

    public SceneGraph() {
        super();
        children = new ArrayList<>();
    }

    @Override
    public void render(GL2 gl) {
        for (Node child: children) {
            gl.glPushMatrix();
                child.render(gl);
            gl.glPopMatrix();
        }
    }

    public void addChild(Node node){
        children.add(node);
    };
}
