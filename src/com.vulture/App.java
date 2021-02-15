package com.vulture;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.vulture.screen.Gscreen;

public class App extends Game {
    private Gscreen screen;
    public void  create(){
        screen =new Gscreen(this);
        this.setScreen(screen);
    }
    public void render(){
        // we wanted to clear the screen after calling each time super render and  do this every frame
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // and the super render will take care of calling the render method and we will be set by then
        super.render();
    }
}
