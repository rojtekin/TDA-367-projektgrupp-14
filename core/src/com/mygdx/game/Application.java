package com.mygdx.game;

import com.badlogic.gdx.Game;
import controller.Controller;
import controller.MenuScreenController;
import model.*;
import utility.Eventbus;
import view.BaseScreen;
import view.ScreenFactory;
import view.ScreenManager;
import view.GameScreen;

public class Application extends Game {
	private Model model;
	private GameScreen gameScreen;

	private Controller controller;
	@Override
	public void create () {
		Eventbus viewEventBus = new Eventbus();
		MenuScreenController menuScreenController = new MenuScreenController(viewEventBus);
		BaseScreen menuScreen = ScreenFactory.createMenuScreen(menuScreenController);
		BaseScreen GameScreen = ScreenFactory.createGameScreen(model);
		new ScreenManager(this, menuScreen, GameScreen, viewEventBus);
		model = ModelFactory.makeModel("TestMap");
		gameScreen = new GameScreen(model);
		gameScreen.initialize();
		controller = new Controller(model);

	}


	@Override
	public void render () {
		super.render();
		controller.update();
		gameScreen.update();
		model.update();

	}

	@Override
	public void dispose () {
		gameScreen.dispose();
	}
}
