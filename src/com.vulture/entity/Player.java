package com.vulture.entity;

import com.badlogic.gdx.math.Interpolation;

public class Player {
    public  final static float ANIMATION_TIMMING=0.7f;//
    private int x;
    private int y;
//to keep track of the animation we wanna add those world cordinates to determine accurately where the sprite gonna be drawn at
    private float xWorld,yWorld;
// we gonna use tweening to make the mouvement smooth
    private  int xStart,yStart;//to determine the time when the aniomation started
    private int xFinish,yFinish;
    private PlayerActions state;
    private float tAnimation=0.25f;//the time the animation going to take
    public Player(int x,int y,Map map){
        this.x=x;
        this.y=y;
        // it s better at first to make  the player cordinates relatively close to the World's cordinates
        this.xWorld=0.75f*x;
        this.yWorld=0.75f*y;
        map.getTile(x,y).setPlayer(this);//this one is a bit tricky
        this.state=PlayerActions.STANDING;// as it the most natural way to make it

    }
    public float getxWorld(){
        return this.xWorld;
    }
    public float getyWorld(){
        return this.yWorld;
    }
    public boolean move(int dx,int dy) {
        //a typical move (variation of position) function in 2 d plan
        Map map=new Map(22,17);

        if (x + dx > map.getW() || x + dx < 0 || y + dy < 0 || y + dy > map.getH()) {
            //SETTING BOUNDARIES CONDITION
            return false;//meaning you are out of bounds
        }

        if (map.getTile(x + dx, y + dy).getPlayer() != null) return false;// this condition is to make sure that the target tile isn't occupied
        if(state!=PlayerActions.STANDING){
            // we must add this condition  too for it to make sense
            return false;
        }
        else {
            animationInitMove(x,y,dx,dy);
            map.getTile(x, y).setPlayer(null);
            x += dx;
            y += dy;
            map.getTile(x, y).setPlayer(this);
            return true;
        }
    }
    /*
    public  boolean checkBounds(int dx,int dy) {
        if (x + dx > map.getW() || x + dx < 0 || y + dy < 0 || y + dy > map.getH()) {//SETTING BOUNDARIES CONDITION
            return false;//meaning you are out of bounds
        }

        if (map.getTile(x + dx, y + dy).getPlayer() != null) return false;
        else return true;
}*/
    public void animationInitMove(int x,int y,int dx,int dy){
        this.xStart=x;
        this.yStart=y;
        this.xFinish=x+dx;//i love the structure it s elegant  and neat how thing fit into pieces so well
        this.yFinish=y+dy;
        tAnimation=0f;
        this.xWorld=x;
        this.yWorld=y;
        //Last but not least at each time we move the player action enum changes therfore
        state=PlayerActions.WALKING;
    }
    public void animationDestroyMove(){
        state=PlayerActions.STANDING;

    }
    // now comes the tweening part where we gonna update x,y world positons
    public void updateWorldCord(float DELTA ){
        if(state==PlayerActions.WALKING){
            tAnimation+=DELTA;//meaning the animating going to update each frame once (time between one frame and an other is DELTA)
            // Here is the tweening to keep updating world cordinate at each frame
            xWorld= Interpolation.pow2.apply(xStart,xFinish,tAnimation/ANIMATION_TIMMING);
            yWorld= Interpolation.pow2.apply(yStart,yFinish,tAnimation/ANIMATION_TIMMING);
            //once updated time to destroy the animation after checking ofc that the tANIMATION IS BIGGER THAN animation timming that we set so that should be out condition
            if(tAnimation>ANIMATION_TIMMING){
                animationDestroyMove();
            }
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
