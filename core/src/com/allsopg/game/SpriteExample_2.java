package com.allsopg.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SpriteExample_2 extends InputAdapter implements ApplicationListener {

    static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 1);
    private ScreenViewport viewport;
    private SpriteBatch batch;

    private Texture txRegion;
    private Sprite sprRings;

    //To hold an array of colours
    private Array<Color> colors;
    private int currentColor;

    //Vector2 holds x and y values
    private Vector2 position;

    @Override
    public void create() {
        position = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ScreenViewport();
        batch = new SpriteBatch();
        txRegion = new Texture(Gdx.files.internal("gfx/threerings_02.png"));
        sprRings = new Sprite(txRegion);
        sprRings.setPosition(position.x*.5f, position.y*.5f);

        //Add a few colours to the collection
        colors = new Array<Color>();
        colors.add(new Color(Color.YELLOW));
        colors.add(new Color(Color.BLUE));
        colors.add(new Color(Color.FIREBRICK));

        //Set up user input
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void render() {
        //Clear screen
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw
        batch.begin();
        sprRings.draw(batch);
        batch.end();
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button)
    {
        if(button == Input.Buttons.RIGHT)
        {
            currentColor = (currentColor + 1) % colors.size;
            sprRings.setColor(colors.get(currentColor));
        }
        else if(button == Input.Buttons.LEFT)
        {
            position.x = screenX;
            position.y = screenY;
            sprRings.setPosition(position.x - sprRings.getWidth()/2, Gdx.graphics.getHeight() - position.y - sprRings.getHeight()/2);
        }

        return true;
    }

    @Override
    public boolean scrolled (int amount)
    {
        sprRings.rotate(amount * 5.0f);
        return true;
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void dispose() { }
}
