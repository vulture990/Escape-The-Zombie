package com.vulture;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Game {
    public static  void main(String[] args ){
        LwjglApplicationConfiguration cfg=new LwjglApplicationConfiguration();
        cfg.height=1200;
        cfg.width=1400;
        cfg.vSyncEnabled=true;
        new LwjglApplication(new App(),cfg);
    }
}
