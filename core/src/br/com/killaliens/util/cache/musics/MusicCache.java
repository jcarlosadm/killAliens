package br.com.killaliens.util.cache.musics;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * MusicCache
 * TODO Flyweight pattern
 */
public class MusicCache {
    
    private static final String MUSIC_FOLDER = "musics";
    
    private static Map<String, Music> musics = new HashMap<String, Music>();
    
    private MusicCache(){
    }
    
    /**
     * get music object
     * @param musicName
     * @return Music instance
     */
    public static Music getMusic(String musicName){
        if (musics.containsKey(musicName)) {
            return musics.get(musicName);
        }
        
        Music music = null;
        
        try {
            music = Gdx.audio.newMusic(Gdx.files.internal(MUSIC_FOLDER+
                    File.separator + musicName));
            if (music != null) {
                musics.put(musicName, music);
            }
        } catch (GdxRuntimeException e) {
            Gdx.app.log("music error", "error to load music");
            e.printStackTrace();
            Gdx.app.exit();
        }
        
        return music;
    }
    
    /**
     * Dispose all musics
     */
    public static void dispose() {
        for (Music music : musics.values()) {
            music.dispose();
        }
    }
}
