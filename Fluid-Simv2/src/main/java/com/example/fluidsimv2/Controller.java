package com.example.fluidsimv2;

import com.example.fluidsimv2.math.Colours;
import com.example.fluidsimv2.math.Particle;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    public Scene scene;
//    public int Xrightwall, Xleftwall, Yfloor;
    int numInstances = 0;

    public Particle[] particles = new Particle[50];
    public Particle[] runningparticles = new Particle[particles.length];
    public int particlecount = 0;
    public int timercounter = 0;
    public float time;
    public int randomvel = 10;

    private Timer timer = new Timer();
    public Controller(Scene scene){
        this.scene = scene;

        for (int i=0; i<particles.length; i++){runningparticles[i] = new Particle(0,0,0,0,0, Color.BLACK);}
        //TIMER STUFF:
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //what you want to do
                Platform.runLater(()->{
                    //CALL TICK AND RENDER IN HERE:
                    if (!(particlecount == runningparticles.length) && timercounter==6){
                        runningparticles[particlecount] = new Particle(250, 250, randomvel,0,10, Colours.gencolors(time));
                        time += 0.33;
                        ((Pane) scene.getRoot()).getChildren().add(runningparticles[particlecount].circle);
                        particlecount += 1;
                        timercounter = 0;
                    }
                    collision(runningparticles);
                    for (int i=0; i<runningparticles.length; i++){
                        runningparticles[i].move();
                        runningparticles[i].updategrap();
                    }
                    timercounter += 1;
                });
            }
        }, 0, 33);//wait 33 ms for 30fps
    }

    public void collision(Particle[] balls){
        double d,drx,dry,dr,offset,dx,dy,xiNew,yiNew,xjNew,yjNew,dvx,dvy,fac,delvx,delvy;
        for (int i=0; i<particlecount; i++){
            for (int j = i+1; j < particlecount; j++){
                System.out.println(particlecount);
                d = balls[i].getR() + balls[j].getR();
                drx = balls[i].getX() - balls[j].getX();
                dry = balls[i].getY() - balls[j].getY();
                dr = Math.hypot(drx,dry);
                if (dr < d){
                    offset = (d - dr)/2.0;
                    dx = offset*drx/dr;
                    dy = offset*dry/dr;
                    xiNew = balls[i].getX() + dx;
                    yiNew = balls[i].getY() + dy;
                    xjNew = balls[j].getX() - dx;
                    yjNew = balls[j].getY() - dy;
                    drx = xiNew - xjNew;
                    dry = yiNew - yjNew;
                    dvx = balls[i].getVx() - balls[j].getVx();
                    dvy = balls[i].getVy() - balls[j].getVy();
                    fac = (dvx*drx + dvy*dry)/(d*d);
                    delvx = fac*drx;
                    delvy = fac*dry;
                    balls[i].setVx(delvx,true);
                    balls[i].setVy(delvy, true);
                    balls[j].setVx(delvx, false);
                    balls[j].setVy(delvy, false);
                    balls[i].setX(xiNew);
                    balls[i].setY(yiNew);
                    balls[j].setX(xjNew);
                    balls[j].setY(yjNew);
                }
            }
        }
    }



}
