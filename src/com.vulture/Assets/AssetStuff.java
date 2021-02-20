package com.vulture.Assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.vulture.entity.Animations;

import java.util.ArrayList;
import java.util.List;


public class AssetStuff {
    private int x,y;// actually these cordinates the define the bottom left corner of an asset

    private TextureRegion texture;
    private float sizeX,sizeY;// defines the right coordinates to where the asset ends

    private List<GridPoint2> tiles;
    private boolean walk;
    public AssetStuff(int x,int y,TextureRegion texture,float sizeX,float sizeY,GridPoint2[] tiles){
        this.x=x;
        this.y=y;
        this.texture=texture;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.tiles=new ArrayList<GridPoint2>();
        for(GridPoint2 p : tiles){
            this.tiles.add(p);
        }
        this.walk=true;
    }//by default assuming that the first constructor has walkable by default
    //we need an other constructor for walkable Assets
    public AssetStuff(int x, int y, boolean walk, float sizeX, float sizeY,GridPoint2[] tiles){
        this.x=x;
        this.y=y;
        this.walk=walk;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.tiles=new ArrayList<GridPoint2>();
    }

    public void update(float delta) {

    }

    public int getX() {
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}


