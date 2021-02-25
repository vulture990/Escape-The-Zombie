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
                Random r = new Random();
                int rn = r.nextInt(10) + 1;// generating a random number between 1 and 10
                if (rn > 1) {
                    tiles[x][y] = new Tile(Ground.GRASS_1);
                    //next[x][y]=new Tile(Ground.GRASS_1);

                } else {
                    tiles[x][y] = new Tile(Ground.GRASS_2);
                    //next[x][y]=new Tile(Ground.GRASS_2);
                }
            }
        }
        //Now time come for conway game of life
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int check = 0;
                if (tiles[x][y].getGround() == Ground.GRASS_1) {
                    //meaning the cell is dead

                    if (tiles[x][y - 1].getGround() == Ground.GRASS_1) {
                        check++;
                        if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                            tiles[x][y].setGround(Ground.GRASS_1);
                        }
                    } else if (tiles[x][y + 1].getGround() == Ground.GRASS_1) {
                        check++;
                        if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                            tiles[x][y].setGround(Ground.GRASS_1);
                        }
                    } else if (tiles[x][y].getGround() == Ground.GRASS_1) {
                        continue;
                    } else if (tiles[x - 1][y - 1].getGround() == Ground.GRASS_1) {
                        check++;
                        if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                            tiles[x][y].setGround(Ground.GRASS_1);
                        }
                    } else if (tiles[x + 1][y - 1].getGround() == Ground.GRASS_1) {
                        check++;
                        if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                            tiles[x][y].setGround(Ground.GRASS_1);
                        }
                    } else if (tiles[x + 1][y + 1].getGround() == Ground.GRASS_1) {
                        check++;
                        if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                            tiles[x][y].setGround(Ground.GRASS_1);
                        }
                    } else if (tiles[x + 1][y].getGround() == Ground.GRASS_1) {
                        check++;
                        if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                            tiles[x][y].setGround(Ground.GRASS_1);
                        }
                    } else if (tiles[x - 1][y + 1].getGround() == Ground.GRASS_1) {
                        check++;
                        if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                            tiles[x][y].setGround(Ground.GRASS_1);
                        }
                    } else if (tiles[x - 1][y - 1].getGround() == Ground.GRASS_1) {
                        check++;
                        if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                            tiles[x][y].setGround(Ground.GRASS_1);
                        }
                        else continue;
                        if(check==3){
                            tiles[x][y].setGround(Ground.GRASS_2);
                        }
                    } else {//meaning the cell is alive
                        int check2 = 0;
                        if (tiles[x][y].getGround() == Ground.GRASS_1) {
                            //meaning the cell is dead

                            if (tiles[x][y - 1].getGround() == Ground.GRASS_1) {
                                check2++;
                                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                                    tiles[x][y].setGround(Ground.GRASS_1);
                                }
                            } else if (tiles[x][y + 1].getGround() == Ground.GRASS_1) {
                                check2++;
                                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                                    tiles[x][y].setGround(Ground.GRASS_1);
                                }
                            } else if (tiles[x][y].getGround() == Ground.GRASS_1) {
                                continue;
                            } else if (tiles[x - 1][y - 1].getGround() == Ground.GRASS_1) {
                                check2++;
                                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                                    tiles[x][y].setGround(Ground.GRASS_1);
                                }
                            } else if (tiles[x + 1][y - 1].getGround() == Ground.GRASS_1) {
                                check2++;
                                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                                    tiles[x][y].setGround(Ground.GRASS_1);
                                }
                            } else if (tiles[x + 1][y + 1].getGround() == Ground.GRASS_1) {
                                check2++;
                                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                                    tiles[x][y].setGround(Ground.GRASS_1);
                                }
                            } else if (tiles[x + 1][y].getGround() == Ground.GRASS_1) {
                                check2++;
                                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                                    tiles[x][y].setGround(Ground.GRASS_1);
                                }
                            } else if (tiles[x - 1][y + 1].getGround() == Ground.GRASS_1) {
                                check2++;
                                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                                    tiles[x][y].setGround(Ground.GRASS_1);
                                }
                            } else if (tiles[x - 1][y - 1].getGround() == Ground.GRASS_1) {
                                check2++;
                                if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                                    tiles[x][y].setGround(Ground.GRASS_1);
                                }
                                else continue;
                                if(check2 <2 || check>3){
                                    tiles[x][y].setGround(Ground.GRASS_1);
                                }
                            }
                        }
                    }
                }
            }
        }
        tiles=next;
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
    
