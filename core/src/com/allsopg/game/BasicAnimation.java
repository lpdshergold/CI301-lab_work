package com.allsopg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Comparator;

public class BasicAnimation implements ApplicationListener {
    static final Color BACKGROUND_COLOR = new Color(1,1,1,1);
    Viewport viewport;
    OrthographicCamera camera;
    SpriteBatch batch;
    TextureAtlas atlas;

    static final float FRAME_DURATION = 1.0f / 30.0f;
    private static final float SCENE_WIDTH = 12.80f;
    private static final float SCENE_HEIGHT = 7.20f;

    Animation threeRingsAnim;
    float animationTime;


    @Override
    public void create(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);



        camera = (OrthographicCamera)viewport.getCamera();
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("texture_atlas/ring_assets.atlas"));
        //load animation
        Array<TextureAtlas.AtlasRegion>ringRegions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        ringRegions.sort(new RegionComparator());
        threeRingsAnim = new Animation(FRAME_DURATION,ringRegions,Animation.PlayMode.LOOP);


        // Position the camera
        camera.position.set(SCENE_WIDTH * 0.5f, SCENE_HEIGHT * 0.5f, 0.0f);
    }

    @Override
    public void resize(int width, int height) {
            camera.viewportWidth = width;
            camera.viewportHeight = height;
            camera.position.set(width/2f, height/2f, 0);
        }

    @Override
    public void render(){
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b,BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        animationTime += Gdx.graphics.getDeltaTime();
        camera.update();
        batch.setProjectionMatrix(camera.combined);


        batch.begin();
        TextureRegion ringFrame = (TextureRegion) threeRingsAnim.getKeyFrame(animationTime);
        int width = ringFrame.getRegionWidth();
        int height = ringFrame.getRegionHeight();
        float originX = width * 0.5f;
        float originY = height * 0.5f;

        batch.draw(ringFrame,camera.position.x-originX,
                camera.position.y-originY,
                originX,originY,
                width,height,
        1,1,0);
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


    private static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion>{
        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2) {
            return region1.name.compareTo(region2.name);
        }
    }

}
