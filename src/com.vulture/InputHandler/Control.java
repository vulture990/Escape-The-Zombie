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
        boolean flag =false;
        if(keycode== Input.Keys.UP){
           flag= player.move(0,1);

        }
        if(keycode==Input.Keys.DOWN){
            flag= player.move(0,-1);
        }
        if(keycode==Input.Keys.RIGHT) {
            flag= player.move(1, 0);
        }
        if(keycode==Input.Keys.LEFT) {
             flag= player.move(-1, 0);
        }
        return flag;// a way to ignore the event if clicked
    }
}
