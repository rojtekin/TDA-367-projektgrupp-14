package com.mygdx.game;

import Controller.Controller;
import Model.*;
import View.View;
import com.badlogic.gdx.ApplicationAdapter;

public class Game extends ApplicationAdapter {
	private Model model;
	private View view;
	private Controller controller;
	@Override
	public void create () {
		model = ModelFactory.makeModel();
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
