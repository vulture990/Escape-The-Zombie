package com.vulture.entity;


public enum PlayerCordinates {
    Up(0, 1),
    Down(0, -1),
    Left(1, 0),
    Right(1, 0);



    private int dx;
    private int dy;

    private  PlayerCordinates(int dx,int dy){
        this.dx=dx;
        this.dy=dy;
    }
    public void setDx(int dx){
        this.dx=dx;
    }
    public void setDy(int dy){
        this.dy=dy;
    }
    public int getDx(){
        return dx;
    }
    public int getDy(){
        return dy;
    }
}
