package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
class Segment {
    private Point start, end;
    public Segment(Point start, Point end) {
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
    }
    double length() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        return sqrt(pow(dx, 2) + pow(dy, 2));
    }
    Point middle() {
        double mx = (start.getX() + end.getX()) / 2;
        double my = (start.getY() + end.getY()) / 2;
        return new Point(mx, my);
    }
    Point intersection(Segment another) {
        double x1 = start.getX(), y1 = start.getY();
        double x2 = end.getX(), y2 = end.getY();
        double x3 = another.start.getX(), y3 = another.start.getY();
        double x4 = another.end.getX(), y4 = another.end.getY();
        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (denominator == 0) {
            return null; 
        }
        double intersectX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double intersectY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;
        Point intersectionPoint = new Point(intersectX, intersectY);
       
        if (isPointOnSegment(intersectionPoint, this) && isPointOnSegment(intersectionPoint, another)) {
            return intersectionPoint;
        } else {
            return null;
        }
    }
    private boolean isPointOnSegment(Point point, Segment segment) {
        double minX = Math.min(segment.start.getX(), segment.end.getX());
        double maxX = Math.max(segment.start.getX(), segment.end.getX());
        double minY = Math.min(segment.start.getY(), segment.end.getY());
        double maxY = Math.max(segment.start.getY(), segment.end.getY());
        return point.getX() >= minX && point.getX() <= maxX && point.getY() >= minY && point.getY() <= maxY;
    }
}

