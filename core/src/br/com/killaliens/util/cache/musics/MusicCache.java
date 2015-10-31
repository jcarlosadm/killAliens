package br.com.killaliens.util.cache.musics;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class MusicCache {
    
    private static final String MUSIC_FOLDER = "musics";
    
    private static Map<String, Music> musics = new HashMap<String, Music>();
    
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
            System.out.println("error to load music");
            Gdx.app.exit();
        }
        
        return music;
    }
    
    public static void dispose() {
        for (Music music : musics.values()) {
            music.dispose();
        }
    }
}
