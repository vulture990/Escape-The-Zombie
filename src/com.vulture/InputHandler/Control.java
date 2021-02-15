package com.vulture.InputHandler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.vulture.entity.Player;

public class Control extends InputAdapter {
    private Player player;// implicitly created
    public Control(Player player){
        this.player=player;
    }
    public boolean keyDown(int keycode){
        if(keycode== Input.Keys.UP){
            player.move(0,1);

        }
        if(keycode==Input.Keys.DOWN){
            player.move(0,-1);
        }
        if(keycode==Input.Keys.RIGHT) {
            player.move(1, 0);
        }
        if(keycode==Input.Keys.LEFT) {
            player.move(-1, 0);
        }
        return false;// a way to ignore the event if clicked
    }
}
