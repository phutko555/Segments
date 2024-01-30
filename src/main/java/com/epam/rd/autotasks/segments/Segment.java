package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
     
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException("Degenerate segment: Start and end points are the same.");
        }
        
        this.start = start;
        this.end = end;
    }

    double length() {
   
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    Point middle() {
      
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    Point intersection(Segment another) {
       
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();

        double x3 = another.start.getX();
        double y3 = another.start.getY();
        double x4 = another.end.getX();
        double y4 = another.end.getY();

        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (denominator == 0) {
            return null;
        }

        double intersectionX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double intersectionY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        if (isPointOnSegment(intersectionX, intersectionY) && another.isPointOnSegment(intersectionX, intersectionY)) {
            return new Point(intersectionX, intersectionY);
        } else {
            return null;
        }
    }
    private boolean isPointOnSegment(double x, double y) {
        return (x >= Math.min(start.getX(), end.getX()) && x <= Math.max(start.getX(), end.getX()) &&
                y >= Math.min(start.getY(), end.getY()) && y <= Math.max(start.getY(), end.getY()));
    }
}

public class Main {
    public static void main(String[] args) {
        double length = new Segment(new Point(0, 0), new Point(3, 4)).length();
        System.out.println(length); 

        Segment first = new Segment(new Point(0, 0), new Point(4, 4));
        Segment second = new Segment(new Point(2, 0), new Point(0, 2));
        Point intersection = first.intersection(second);

        System.out.println(intersection.getX()); 
        System.out.println(intersection.getY()); 

        Segment segment = new Segment(new Point(2, 0), new Point(0, 2));
        Point midpoint = segment.middle();

        System.out.println(midpoint.getX()); 
        System.out.println(midpoint.getY()); 

        Segment third = new Segment(new Point(0, 0), new Point(4, 0));
        Segment fourth = new Segment(new Point(2, 1), new Point(1, 2));
        Point noIntersection = third.intersection(fourth);

        System.out.println(noIntersection == null); 
    }
}


