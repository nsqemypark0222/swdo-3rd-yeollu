package com.yeollu.getrend.store.util.map;

/**
 * @Class 	: Point.java
 * @Package	: com.yeollu.getrend.store.util.map
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: 위도와 경도 나타내는 VO 역할을 수행한다.
 */
public class Point {
	
    /**
     * Fields
     */
    private double x;
    private double y;

    /**
     * Constructor
     */
    public Point() {

    }

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @Method	: getX
     * @Return	: double
     * @Author	: 박민열
     * @Since	: 2020. 3. 12.
     * @Version	: 1.0
     * @Desc	: Getter
     */
    public double getX() {
        return x;
    }

    /**
     * @Method	: setX
     * @Return	: void
     * @Author	: 박민열
     * @Since	: 2020. 3. 12.
     * @Version	: 1.0
     * @Desc	: Setter
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @Method	: getY
     * @Return	: double
     * @Author	: 박민열
     * @Since	: 2020. 3. 12.
     * @Version	: 1.0
     * @Desc	: Getter
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * @Method	: setY
     * @Return	: void
     * @Author	: 박민열
     * @Since	: 2020. 3. 12.
     * @Version	: 1.0
     * @Desc	: Setter
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
}