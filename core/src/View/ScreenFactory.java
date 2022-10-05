package View;

import Controller.MenuScreenController;

public abstract class ScreenFactory {

    public static BaseScreen createMainScreen(MenuScreenController menuScreenController){

        return new MenuScreen(menuScreenController);
    }


  //  public static BaseScreen createGameScreen(){

        //return new GameScreen();
 //   }






}

