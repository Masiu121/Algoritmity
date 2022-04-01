package com.oxology.algoritmity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.algoritmity.blocks.StartBlock;
import com.oxology.algoritmity.blocks.StopBlock;

public class Algoritmity extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	GameManager gameManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		gameManager = new GameManager();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		gameManager.draw(batch);

		update();
	}

	public void update() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.O)) {
			gameManager.addBlock(new StartBlock(100, 100, gameManager));
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			gameManager.addBlock(new StopBlock(100, 100, gameManager));
		}

		gameManager.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}