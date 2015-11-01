package br.com.killaliens.util.cache.sounds;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * SoundCache
 * TODO Flyweight pattern
 */
public class SoundCache {
    
    private static final String SOUND_FOLDER = "sounds";
    
    private static Map<String, Sound> sounds = new HashMap<String, Sound>();
    
    public static Sound getSound(String soundName){
        if (sounds.containsKey(soundName)) {
            return sounds.get(soundName);
        }
        
        Sound sound = null;
        
        try {
            sound = Gdx.audio.newSound(Gdx.files.internal(SOUND_FOLDER +
                    File.separator + soundName));
            if (sound != null) {
                sounds.put(soundName, sound);
            }
        } catch (GdxRuntimeException e) {
            System.out.println("error to load audio");
            Gdx.app.exit();
        }
        
        return sound;
    }
    
    public static void dispose() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
    }
}
