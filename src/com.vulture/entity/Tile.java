package com.vulture.entity;


public class Tile  {
    private Ground gr;
    private Player player;
    public Tile(Ground gr){
        this.gr = gr;
    }
    public  Ground getGround(){
        return gr;
    }
    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player player){
        this.player= player;
    }

}

