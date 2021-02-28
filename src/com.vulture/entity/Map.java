package com.vulture.entity;

import java.util.Random;

// we gonna process the classic way 2d array
public class Map {
    private int w, h;
    private Tile[][] tiles;
    private Tile[][] next;

    public Map(int w, int h) {
        this.w = w;
        this.h = h;
        tiles = new Tile[w][h];
        next = new Tile[w][h];
        //init for each tile object
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                tiles[x][y] = new Tile(Ground.GRASS_1);
                next[x][y] = new Tile(Ground.GRASS_1);
                /*
                Random r = new Random();
                int rn = r.nextInt(10) + 1;// generating a random number between 1 and 10
                if (rn > 1) {
                    tiles[x][y] = new Tile(Ground.GRASS_1);
                    //next[x][y]=new Tile(Ground.GRASS_1);

                } else {
                    tiles[x][y] = new Tile(Ground.GRASS_2);
                    //next[x][y]=new Tile(Ground.GRASS_2);
                }

                 */

           }
        }
       // tiles[5][1].setGround(Ground.GRASS_2);
    }
    public void updateMap(float delta){
        //Now time come for conway game of life
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                    this.next[x][y].setGround(Ground.GRASS_1);
                } else {
                    int check = 0;
                    if (this.tiles[x][y].getGround() == Ground.GRASS_2) {
                        //meaning the cell has grass2 meanin it s dead cell
                        // checking it s neighboors
                        if (this.tiles[x][y - 1].getGround() == Ground.GRASS_1) {
                            check++;

                        } else if (this.tiles[x][y + 1].getGround() == Ground.GRASS_1) {
                            check++;

                        } else if (this.tiles[x][y].getGround() == Ground.GRASS_1) {
                            continue;
                        } else if (this.tiles[x - 1][y - 1].getGround() == Ground.GRASS_1) {
                            check++;

                        } else if (this.tiles[x + 1][y - 1].getGround() == Ground.GRASS_1) {
                            check++;

                        } else if (this.tiles[x + 1][y + 1].getGround() == Ground.GRASS_1) {
                            check++;

                        } else if (this.tiles[x + 1][y].getGround() == Ground.GRASS_1) {
                            check++;

                        } else if (this.tiles[x - 1][y + 1].getGround() == Ground.GRASS_1) {
                            check++;

                        } else if (this.tiles[x - 1][y - 1].getGround() == Ground.GRASS_1) {
                            check++;
                        }

                        if (check == 3) {
                            this.next[x][y].setGround(Ground.GRASS_1);
                            //if 3 of its neighboors  are alive the next generation that cell is going to turn into
                        }
                    } else {//meaning the cell is alive
                        int check2 = 0;
                        if (this.tiles[x][y].getGround() == Ground.GRASS_1) {
                            //meaning the cell is dead

                            if (this.tiles[x][y - 1].getGround() == Ground.GRASS_1) {
                                check2++;

                            } else if (this.tiles[x][y + 1].getGround() == Ground.GRASS_1) {
                                check2++;

                            } else if (this.tiles[x][y].getGround() == Ground.GRASS_1) {
                                continue;
                            } else if (this.tiles[x - 1][y - 1].getGround() == Ground.GRASS_1) {
                                check2++;

                            } else if (this.tiles[x + 1][y - 1].getGround() == Ground.GRASS_1) {
                                check2++;

                            } else if (this.tiles[x + 1][y + 1].getGround() == Ground.GRASS_1) {
                                check2++;

                            } else if (this.tiles[x + 1][y].getGround() == Ground.GRASS_1) {
                                check2++;

                            } else if (this.tiles[x - 1][y + 1].getGround() == Ground.GRASS_1) {
                                check2++;

                            } else if (this.tiles[x - 1][y - 1].getGround() == Ground.GRASS_1) {
                                check2++;
                            }
                            if (check2 < 2 || check2 > 3) {
                                this.next[x][y].setGround(Ground.GRASS_2);
                            } else {
                                this.next[x][y] = this.tiles[x][y];

                            }
                        }
                    }
                }
            }
        }
        this.tiles=this.next;
        //tiles=next;
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

