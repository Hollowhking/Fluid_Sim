package com.example.fluidsim.Math;

import com.example.fluidsim.Controller;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.abs;

public class Particle {
    public int X,Y;
    public int radius;
    public Color color;
    public Circle particle;
    int grav = 2;
    public Controller controller;
    public int accY,accX;
    int mass = 1;

    public Particle(int X, int Y, int radius, Color color, Controller controller){
        //System.out.println("particlemade");
        this.X = X;
        this.Y = Y;
        this.radius = radius;
        this.color = color;
        this.controller = controller;
        this.accY = 0;
        this.accX = 20;

        this.particle = new Circle();
        this.particle.setCenterX(this.X);
        this.particle.setCenterY(this.Y);
        this.particle.setFill(this.color);
        this.particle.setRadius(this.radius);
    }
    public void tick(){
        //collision with container Y:
        if (intersects(particle,controller.floor)){
            //System.out.println(this.acc);
            this.accY = this.accY/2;
//            this.accY = this.accY -1;
            this.accY = this.accY * -1;
        }else{
            this.accY += this.mass * this.grav;
        }

        //X collisions:
        if (intersects(particle,controller.wallR)){
            //System.out.println("A");
            //this.accX = (int) (this.accX*0.99);
            this.accX = this.accX * -1;
        }
        if (intersects(particle,controller.wallL)){
            //System.out.println("B");
            //this.accX = (int) (this.accX*0.99);
            this.accX = this.accX * -1;
        }

        //Collision with other particles:
//        for (Particle i : controller.particles){
//            if (!(i.color == this.color)){
//                if (intersectparticles(this.particle,i.particle)){
//                    //System.out.println("BONK");
//                    this.accX = this.accX * -1;
//                    this.accY = this.accY * -1;
//                }
//            }
//        }

        //
        if (this.accY == 1){
            this.accY = 0;
        }

        //updadte statements:
        this.Y += this.accY;
        this.X += this.accX;
        this.particle.setCenterX(this.X);
        this.particle.setCenterY(this.Y);
        this.particle.setFill(this.color);
    }


    boolean intersects(Circle circle, Rectangle rect) {
        if (circle.intersects(rect.getLayoutBounds())){
            return true;
        }
//        if (circle.getCenterX()+circle.getRadius()+accX > rect.getX()
//                && circle.getCenterX() - circle.getRadius() < rect.getX() + rect.getWidth()
//                    && circle.getCenterY() + circle.getRadius()+accY > rect.getY()
//                        && circle.getCenterY() - circle.getRadius() < rect.getY() + rect.getHeight()){
//            return true;
        //}
        else{
            return false;
        }
    }
    //Getters/Setters
    public int getX(){
        return this.X;
    }
    public int getY(){
        return this.Y;
    }
    public Color getColor(){
        return this.color;
    }

    public void setX(int x){
        this.X = x;
    }
    public void setY(int y){
        this.Y = y;
    }
    public void setColor(Color col){
        this.color = col;
    }
}
