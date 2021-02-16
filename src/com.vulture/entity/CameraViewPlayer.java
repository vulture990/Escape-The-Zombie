package com.vulture.entity;

public class CameraViewPlayer<x> {
    public double xScroll=0;
    private double  yScroll=0;
    public void cameraUpdate(double newXpos,double newYpos){
        this.xScroll=newXpos;
        this.yScroll=newYpos;
    }
    public double getxScroll(){
        return this.xScroll;
    }
    public double getyScroll(){
        return this.yScroll;
    }

}
