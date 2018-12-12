package com.allsopg.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntMap;

public class soundLink {
    private IntMap<Sound> soundKeys;

    public soundLink() {
        soundKeys = new IntMap<Sound>();

        //Create new sounds variables
        Sound sound1 = Gdx.audio.newSound(Gdx.files.internal(""));
        Sound sound2 = Gdx.audio.newSound(Gdx.files.internal(""));
        Sound sound3 = Gdx.audio.newSound(Gdx.files.internal(""));

        //Asigning sounds to a specific key
        soundKeys.put(1, sound1);
        soundKeys.put(2, sound2);
        soundKeys.put(3, sound3);
    }

    public boolean play (int keycode) {
        Sound sound = soundKeys.get(keycode);
        if (sound != null)
        {
            sound.play();
        }
        return false;
    }

    public void dispose() {
        for (Sound sound : soundKeys.values()) {
            sound.dispose();
        }
    }
}
