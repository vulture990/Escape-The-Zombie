package com.vulture.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vulture.Assets.AssetStuff;
import com.vulture.Assets.Assets;
import com.vulture.entity.CameraViewPlayer;
import com.vulture.entity.Ground;
import com.vulture.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AssetsRendering {
    private final float SCALL=2f;
    private AssetManager assetManager;
    private Assets world;
    private Texture grass_1;
    private Texture grass_2;
    private List<Integer> renderAssets=new ArrayList<Integer>();
    private List<AssetStuff> forRendering=new ArrayList<AssetStuff>();
    public AssetsRendering(AssetManager assetManager,Assets world){
        this.assetManager=assetManager;
        this.world=world;
        grass_1=assetManager.get("rsc/groundtiles.png",Texture.class);
        grass_2=assetManager.get("rsc/groundtiles_1.png",Texture.class);

    }
    public void render(SpriteBatch batch , CameraViewPlayer camera) {
        float worldStartX = Gdx.graphics.getWidth() / 2 - camera.getxScroll() * SCALL
        float worldStartY = Gdx.graphics.getHeight() / 2 - camera.getyScroll() * SCALL
        for (int y = 0; y < world.getMap().getH(); y++) {
            for (int x = 0; x < world.getMap().getW(); x++) {
                Texture render;
                if (world.getMap().getTile(x, y).getGround() == Ground.GRASS_1) {
                    render = grass_1;
                } else {
                    render = grass_2;
                }
                batch.draw(render,
                        worldStartX + x * SCALL,
                        worldStartY + y * SCALL,
                        SCALL, SCALL);
            }
        }
        for(int x=0;x<world.getMap().getW();x++){
            for(int y=0;y<world.getMap().getH();y++){
                if(world.getMap().getTile(x,y).getAssets()!=null){
                    AssetStuff worldAssets=world.getMap().getTile(x,y).getAssets();
                    if(renderAssets.contains(worldAssets.hashCode())){
                        continue;//cuz no big deal just move on
                    }
                    forRendering.add(worldAssets);
                    renderAssets.add(worldAssets.hashCode());
                    if(world.getMap().getTile(x,y).getPlayer()!=null){
                        Player p=world.getMap().getTile(x,y).getPlayer();
                        forRendering.add(p);
                    }
                }
                }
            }
        }
