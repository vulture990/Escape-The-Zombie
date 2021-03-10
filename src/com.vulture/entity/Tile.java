package com.vulture.entity;


//import com.vulture.Assets.AssetStuff;

import com.vulture.Assets.AssetStuff;

import java.util.ArrayList;
import java.util.List;

public class Tile  {
    private Ground gr;
    private Player player;
    private Enemy enemy;
    private AssetStuff assets;
    private int gCost;
    private int hCost;
    public Tile previous;
    private int fCost=gCost+hCost;
    public int xposMap;
    public  int yposMap;
    public List<Tile> neighbors=new ArrayList<Tile>();
  //  private AssetStuff objects;
    public Tile(Ground gr){
        this.gr = gr;

    }
    public  Ground getGround()
    { return gr;
    }
    public void setGround(Ground gr){
          this.gr=gr;
    }
    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player player){
        this.player= player;
    }
    public Enemy getEnemy(){
        return enemy;
    }
    public void setEnemy(Enemy enemy){
        this.enemy=enemy;
    }
    public void setgCost(int g){
        this.gCost=g;
    }
    public int getXposMap(){
        return this.xposMap;
    }
    public void setXposMap(int x){
        this.xposMap=x;
    }
    public int getYposMap(){
        return this.yposMap;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }
    public int getfCost(){
        return fCost;
    }

    public void setYposMap(int y){
        this.yposMap=y;
    }
    public int getgCost(){
        return this.gCost;
    }
    public int gethCost(){
        return this.hCost;
    }
    public  void sethCost(int h){
        this.hCost=h;
    }
    public void setAssets(AssetStuff assets) {
        this.assets = assets;
    }
    public AssetStuff getAssets(){
        return this.assets;
    }
    public void addNeighboors(Map map){
        if(this.getXposMap()<map.getW()-1) {
            this.neighbors.add(map.getTile(this.getXposMap() + 1, this.getYposMap()));
        }
        if(this.getXposMap()>0) {
            this.neighbors.add(map.getTile(this.getXposMap() - 1, this.getYposMap()));
        }
        if(this.getYposMap()<map.getH()-1) {
            this.neighbors.add(map.getTile(this.getXposMap(), this.getYposMap() + 1));
        }
        if(this.getYposMap()>0) {
            this.neighbors.add(map.getTile(this.getXposMap(), this.getYposMap() - 1));
        }
    }
}

