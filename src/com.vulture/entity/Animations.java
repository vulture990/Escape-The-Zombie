package com.vulture.entity;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Animations {


    //basicly here what i m trying to do is essentially make animations but the way i'm going about it might not be the best way i couldn"t make a pair of mouvement cordinates as my key to the map that going to contain the animation sprites Hence why i did the enumeration
    // the animation are realted to the action of walking
    private Map<PlayerCoor,Animation> walking;
    // for standing i only need the texture to be rendered
    private Map<PlayerCoor,TextureRegion> standing;
    // lets forget about swimming and sprinting for now


    public Animations(TextureRegion UP,TextureRegion DOWN,TextureRegion LEFT,TextureRegion RIGHT
            ,Animation Up,Animation Down, Animation Left , Animation Right
    ){
        standing=new Hashtable<PlayerCoor,TextureRegion>();
        standing.put(PlayerCoor.Up,UP);
        standing.put(PlayerCoor.Down,DOWN);
        standing.put(PlayerCoor.Left,LEFT);
        standing.put(PlayerCoor.Right,RIGHT);
        walking= new HashMap<PlayerCoor, Animation>();//i cou
        walking.put(PlayerCoor.Up,Up);
        walking.put(PlayerCoor.Down,Down);
        walking.put(PlayerCoor.Left,Left);
        walking.put(PlayerCoor.Right,Right);

    }
    public TextureRegion getStanding(PlayerCoor coor){
        // xy=new PlayerCordinates(dx,dy);
       // xy.setDx(dx);
        //xy.setDy(dy);
        return standing.get(coor);
    }
    public Animation getWalking(PlayerCoor coor){
        //xy.setDx(dx);
        //xy.setDy(dy);
        return walking.get(coor);
    }

}
