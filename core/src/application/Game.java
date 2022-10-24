package application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import controller.Controller;
import model.*;
import view.View;
import com.badlogic.gdx.ApplicationAdapter;

public class Game extends ApplicationAdapter {
	private Model model;
	private View view;
	private Controller controller;
	private boolean gamePaused = true;
	@Override
	public void create () {
		model = ModelFactory.makeModel("Flowerfield");
		view = new View(model);
		view.initialize();
		model.addObserver(view);
		controller = new Controller(model.getPlayer());
	}

	@Override
	public void render () {
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			pauseGame();
		}
		if (!gamePaused) {
			controller.update();
			model.update();
			Time.getInstance().tick();
		}
		view.update(gamePaused);
	}

	@Override
	public void dispose () {
		view.dispose();
	}

	private void pauseGame() {
		gamePaused = !gamePaused;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}