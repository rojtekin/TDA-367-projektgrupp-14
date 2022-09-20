package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

public class Game extends ApplicationAdapter {
	Model model;
	View view;
	Controller controller;
	@Override
	public void create () {
		model = new Model();
		model.initialize();
		view = new View(model);
		view.initialize();
		controller = new Controller(model);
	}

	@Override
	public void render () {
		controller.update();
		view.update();
	}

	@Override
	public void dispose () {
		view.dispose();
	}
}