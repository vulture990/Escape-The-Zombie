package com.vulture.Animation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vulture.entity.Player;
import com.vulture.entity.PlayerCordinates;

import java.util.*;

public class Animations {

//basicly here what i m trying to do is essentially make animations but the way i'm going about it might not be the best way i couldn"t make a pair of mouvement cordinates as my key to the map that going to contain the animation sprites Hence why i did the enumeration
// the animation are realted to the action of walking
    private Map<PlayerCordinates,Animations> walking;
    // for standing i only need the texture to be rendered
    private Map<PlayerCordinates,TextureRegion> standing;
    // lets forget about swimming and sprinting for now
    public PlayerCordinates xy;

    public Animations(TextureRegion UP,TextureRegion DOWN,TextureRegion LEFT,TextureRegion RIGHT
            ,Animations Up,Animations Down, Animations Left , Animations Right
    ){
        standing=new Hashtable<PlayerCordinates,TextureRegion>();
        standing.put(PlayerCordinates.Up,UP);
        standing.put(PlayerCordinates.Down,DOWN);
        standing.put(PlayerCordinates.Left,LEFT);
        standing.put(PlayerCordinates.Right,RIGHT);
        walking=  new HashMap<PlayerCordinates,Animations>();//i cou
        walking.put(PlayerCordinates.Up,Up);
        walking.put(PlayerCordinates.Down,Down);
        walking.put(PlayerCordinates.Left,Left);
        walking.put(PlayerCordinates.Right,Right);

    }
    public TextureRegion getStanding(int dx,int dy){
        xy.setDx(dx);
        xy.setDy(dy);
        return standing.get(xy);
    }
    public Animations getWalking(int dx, int dy){
        xy.setDx(dx);
        xy.setDy(dy);
        return walking.get(xy);
    }

}
