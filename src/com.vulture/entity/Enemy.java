package com.vulture.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

import java.util.*;

public class Enemy {
    public  final static float ANIMATION_TIMMING=0.1f;//
    private float tAnimation=0.3f;
    private  PlayerCoor front;
    private int x;
    private int y;
    public Tile enemyTile;
    //to keep track of the animation we wanna add those world cordinates to determine accurately where the sprite gonna be drawn at
    private float xWorld,yWorld;
    // we gonna use tweening to make the movement smooth
    private  int xStart,yStart;//to determine the time when the animation started
    private int xFinish,yFinish;
    private float tWalking;
    private boolean tweak;
    private Texture texture;
    private Map map;
    private Player player;
    public Set<Tile> openSet=new HashSet<Tile>();
    public Set<Tile> closedSet=new HashSet<Tile>();
    public List <Tile> totalPath=new ArrayList<Tile>();
    public Enemy(int x,int y,Map map,Texture texture,Player player){//it will be better not store the cordinates alone in enum to handle the input and the overloading at each time
        this.x=x;
        this.y=y;
        this.map=map;
        this.player=player;
        // it s better at first to make  the player cordinates relatively close to the World's cordinates
        this.xWorld=0.75f*x;
        this.yWorld=0.75f*y;
        enemyTile=map.getTile(x,y);
        map.getTile(x,y).setEnemy(this);//this one is a bit tricky
       // player.playerTile.setgCost(enemyTile.gethCost());
        this.texture=texture;
    }
    public void backTrack(Tile current){
        Tile beforeTile=current;
        totalPath.add(beforeTile);
        while (beforeTile.previous!=null){
            System.out.println("tadkhl lhna atbi");
            totalPath.add(beforeTile);
            beforeTile=beforeTile.previous;
        }
    }
    public int heuristic(Tile tile,Tile end,Map map){
        // we gonna be using the manhanten distance for now
        return Math.abs(tile.getXposMap()-end.getXposMap())+Math.abs(tile.getYposMap()-end.getYposMap());
    }
    public void pathFinding(Tile start,Tile end,Map map){
            openSet.add(start);
            List<Tile> path=new ArrayList<Tile>();
            start.setgCost(0);
            start.sethCost(heuristic(start,end,map));
            // fCost represent our best guest and hint as to where the shortest path might be
        Tile current;
        while(!openSet.isEmpty()){
                // IF WE WANT TO OPTIMIZE LATER we can use priority queue so that this search will be O(1)
                Iterator<Tile> i=openSet.iterator();
                 current=i.next();
                while(i.hasNext()){
                    Tile flag=i.next();
                    if(flag.getfCost()<current.getfCost()){
                        current=flag;
                    }
                }
                if(current==end){
                  //  System.out.println("we re DOOONNNEE");
                    backTrack(current);
                }
                closedSet.add(current);
                openSet.remove(current);
                map.getTile(current.getXposMap(), current.getYposMap()).setPlayer(null);
                for(int j =0;j<current.neighbors.size();j++) {
                    Tile neighbor = current.neighbors.get(j);
                    if (!closedSet.contains(neighbor)) {
                        int tempGcost=(current.getgCost() + 1);
                        //i need to figure out if it already exist it within the open list and if yes updated to optimize as much as we possibly can
                        if(openSet.contains(neighbor)){
                            if(tempGcost<neighbor.getgCost()){
                                //time for updating it s value
                                neighbor.setgCost(tempGcost);
                            }
                            else{
                                neighbor.setgCost(tempGcost);
                                openSet.add(neighbor);
                            }
                            // time for the fCost
                            neighbor.sethCost(heuristic(neighbor,end,map));//this is what makes Astar a better version than dijkistra
                            neighbor.setfCost(neighbor.getgCost()+neighbor.gethCost());
                            neighbor.previous=current;
                            //backTrack();
                        }
                    }
                }
            }
    }
    public void update(float f,Tile start,Tile end,Map map) {
        pathFinding(start, end, map);
        System.out.println(totalPath.size());
        for (int i = 0; i < this.totalPath.size(); i++) {
            map.getTile(this.totalPath.get(i).getXposMap(), this.totalPath.get(i).getYposMap()).setEnemy(this);
            System.out.println("hal3ar2");
        }
    }
    public float getxWorld(){
        return this.xWorld;
    }
    public float getyWorld(){
        return this.yWorld;
    }

    public void move(PlayerCoor coordinates) {
        if (map.getTile(x + coordinates.getDx(), y + coordinates.getDy()).getEnemy() == null){
            animationInitMove(x,y,coordinates);
            // erase the player from the position he was in and move him
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
