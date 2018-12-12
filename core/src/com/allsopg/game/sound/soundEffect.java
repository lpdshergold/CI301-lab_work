package com.allsopg.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntMap;

public class soundEffect implements InputProcessor{

    private IntMap<Sound> soundKeys;

    public soundEffect() {
        soundKeys = new IntMap<Sound>();
        //Create new sounds variables
        Sound sound1 = Gdx.audio.newSound(Gdx.files.internal(""));
        Sound sound2 = Gdx.audio.newSound(Gdx.files.internal(""));
        Sound sound3 = Gdx.audio.newSound(Gdx.files.internal(""));

        //Asigning sounds to a specific key
        soundKeys.put(Input.Keys.NUM_1, sound1);
        soundKeys.put(Input.Keys.NUM_2, sound2);
        soundKeys.put(Input.Keys.NUM_3, sound3);
    }
    @Override
    public boolean keyDown(int keycode) {
        //get soundKey keycodes
        Sound sound = soundKeys.get(keycode);
        //check if sound != null (if not go through if statement)
        if (sound != null) {
            //play sounds
            sound.play();
        }
        return false;
    }

    //Free memory when can
    public void dispose() {
        for (Sound sound : soundKeys.values()) {
            sound.dispose();
        }
    }

    @Override
    public boolean keyUp(int keycode) { return false; }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }

    @Override
    public boolean scrolled(int amount) { return false; }
}
