package view;

import controller.MenuScreenController;

public abstract class ScreenFactory {

    public static BaseScreen createMenuScreen(MenuScreenController menuScreenController){

        return new MenuScreen(menuScreenController);
    }


  //  public static BaseScreen createGameScreen(){

        //return new GameScreen();
 //   }






}

