package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.Controller.Controller;
import com.mygdx.game.Model.IModel;
import com.mygdx.game.Model.Model;
import com.mygdx.game.View.View;

public class Game extends ApplicationAdapter {
	IModel model;
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
