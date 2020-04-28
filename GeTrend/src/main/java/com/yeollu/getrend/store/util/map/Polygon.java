package com.yeollu.getrend.store.util.map;

import java.util.ArrayList;

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

        for (int cur = 0, prev = size - 1; cur < size; cur++) {
            Point curPoint = pointList.get(cur);
            Point prevPoint = pointList.get(prev);

            if (curPoint.getY() < y && prevPoint.getY() >= y || prevPoint.getY() < y && curPoint.getY() >= y) {
                if (curPoint.getX() + (y - curPoint.getY()) / (prevPoint.getY() - curPoint.getY()) * (prevPoint.getX() - curPoint.getX()) < x) {
                    flag = !flag;
                }
            }
            prev = cur;
        }
        return flag;
    }
}