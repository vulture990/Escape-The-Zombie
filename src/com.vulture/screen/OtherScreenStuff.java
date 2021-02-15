package com.vulture.screen;

import com.badlogic.gdx.Screen;
import com.vulture.App;

public abstract class OtherScreenStuff implements Screen {
    //we need sort like a root to our game let s start with this
    private App app;
    public OtherScreenStuff(App app){
        this.app=app;
    }
    public  abstract void dispose();
    public abstract void hide();
    public abstract void render(float f);

}
