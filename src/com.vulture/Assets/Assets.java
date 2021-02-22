package com.vulture.Assets;

import com.badlogic.gdx.math.GridPoint2;
import com.vulture.entity.Map;
import com.vulture.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Assets {

    private int x,y;
    private Map map;
    private List <Player> players;
    private List <AssetStuff> objects;

    public Assets(int w,int h){
        this.map=new Map(w,h);
        players=new ArrayList<Player>();
        objects=new ArrayList<AssetStuff>();
    }


    public void addPlayer(Player p){
        //we wanna make sure that the player is added to the tile map as well to the tile map
        map.getTile(p.getX(),p.getY());
        players.add(p);
    }
    public void addAssets(AssetStuff a) {

        for (GridPoint2 p : a.getTiles()) {

            map.getTile(a.getX()+p.x , a.getY()+p.y).setAssets(a);

        }
        objects.add(a);
    }
    public void update(float delta) {
        for (Player p : players) {
            p.updateWorldCord(delta);
        }
        for (AssetStuff a : objects) {
            a.update(delta);
        }
    }
    public Map getMap() {
        return  map;
    }
}

