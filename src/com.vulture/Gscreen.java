package com.vulture.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vulture.App;
import com.vulture.InputHandler.Control;
import com.vulture.entity.Ground;
import com.vulture.entity.Map;
import com.vulture.entity.Player;

public class Gscreen extends OtherScreenStuff {

    private Control control;
    private SpriteBatch batch;// this is going to reduce so much work
    private Player player;
    private Texture texture;// i'm going to use it in order to load and render the sprite
    private Texture textureGRASS_1;
    private Texture textureGRASS_2;
    private Map map;
    public static final int TILE=64;
    public static final int SCALE=2;
    public static final int SCALE_TILE=TILE*SCALE;
    public Gscreen(App app) {
        super(app);
        texture = new Texture("rsc/gg.png");
        textureGRASS_1=new Texture("rsc/grass.png");
        textureGRASS_2=new Texture("rsc/grass2.png");
        map=new Map(20,20);
        batch= new SpriteBatch();
        player=new Player(0,0,map);
        control=new Control(player);

    }
    public void dispose(){

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
        batch.begin();
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
                batch.draw(tex,x*SCALE_TILE,y*SCALE_TILE,SCALE_TILE,SCALE_TILE);
            }
        }

        batch.draw(texture,player.getX()*SCALE_TILE,player.getY()*SCALE_TILE,TILE,TILE*1.5f);
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
