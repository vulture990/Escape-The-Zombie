package com.vulture.entity;
import java.util.Random;
// we gonna process the classic way 2d array
public class Map {
    private int w, h;
    private Tile[][] tiles;

    public Map(int w, int h) {
        this.w = w;
        this.h = h;
        tiles = new Tile[w][h];
        //init for each tile object
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                //tiles[x][y] = new Tile(Ground.GRASS_1);
                Random r = new Random();
                int rn = r.nextInt(10) + 1;// generating a random number between 1 and 10
                if (rn > 1) {
                        tiles[x][y] = new Tile(Ground.GRASS_1);
                        tiles[x][y].setXposMap(x);
                        tiles[x][y].setYposMap(y);
                } else {
                    tiles[x][y] = new Tile(Ground.GRASS_2);
                    tiles[x][y].setXposMap(x);
                    tiles[x][y].setYposMap(y);
                }
           }
        }
    }
    public void init() {
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                tiles[x][y].addNeighboors(this);
            }
        }
    }


    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }
}

