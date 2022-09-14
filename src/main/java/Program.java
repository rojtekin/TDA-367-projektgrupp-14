import de.gurkenlabs.litiengine.Game;

public class Program {

    public static void main(String[] args) {
        setWindowTitleText();
        Game.init(args);
        Game.start();
    }

    private static void setWindowTitleText() {
        Game.info().setName("hej");
        Game.info().setVersion("0.01");
        Game.info().setSubTitle("");
    }
}