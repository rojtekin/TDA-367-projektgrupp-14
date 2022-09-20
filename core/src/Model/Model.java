package Model;

public class Model implements IModel {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    public void initialize() {
        PlayerCharacter.instance();
    }
}
