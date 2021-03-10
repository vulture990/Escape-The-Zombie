package com.vulture.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.vulture.App;
import com.vulture.Assets.Assets;
import com.vulture.InputHandler.Control;
import com.vulture.entity.*;
import org.w3c.dom.Text;

public class Gscreen extends OtherScreenStuff {
    private Control control;
    private CameraViewPlayer camera;
    private SpriteBatch batch;// this is going to reduce so much work
    private Player player;
    private Enemy enemy;
    private Texture texture;// i'm going to use it in order to load and render the sprite
    private Texture texture2;
    private Texture textureGRASS_1;
    private Texture textureGRASS_2;
    private Map map;
    private Assets world;
    private AssetsRendering worldRenderer;
    public static final int TILE=64;
    public static final int SCALE=1;
    public static final int SCALE_TILE=TILE*SCALE;
    public Gscreen(App app) {
        super(app);
        texture = new Texture("rsc/gg.png");
        texture2=new Texture("rsc/enemyPositions/back_0.png");
        textureGRASS_1=new Texture("rsc/groundtiles.png");
        textureGRASS_2=new Texture("rsc/tile.png");
        TextureAtlas atlas=app.getAssetManager().get("rsc/playerTexture/tex.atlas",TextureAtlas.class);
       // TextureAtlas atlases=app.getAssetManager().get("rsc/enemyTexture/texture.atlas",TextureAtlas.class);
        /*
        Animations animations2=new Animations(

                atlases.findRegion("back"),
                atlases.findRegion("front"),
                atlases.findRegion("left"),
                atlases.findRegion("right"),
               new Animation(0.3f/2f,atlases.findRegions("back"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f,atlases.findRegions("front"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f,atlases.findRegions("left"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f,atlases.findRegions("right"), Animation.PlayMode.LOOP_PINGPONG)
                );

         */
        Animations animations=new Animations(
                atlas.findRegion("back"),
                atlas.findRegion("front"),
                atlas.findRegion("left"),
                atlas.findRegion("right"),
                new Animation(0.3f/2f,atlas.findRegions("back"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f,atlas.findRegions("front"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f,atlas.findRegions("left"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f,atlas.findRegions("right"), Animation.PlayMode.LOOP_PINGPONG)
        );
        map=new Map(22,17);
        map.init();
        batch= new SpriteBatch();
        player=new Player(0,0,map,animations);
        enemy=new Enemy(20,15,map,texture2,player);
        control=new Control(player,enemy);
        camera =new CameraViewPlayer();
        world=new Assets(100,100);
       // worldRenderer=new AssetsRendering(getApp().getAssetManager,world);
    }
    public void dispose(){
        this.dispose();
    }
    public void hide(){
    }
// each time a frame passes show fires that s why i think it s the best place to get input from
    @Override
    public void show() {
        Gdx.input.setInputProcessor(control);
    }

    @Override
    public void render(float f) {
        control.update(f);
        player.updateWorldCord(f);// this is to update the player each frame
        enemy.update(f, enemy.enemyTile, player.playerTile,map);
        world.update(f);
        //camera.cameraUpdate(player.getX()+0.5f,player.getY()+0.5f);//for it to be centred around the player OR THE CAMERA
        //after testing it i figured that if we centered the camera on the world cordinates it looks way cooler than the player
        camera.cameraUpdate(player.getxWorld()+0.5f,player.getyWorld()+0.5f);
        batch.begin();
        double worldStartX=Gdx.graphics.getWidth()/2 - camera.getxScroll()*SCALE_TILE;//what this means is that given a cordinates of where the camera we can keep update it constantly for it to follow and be centred around the player
        double worldStartY=Gdx.graphics.getHeight()/2 - camera.getyScroll()*SCALE_TILE;//what this means is that given a cordinates of where the camera we can keep update it constantly for it to follow and be centred around the player

        // i want to render each tile
        for(int x=0;x< map.getW();x++){
            for(int y=0;y<map.getH();y++){
                Texture tex;
                if(map.getTile(x,y).getGround()== Ground.GRASS_1){
                    tex=textureGRASS_1;
                }
                else{
                    tex=textureGRASS_2;
                }
                batch.draw(tex, (float) (worldStartX+x * SCALE_TILE), (float) (worldStartY+y*SCALE_TILE), SCALE_TILE, SCALE_TILE);
            }
        }

        batch.draw(enemy.getTexture(),(float)(worldStartX + enemy.getX() * SCALE_TILE),(float)(worldStartY + SCALE_TILE * enemy.getY()), TILE, TILE);

        batch.draw(player.getSprite(), (float) (worldStartX +player.getxWorld() * SCALE_TILE),(float) ( worldStartY+ SCALE_TILE * player.getyWorld()),TILE* 3 ,TILE *2.5f);
        //batch.draw(enemy.getSprite(), (float) (worldStartX +enemy.getxWorld()+20 * SCALE_TILE),(float) ( worldStartY+ SCALE_TILE * enemy.getyWorld()+255*4),TILE* 3 ,TILE *2.5f);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
