package scene.styling;

/**
 * Created by vasily on 04/12/15.
 */
public class BlackRubber extends Material{
    public BlackRubber() {
        super(
                new float[]{0.02f, 0.02f, 0.02f, 1.0f},
                new float[]{0.01f, 0.01f, 0.01f, 1.0f},
                new float[]{0.4f, 0.4f, 0.4f, 1.0f},
                0.078125f,
                new float[]{0.0f, 0.0f, 0.0f, 1.0f}
        );
    }
}

