package view;

import controller.MenuScreenController;
import model.Model;

public abstract class ScreenFactory {

    public static BaseScreen createMenuScreen(MenuScreenController menuScreenController){

        return new MenuScreen(menuScreenController);
    }


   public static BaseScreen createGameScreen(Model model){

        return new GameScreen(model);
    }






}

