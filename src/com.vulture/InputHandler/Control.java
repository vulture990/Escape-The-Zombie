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
        switch (keycode){
            case Input.Keys.UP:
                flag= player.move(0,1);
                break;
            case Input.Keys.DOWN:
                flag= player.move(0,-1);
                break;
            case Input.Keys.RIGHT:
                flag= player.move(1, 0);
                break;
            case Input.Keys.LEFT:
             flag= player.move(-1, 0);
             break;
        }
        return flag;// a way to ignore the event if clicked
    }
}
