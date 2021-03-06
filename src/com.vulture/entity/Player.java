package com.vulture.entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.vulture.Assets.AssetStuff;

public class Player implements Sortable {
    public  final static float ANIMATION_TIMMING=0.1f;//
    private  PlayerCoor front;
    private int x;
    private int y;
//to keep track of the animation we wanna add those world cordinates to determine accurately where the sprite gonna be drawn at
    private float xWorld,yWorld;
// we gonna use tweening to make the mouvement smooth
    private  int xStart,yStart;//to determine the time when the aniomation started
    private int xFinish,yFinish;
    private PlayerActions state;
    private float tWalking;
    private Animations animation;
    private float tAnimation=0.3f;//the time the animation going to take
    private boolean tweak;
    private Texture texture;
    public Player(int x,int y,Map map,Animations animation){//it willbe better not store the cordinates alone in enum to handle the input and the overloading at each time
        this.x=x;
        this.y=y;
        // it s better at first to make  the player cordinates relatively close to the World's cordinates
        this.xWorld=0.75f*x;
        this.yWorld=0.75f*y;
        map.getTile(x,y).setPlayer(this);//this one is a bit tricky
        this.state=PlayerActions.STANDING;// as it the most natural way to make it
        this.front=PlayerCoor.Up;//charchter looking at us
        this.animation=animation;
    }
    public float getxWorld(){
        return this.xWorld;
    }
    public float getyWorld(){
        return this.yWorld;
    }
    public boolean move(PlayerCoor coordinates) {
        Map map=new Map(22,17);
        //a typical move (variation of position) function in 2 d plan
        if(state==PlayerActions.WALKING) {

            if (front==coordinates) {
                tweak = true;
            }
            return false;
        }

        if (x + coordinates.getDx() > map.getW()-1 || x + coordinates.getDx() < 0 || y + coordinates.getDy() < 0 || y + coordinates.getDy() > map.getH()-1) {
            //SETTING BOUNDARIES CONDITION
            return false;//meaning you are out of bounds
        }

        if (map.getTile(x + coordinates.getDx(), y + coordinates.getDy()).getPlayer() != null) return false;// this condition is to make sure that the target tile isn't occupied
        if(state!=PlayerActions.STANDING){
            // we must add this condition  too for it to make sense
            return false;
        }
        else {
            animationInitMove(x,y,coordinates);
            map.getTile(x, y).setPlayer(null);
            x += coordinates.getDx();
            y += coordinates.getDy();
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
    public void animationInitMove(int x,int y,PlayerCoor coordinates){

        this.front=coordinates;
        this.xStart=x;
        this.yStart=y;
        this.xFinish=x+coordinates.getDx();//i love the structure it s elegant  and neat how thing fit into pieces so well
        this.yFinish=y+coordinates.getDy();
        tAnimation=0f;
        this.xWorld=x;
        this.yWorld=y;
        //Last but not least at each time we move the player action enum changes therfore
        state=PlayerActions.WALKING;
    }
    public  void reset(){
        this.xWorld=xFinish;
        this.yWorld=yFinish;
        this.xStart=0;
        this.yStart=0;
        //this.xFinish=0;
        //this.yFinish=0;

    }
    public void animationDestroyMove(){//oblitirate that bitch xD
        state=PlayerActions.STANDING;
        this.reset();
       //
    }
    // now comes the tweening part where we gonna update x,y world positons
    public void updateWorldCord(float DELTA ){
            tWalking+=(float)DELTA;
            tAnimation+=(float)DELTA ;//meaning the animating going to update each frame once (time between one frame and an other is DELTA)
            // Here is the tweening to keep updating world cordinate at each frame
            xWorld= Interpolation.pow2.apply(xStart,xFinish,tAnimation/ANIMATION_TIMMING);
            yWorld= Interpolation.pow2.apply(yStart,yFinish,tAnimation/ANIMATION_TIMMING);
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


    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    @Override
    public float getWorldX() {
        return 0;
    }

    @Override
    public float getWorldY() {
        return 0;
    }

    public  void setTexture(Texture t){
        this.texture=t;
    }
    public Texture getTexture(){
        return this.texture;
    }
    public TextureRegion getSprite(){
        if(state==PlayerActions.STANDING){
            return animation.getStanding(front);
        }
        else if (state == PlayerActions.WALKING){
           // return  animation.getWalking(front.getDx(), front.getDy()).getWalking(front.getDx(), front.getDy());
            return animation.getWalking(front).getKeyFrame(tWalking);
        }
        //we gonna discuss other cases as we go along
        return animation.getStanding(front);
    }

    @Override
    public float getSizeX() {
        return 0;
    }

    @Override
    public float getSizeY() {
        return 0;
    }
}
