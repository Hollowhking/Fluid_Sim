package com.example.fluidsim;

import com.example.fluidsim.Math.Colours;
import com.example.fluidsim.Math.Particle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

//    public Timer timer;
    public Scene scene;
    public Rectangle wallL = new Rectangle();
    public Rectangle wallR = new Rectangle();
    public Rectangle floor = new Rectangle();

    public int radius;
    public Particle[] particles = new Particle[10];
    public Particle[] runningparticles = new Particle[particles.length];
    public int particlecount = 0;
    public int timercounter = 0;
    public float time;
    //private Solver solver;
    private Timer timer = new Timer();
    public Controller(Scene scene){
        this.scene = scene;
        //Place LEFT WALL
        wallL.setX(50);
        wallL.setY(scene.getHeight()/2);
        wallL.setHeight((scene.getHeight()/2)-10);
        wallL.setWidth(10);
        wallL.setFill(Color.RED);
        ((Pane)scene.getRoot()).getChildren().add(wallL);

        //PLACE RIGHT WALL:
        wallR.setX(scene.getWidth()-50);
        wallR.setY(scene.getHeight()/2);
        wallR.setHeight((scene.getHeight()/2)-10);
        wallR.setWidth(10);
        wallR.setFill(Color.GREEN);
        ((Pane)scene.getRoot()).getChildren().add(wallR);

        //PLACE FLOOR:
        floor.setX(50);
        floor.setY(scene.getHeight()-10);
        floor.setHeight(10);
        floor.setWidth(scene.getWidth()-90);
        floor.setFill(Color.BLUE);
        ((Pane)scene.getRoot()).getChildren().add(floor);


        //ADD PARTICLES:
//        for (int i=0; i<particles.length; i++) {
//            particles[i] = new Particle(250, 250-(i*50), 20, Colours.gencolors(time), this);
//            ((Pane) scene.getRoot()).getChildren().add(particles[i].particle);
//            time += 0.33;
//        }
        for (int i=0; i<particles.length; i++){
            runningparticles[i] = new Particle(0,0,0,Color.BLACK,this);

        }

        //TIMER STUFF:
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //what you want to do
                Platform.runLater(()->{
                    //CALL TICK AND RENDER IN HERE:
                    //Add ball
                    if (!(particlecount == runningparticles.length) && timercounter==6){
                        runningparticles[particlecount] = new Particle(250, 250, 20, Colours.gencolors(time), Controller.this);
                        time += 0.33;
                        ((Pane) scene.getRoot()).getChildren().add(runningparticles[particlecount].particle);
                        particlecount += 1;
                        timercounter = 0;
                    }

                    for (int i=0; i<runningparticles.length; i++){
                        runningparticles[i].tick();
                        for (int j=0; j<runningparticles.length; j++){
                            if (i == j)continue;
                            if (intersectparticles(runningparticles[i].particle,runningparticles[j].particle)){
                                System.out.println("BONK");
                                runningparticles[i].accX = runningparticles[i].accX * -1;
                                runningparticles[i].accY = runningparticles[i].accY * -1;
                            }
                        }
                    }
                    timercounter += 1;

                });
            }
        }, 0, 33);//wait 33 ms for 30fps

    }

    boolean intersectparticles(Circle circle, Circle circle2){
        if (circle.intersects(circle2.getLayoutBounds())){
            return true;
        }else{return false;}
//        double d = Math.sqrt((circle.getCenterX() - circle2.getCenterX()) * (circle.getCenterX() - circle2.getCenterX())
//                + (circle.getCenterY() - circle2.getCenterY()) * (circle.getCenterY() - circle2.getCenterY()));
//        if (circle2.getRadius() >= circle.getRadius() && d <= (circle2.getRadius() - circle.getRadius())){
//            //System.out.println("Circle 1 is inside Circle 2.");
//            return true;
//        }
//        else if (circle.getRadius() >= circle2.getRadius() && d <= (circle.getRadius() - circle2.getRadius()) ) {
//            //System.out.println("Circle 2 is inside Circle 1.");
//            return true;
//        }
//        else if (d > (circle.getRadius() + circle2.getRadius())){
//            //System.out.println("Circle 2 does not overlap Circle 1.");
//            return false;
//        }
//        else {
//            //System.out.println("Circle 2 overlaps Circle 1.");
//            return true;
//        }

    }

}
