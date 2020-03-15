package com.yeollu.getrend.map.vo;

public class PointVO {
	private double xPos;
	private double yPos;
	
	public PointVO() {
		
	}
	
	public PointVO(double xPos, double yPos) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	
	@Override
	public String toString() {
		return "위도 : " + getxPos() + ", 경도 : " + getyPos();
	}
}
