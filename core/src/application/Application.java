package application;

import com.badlogic.gdx.Game;
import controller.Controller;
import controller.MenuScreenController;
import controller.utility.Eventbus;
import model.*;
import view.MainMenuScreen;
import view.ScreenManger;
import view.View;
import com.badlogic.gdx.ApplicationAdapter;

public class Application extends Game {
	private Model model;
	private MainMenuScreen mainMenuScreen;
	private View view;
	private Controller controller;
	@Override
	public void create () {
		Eventbus viewEventBus = new Eventbus();
		MenuScreenController menuScreenController = new MenuScreenController(viewEventBus);
		mainMenuScreen = new MainMenuScreen(menuScreenController);
		//new ScreenManger(this, mainMenuScreen, view, viewEventBus);
		model = ModelFactory.makeModel("Flowerfield");
		view = new View(model);
		view.initialize();
		controller = new Controller(model);
		setScreen(mainMenuScreen);
	}

	@Override
	public void render () {
		controller.update(model.getPlayer());
		view.update();
		model.update();
		Time.getInstance().tick();
	}

	@Override
	public void dispose () {
		view.dispose();
	}
}
