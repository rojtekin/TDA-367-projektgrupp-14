package Controller;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import View.IngameScreen;

public class Program {

    public static void main(String[] args) {
        setWindowTitleText();
        Game.init(args);
        // load datafrom gamefile
        Resources.load("game.litidata");
        Game.world().loadEnvironment("Test2ActualMap2");
        Game.screens().add(new IngameScreen());
        Game.start();
    }

    private static void setWindowTitleText() {
        Game.info().setName("hej");
        Game.info().setVersion("0.01");
        Game.info().setSubTitle("");
    }
}