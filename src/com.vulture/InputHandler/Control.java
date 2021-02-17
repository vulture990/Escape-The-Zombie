package com.vulture.InputHandler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g3d.attributes.PointLightsAttribute;
import com.vulture.entity.Player;
public class Control extends InputAdapter {
    private Player player;// implicitly created
    private boolean UP, DOWN, LEFT, RIGHT;

    public Control(Player player) {
        this.player = player;
    }

    public boolean keyDown(int keycode) {
        boolean flag = false;
        switch (keycode) {
            case Input.Keys.UP:
                // flag = player.move(0, 1);
                UP = true;
                break;
            case Input.Keys.DOWN:
                //flag= player.move(0,-1);
                DOWN = true;
                break;
            case Input.Keys.RIGHT:
                //flag= player.move(1, 0);
                LEFT = true;
                break;
            case Input.Keys.LEFT:
                //flag= player.move(-1, 0);
                RIGHT = true;
                break;
        }
        return flag;// a way to ignore the event if clicked
    }

    public boolean keyUp(int keycode) {
        boolean flag = false;
        switch (keycode) {
            case Input.Keys.UP:
                // flag = player.move(0, 1);
                UP = false;
                break;
            case Input.Keys.DOWN:
                //flag= player.move(0,-1);
                DOWN = false;
                break;
            case Input.Keys.RIGHT:
                //flag= player.move(1, 0);
                LEFT = false;
                break;
            case Input.Keys.LEFT:
                //flag= player.move(-1, 0);
                RIGHT = false;
                break;
        }
        return false;
    }
    public void update(float delta){
        if(UP){
            player.move(0,1);
            return;
        }
        if(DOWN){
            player.move(0,-1);
            return;
        }
        if(LEFT){
            player.move(-1,0);
            return;
        }
        if(RIGHT){
            player.move(1,0);
            return;
        }
    }
}
