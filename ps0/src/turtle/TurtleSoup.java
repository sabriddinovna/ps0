/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.io.PipedWriter;
import java.util.List;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;

public class TurtleSoup {
    /**
     * Draw a square.
     *
     * @param turtle     the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
//
//        throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * <p>
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     *
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        if (sides > 2) {
           double angle = (180.0 * (sides - 2)) / sides;
            return angle;
        } else
            return -1;

//         throw new RuntimeException("implement me!");
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * <p>
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     *
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        int sides = 0;
        if (angle > 180 || angle < 0) {
            sides = 0;
        }
        if (angle > 0 || angle < 180) {
            sides = (int) ceil((360 / (180.0-angle)));
        }

        return sides;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * <p>
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     *
     * @param turtle     the turtle context
     * @param sides      number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angle = calculateRegularPolygonAngle(sides);
        for (int i = 0; i < sides; i++) {
            turtle.forward(sideLength);
            turtle.turn(180-angle);


        }

    }



    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * <p>
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360.
     * <p>
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     *
     * @param currentHeading current direction as clockwise from north
     * @param currentX       current location x-coordinate
     * @param currentY       current location y-coordinate
     * @param targetX        target point x-coordinate
     * @param targetY        target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     * must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        double angleToTarget = Math.atan2(targetY - currentY, targetX - currentX);

        // Convert radians to degrees and adjust to navigational angle
        angleToTarget = Math.toDegrees(angleToTarget);
        angleToTarget = (90 - angleToTarget + 360) % 360;

        // Calculate the adjustment needed from the current heading
        double heading = angleToTarget - currentHeading;

        // Normalize the adjustment to ensure it is within 0 to 360 degrees
        heading = (heading + 360) % 360;
           return heading;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * <p>
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     *
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     * otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> adjustments = new ArrayList<>();
        if (xCoords.size()!=yCoords.size() || xCoords.size()<2){
            return adjustments;
        }
        double currentHeading=0;
        int currentX=xCoords.get(0);
        int currentY=yCoords.get(0);
        for(int i=1;i< xCoords.size();i++){
            int nextX=xCoords.get(i);
            int nextY=yCoords.get(i);
            double adj= calculateHeadingToPoint(currentHeading,currentX,currentY,nextX,nextY);
            currentHeading = (currentHeading + adj) % 360;
            adjustments.add(adj);
            currentX=nextX;
            currentY=nextY;
        }
return adjustments;
    }

    /**
     * Draw your personal, custom art.
     * <p>
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     *
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
      int l=100;

        for (int i=1; i<100; i++){

        switch (i%5)
            {   case 1:
                    turtle.color(PenColor.BLUE);
                turtle.forward(l);
                turtle.turn(i+18);
                    break;
                case 2:
                    turtle.color(PenColor.YELLOW);
                    turtle.forward(l);
                    turtle.turn(i+45);
                    break;
                case 3:
                    turtle.color(PenColor.GREEN);
                    turtle.forward(l);
                    turtle.turn(i+36);
                    break;
                case 4:
                    turtle.color(PenColor.BLACK);
                    turtle.forward(l);
                    turtle.turn(i+72);
                    break;
                case 0:
                    turtle.color(PenColor.MAGENTA);
                    turtle.forward(l);
                    turtle.turn(i+15);
                    break;
            }



      }
    }

    /**
     * Main method.
     * <p>
     * This is the method that runs when you run "java TurtleSoup".
     *
     * @param args unused
     */


    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

     //drawSquare(turtle, 40);
      drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}