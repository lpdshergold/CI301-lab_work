package com.allsopg.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ViewPortExample implements ApplicationListener {
    static final Color BACKGROUND_COLOR = new Color(1,1,1,1);
    private static final float MIN_SCENE_WIDTH = 800.0f;
    private static final float MIN_SCENE_HEIGHT = 600.0f;
    private static final float MAX_SCENE_WIDTH = 1280.0f;
    private static final float MAX_SCENE_HEIGHT = 720.0f;
    private static String TAG = "com.allsopg.game.ViewPortExample";

    private ArrayMap<String, Viewport> viewports;
    private int currentViewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture background;


    @Override
    public void create() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("data/space.jpg"));
        createViewports();
        selectNextViewport();
    }

    private void selectNextViewport() {
        Gdx.app.log(TAG, "SELECTNEXTVIEWPORT");
        currentViewport = (currentViewport + 1) % viewports.size;
        viewports.getValueAt(currentViewport).update(Gdx.graphics.getWidth
                (), Gdx.graphics.getHeight());
        Gdx.app.log(TAG, "selected " + viewports.
                getKeyAt(currentViewport));
    }

    private void createViewports() {
        viewports = new ArrayMap<String, Viewport>();
        viewports.put("StretchViewport", new StretchViewport(MIN_SCENE_WIDTH, MIN_SCENE_HEIGHT, camera));
        viewports.put("FitViewport", new FitViewport(MIN_SCENE_WIDTH, MIN_SCENE_HEIGHT, camera));
        viewports.put("FillViewport", new FillViewport(MIN_SCENE_WIDTH, MIN_SCENE_HEIGHT, camera));
        viewports.put("ScreenViewport", new ScreenViewport(camera));
        viewports.put("ExtendViewport (no max)", new ExtendViewport(MIN_SCENE_WIDTH, MIN_SCENE_HEIGHT, camera));
        viewports.put("ExtendViewport (max)", new ExtendViewport(MIN_SCENE_WIDTH, MIN_SCENE_HEIGHT,
                MAX_SCENE_HEIGHT, MAX_SCENE_WIDTH, camera));
        currentViewport = -1;
    }

    @Override
    public void resize(int width, int height) {
        viewports.getValueAt(currentViewport).update(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b,BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            selectNextViewport();
        }
        batch.begin();
        batch.draw(background, -background.getWidth() * 0.5f, -background.
                getHeight() * 0.5f);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
