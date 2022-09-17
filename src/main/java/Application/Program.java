package Application;

import View.IngameScreen;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;

public class Program {

    public static void main(String[] args) {
        setWindowTitleText();
        Game.init(args);
        setWindowData();
        loadResources();
        Game.world().loadEnvironment("TestMap");
        Game.screens().add(new IngameScreen());
        Game.start();

    }

    private static void setWindowTitleText() {
        Game.info().setName("hej");
        Game.info().setVersion("0.01");
        Game.info().setSubTitle("");
    }

    private static void setWindowData() {
        Game.graphics().setBaseRenderScale(4f);
    }

    private static void loadResources() {
        Resources.load("game.litidata");
    }
}