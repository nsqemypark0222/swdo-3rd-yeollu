package com.yeollu.getrend.map.core;

import java.util.ArrayList;

import com.yeollu.getrend.map.vo.Point;

public class Polygon {
    private ArrayList<Point> pointList = null;

    public Polygon() {
        pointList = new ArrayList<Point>();
    }

    public void addPoint(Point point) {
        pointList.add(point);
    }

    public void reset() {
        pointList.clear();
    }

    public boolean isContains(double x, double y) {
        int size = pointList.size();
        if (size < 3) {
            return false;
        }
        boolean flag = false;

        for (int i = 0, j = size - 1; i < size; i++) {
            Point iPoint = pointList.get(i);
            Point jPoint = pointList.get(j);

            if (iPoint.getY() < y && jPoint.getY() >= y || jPoint.getY() < y && iPoint.getY() >= y) {
                if (iPoint.getX() + (y - iPoint.getY()) / (jPoint.getY() - iPoint.getY()) * (jPoint.getX() - iPoint.getX()) < x) {
                    flag = !flag;
                }
            }
            j = i;
        }
        return flag;
    }
}