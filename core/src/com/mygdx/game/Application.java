package com.mygdx.game;

import com.badlogic.gdx.Game;
import controller.Controller;
import controller.MenuScreenController;
import model.*;
import utility.Eventbus;
import view.BaseScreen;
import view.ScreenFactory;
import view.ScreenManager;
import view.View;
import com.badlogic.gdx.ApplicationAdapter;

public class Application extends Game {
	private Model model;
	private View view;

	private Controller controller;
	@Override
	public void create () {
		Eventbus viewEventBus = new Eventbus();
		MenuScreenController menuScreenController = new MenuScreenController(viewEventBus);
		BaseScreen menuScreen = ScreenFactory.createMenuScreen(menuScreenController);
		new ScreenManager(this, menuScreen, view, viewEventBus);
		model = ModelFactory.makeModel("TestMap");
		view = new View(model);
		view.initialize();
		controller = new Controller(model);

	}

	@Override
	public void render () {
		controller.update();
		view.update();
		model.update();

	}

	@Override
	public void dispose () {
		view.dispose();
	}
}
