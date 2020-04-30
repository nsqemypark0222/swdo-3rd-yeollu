package com.yeollu.getrend.store.util.map;

import java.util.ArrayList;

/**
 * @Class 	: Polygon.java
 * @Package	: com.yeollu.getrend.store.util.map
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: 다각형의 역할을 수행한다.
 */
public class Polygon {
	
    /**
     * Fields
     */
    private ArrayList<Point> pointList = null;

    /**
     * Constructor
     */
    public Polygon() {
        pointList = new ArrayList<Point>();
    }

    /**
     * @Method	: addPoint
     * @Return	: void
     * @Author	: 박민열
     * @Since	: 2020. 3. 12.
     * @Version	: 1.0
     * @Desc	: 다각형을 구성하는 Point를 입력받아 저장한다.
     * @param point
     */
    public void addPoint(Point point) {
        pointList.add(point);
    }

    /**
     * @Method	: reset
     * @Return	: void
     * @Author	: 박민열
     * @Since	: 2020. 3. 12.
     * @Version	: 1.0
     * @Desc	: 다각형을 구성하는 Point 리스트를 초기화한다.
     */
    public void reset() {
        pointList.clear();
    }

    /**
     * @Method	: isContains
     * @Return	: boolean
     * @Author	: 박민열
     * @Since	: 2020. 3. 12.
     * @Version	: 1.0
     * @Desc	: 위도와 경도를 입력받아 현재의 다각형 내부에 존재하는지 외부에 존재하는지 여부를 반환한다.
     * @param x
     * @param y
     */
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