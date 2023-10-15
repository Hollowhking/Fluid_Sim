package com.example.fluidsimv2.math;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Particle {

    int numcircles = 0; //num of circles in sim
    double dt = 0.33; //timestep
    double grav = 10;
    double boundsL = 0.0;
    double boundsR = 500.0;
    double boundsU = 500.0;
    double boundsD = 0.0;

    double x, y, vx, vy, r;
    double xC, t, v;

    public Circle circle;

    public Particle(int x,int y,int vx, int vy, int r, Color color){

        numcircles += 1;
        this.x = x;
        this.xC = x/boundsR;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.r = r;
        this.t = 0.0;
        this.v = Math.hypot(vx,vy);
        if (v != 0){
            this.dt = r/(4.0*v);
        }
        this.circle = new Circle();
        this.circle.setCenterX(this.x);
        this.circle.setCenterY(this.y);
        this.circle.setFill(color);
        this.circle.setRadius(this.r);
    }

    public void move(){
        //X
        this.x += vx*dt;
        //Y
        this.y += vy*dt + grav*(dt*dt)/2.0;
        this.vy += (grav+grav)*dt/2.0;

        //Collision with wall:
        this.boundaries();
        //Time:
        this.t += dt;
        //graphics

    }

    public void boundaries() {
        //Y
        if (y < this.boundsD + r) {
            // tD = dt - abs( (bD - y)/vy )
            this.vy *= -1.0;
            // self.y = bD + vy*tD + r
            this.y = this.boundsD + r;
        }
        else if (y > boundsU - r) {
            // tU = dt - abs( (bU - y)/vy )
            this.vy *= -1.0;
            // self.y = bU + vy*tU - r
            this.y = boundsU - r;
        }
        //X
        if (x < this.boundsL + r) {
            // tL = dt - abs( (bL - x)/vx )
            this.vx *= -1.0;
            // self.x = bL + vx*tL + r
            this.x = this.boundsL + r;
        }
        else if(x > this.boundsR - r){
            // tR = dt - abs( (bR - x)/vx )
            this.vx *= -1.0;
            // self.x = bR + vx*tR - r
            this.x = this.boundsR - r;
        }
    }

    public void updategrap(){
        this.circle.setCenterX(this.x);
        this.circle.setCenterY(this.y);
    }
//

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx, boolean add) {
        if (add) {
            this.vx -= vx;
        }else{this.vx += vx;}
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy, boolean add) {
        if (add) {
            this.vy -= vy;
        }else this.vy += vy;
    }

    public double getGrav() {
        return grav;
    }

    public void setGrav(double grav) {
        this.grav = grav;
    }
}
