package com.allsopg.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SpriteExample_3 extends InputAdapter implements ApplicationListener {

    static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 1);
    private ScreenViewport viewport;

    private SpriteBatch batch;

    private AnimatedSprite sprRing;
    private float animationTime;

    private static final String size = "data/mediumSize.png";


    @Override
    public void create() {
        viewport = new ScreenViewport();
        batch = new SpriteBatch();
        sprRing = new AnimatedSprite("texture_atlas/ring_assets.atlas", new Vector2(100, 100), new Texture(size));
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void render() {
        //Clear screen
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update game time
        animationTime+=Gdx.graphics.getDeltaTime();
        sprRing.update(animationTime);

        //Draw
        batch.begin();
        sprRing.draw(batch);
        batch.end();
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void dispose() { }

    public boolean touchDown (int screenX, int screenY, int pointer, int button)
    {
        if (button == Input.Buttons.LEFT)
        {
            sprRing.updatePosData(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
        }
        return true;
    }
}
