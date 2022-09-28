package com.mygdx.game;

import Controller.Controller;
import Model.*;
import View.View;
import com.badlogic.gdx.ApplicationAdapter;

public class Game extends ApplicationAdapter {
	Model model;
	View view;
	Controller controller;
	@Override
	public void create () {
		model = new Model();
		model.initialize("TestMap");
		view = new View(model);
		view.initialize();
		controller = new Controller(model, model.getPlayerCharacter());
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
