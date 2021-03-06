package com.vulture.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

public class Enemy {
    public  final static float ANIMATION_TIMMING=0.1f;//
    private float tAnimation=0.3f;
    private  PlayerCoor front;
    private int x;
    private int y;
    //to keep track of the animation we wanna add those world cordinates to determine accurately where the sprite gonna be drawn at
    private float xWorld,yWorld;
    // we gonna use tweening to make the mouvement smooth
    private  int xStart,yStart;//to determine the time when the aniomation started
    private int xFinish,yFinish;
    private float tWalking;
    private boolean tweak;
    private Texture texture;
    private Map map;
    public Enemy(int x,int y,Map map,Texture texture){//it willbe better not store the cordinates alone in enum to handle the input and the overloading at each time
        this.x=x;
        this.y=y;
        this.map=map;
        // it s better at first to make  the player cordinates relatively close to the World's cordinates
        this.xWorld=0.75f*x;
        this.yWorld=0.75f*y;
        map.getTile(x,y).setEnemy(this);//this one is a bit tricky
        this.texture=texture;
    }
    public float getxWorld(){
        return this.xWorld;
    }
    public float getyWorld(){
        return this.yWorld;
    }

    public void move(PlayerCoor coordinates) {
            //map=new Map(22,17);
        if (map.getTile(x + coordinates.getDx(), y + coordinates.getDy()).getEnemy() == null){
            animationInitMove(x,y,coordinates);
            map.getTile(x, y).setPlayer(null);
            x += coordinates.getDx();
            y += coordinates.getDy();
            map.getTile(x, y).setEnemy(this);

        }
    }

    public void animationInitMove(int x,int y,PlayerCoor coordinates){
        this.xStart=x;
        this.yStart=y;
        this.xFinish=x+coordinates.getDx();//i love the structure it s elegant  and neat how thing fit into pieces so well
        this.yFinish=y+coordinates.getDy();
        this.xWorld=x;
        this.yWorld=y;
    }
    public  void reset(){
        this.xWorld=xFinish;
        this.yWorld=yFinish;
        this.xStart=0;
        this.yStart=0;
    }
    public void animationDestroyMove(){
        this.reset();
    }
    /*
    public void updateWorldCord(float DELTA ){
        tWalking+=(float)DELTA;
        tAnimation+=(float)DELTA;
        xWorld= Interpolation.pow2.apply(xStart,xFinish,tWalking/ANIMATION_TIMMING);
        yWorld= Interpolation.pow2.apply(yStart,yFinish,tWalking/ANIMATION_TIMMING);
        //once updated time to destroy the animation after checking ofc that the tANIMATION IS BIGGER THAN animation timming that we set so that should be out condition
        if(tAnimation>ANIMATION_TIMMING){
            tWalking-=tAnimation-ANIMATION_TIMMING;
            animationDestroyMove();
            if(tweak){
                //we wanna move in the front dic
                move(front);
            }
        }
        else{
            tWalking=0f;
        }
        tweak=false;
    }


    public void pathFinding(float delta,int x1,int y1){// (x,y) player coordinates
            // given (x1,y1) zombie coordinates we want to find the shortest path to the player coordinates(x,y) each
        //frame the player coordinates are going to updated so we should update(by using move method) also the enemy coordinates for the zombie to follow along
        Tile t=map.getTile(x1,y1);
        Tile t1= map.getTile(this.x,this.y);

    }

     */
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public float getWorldX() {
        return 0;
    }
    public float getWorldY() {
        return 0;
    }
    public  void setTexture(Texture t){
        this.texture=t;
    }
    public Texture getTexture(){
        return this.texture;
    }
    public float getSizeX() {
        return 0;
    }
    public float getSizeY() {
        return 0;
    }
}
