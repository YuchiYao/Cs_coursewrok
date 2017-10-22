import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class NBody {
    static double readRadius(String file){
        In in;
        in = new In(file);
        int N= in.readInt(); // The number of planets
        double radius =in.readDouble();
        in.close();          // Close the file in the end
        return radius;
        }
    
    static Planet[] readPlanets(String file){
        In input;
        input = new In(file);
        int N= input.readInt();
        Planet[] planets = new Planet[N];
        double radius =input.readDouble();
        for (int i=0;i<N;i++){
            planets[i] = new Planet(input.readDouble(),input.readDouble(),input.readDouble(),input.readDouble(),input.readDouble(),input.readString());// Get x,y coordinate, then speed of x and y. Finnally, mass and string
        }
        input.close();
        return planets;
    }
    public static void main(String[] args) {
        //rad data from file
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius = NBody.readRadius(filename);
        double time =0;
        Planet[] planets =NBody.readPlanets(filename);
        //Set the sky
        String imageToDraw = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, imageToDraw);
        // Draw all the planets
        for (int i =0;i <planets.length;i++)
        {
            planets[i].draw();
        }
        StdDraw.show(10);
        StdAudio.play("audio/2001.mid");
        //Start the animation
        while (time<=T){
        
            double [] xForces = new double[planets.length];
            double [] yForces = new double[planets.length];
        //Calculate the force
        for (int i=0;i<planets.length;i++){
            xForces[i] = planets[i].calcNetForceExertedByX(planets);
            yForces[i] = planets[i].calcNetForceExertedByY(planets);
                }
            //updata the position, speed...
            for (int i=0;i<planets.length;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
                }
            //Draw the new layout
            StdDraw.picture(0, 0, imageToDraw);
            // Draw all the planets
            for (int i =0;i <planets.length;i++){
                    planets[i].draw();
                }
            
            StdDraw.show(10);
            time+=dt;
        
            }
        //
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
            }
        }
    }

