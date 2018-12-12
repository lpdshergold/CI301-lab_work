package com.allsopg.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import utility.BonusSprite;
import utility.Constants;
import utility.UniversalResource;

import static com.badlogic.gdx.Gdx.graphics;
import static utility.Constants.BACKGROUND_COLOR;

public class TestTweenAnimation implements ApplicationListener{
    private SpriteBatch batch;
    private float animationTime;
    BonusSprite bp;
    private ScreenViewport viewport;
    private BitmapFont font;
    public static String text;


    @Override
    public void create() {
        text = "Here comes philip the bouncing ball!";
        viewport = new ScreenViewport();
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font/newspaper.fnt"));

        bp = new BonusSprite(Constants.BALL_ATLAS, new Texture(Constants.SIZE),new Vector2(100, 100));
        bp.runRoutine("startroutine");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        animationTime+= graphics.getDeltaTime();
        UniversalResource.getInstance().tweenManager.update(animationTime);
        bp.update(animationTime);

        //draw
        batch.begin();
        //changing the divisions after getWidth and getHeight moves the placement of the font
        //can set the exact position of the font (or other objects)
        //to set, just get rid of graphics.getWidth() (or height) and change to the exact
        //pixels that you want to use, for example, -> font.draw(batch, text, 100, 100);
        font.draw(batch, text, graphics.getWidth()/4, graphics.getHeight()/2);
                bp.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void dispose() { }
}
