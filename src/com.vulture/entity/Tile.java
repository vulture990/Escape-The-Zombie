package com.vulture.entity;


//import com.vulture.Assets.AssetStuff;

import com.vulture.Assets.AssetStuff;

public class Tile  {
    private Ground gr;
    private Player player;
    private AssetStuff assets;
  //  private AssetStuff objects;
    public Tile(Ground gr){
        this.gr = gr;
    }
    public  Ground getGround(){
        return gr;
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

    public void setAssets(AssetStuff assets) {
        this.assets = assets;
    }
    public AssetStuff getAssets(){
        return this.assets;
    }
}

