package com.vulture.InputHandler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import com.badlogic.gdx.InputProcessor;
import com.vulture.entity.Player;
import com.vulture.entity.PlayerCoor;

public class Control implements InputProcessor {
    private Player player;// implicitly created
    private boolean UP, DOWN, LEFT, RIGHT;

    public Control(Player player) {
        this.player = player;
    }

    public boolean keyDown(int keycode) {

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
        return false;// a way to ignore the event if clicked
    }

    public boolean keyUp(int keycode) {

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

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void update(float delta){
        if(UP){
            player.move(PlayerCoor.Up);
            return;
        }
        if(DOWN){
            player.move(PlayerCoor.Down);
            return;
        }
        if(LEFT){
            player.move(PlayerCoor.Left);
            return;
        }
        if(RIGHT){
            player.move(PlayerCoor.Right);
            return;
        }
    }
}
