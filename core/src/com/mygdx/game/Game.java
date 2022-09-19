package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

public class Game extends ApplicationAdapter {
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 480;
	Model model;
	View view;
	Controller controller;
	@Override
	public void create () {
		model = new Model(new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 32, 32, 5));
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
