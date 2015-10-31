package br.com.killaliens.util.sounds;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundCache {
    
    private static final String SOUND_FOLDER = "sounds";
    
    private static Map<String, Sound> sounds = new HashMap<String, Sound>();
    
    public static Sound getSound(String soundName){
        if (sounds.containsKey(soundName)) {
            return sounds.get(soundName);
        }
        
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER +
                File.separator + soundName));
        if (sound != null) {
            sounds.put(soundName, sound);
        }
        
        return sound;
    }
    
    public static void dispose() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
    }
}
