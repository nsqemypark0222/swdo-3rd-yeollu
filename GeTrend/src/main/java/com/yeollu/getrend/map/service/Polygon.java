package com.yeollu.getrend.map.service;

import java.util.ArrayList;

import com.yeollu.getrend.map.vo.PointVO;

public class Polygon {
	private ArrayList<PointVO> mVertexs = null;
	
	public Polygon() {
		mVertexs = new ArrayList<PointVO>();
	}
	
	public void addPoint(double xPos, double yPos) {
		PointVO point = new PointVO();
		point.setxPos(xPos);
		point.setyPos(yPos);
		mVertexs.add(point);
	}
	
	public void clear() {
		mVertexs.clear();
	}
	
	public boolean contains(double xPos, double yPos) {
		int sizeOfVertexs = mVertexs.size();
		if(sizeOfVertexs < 3) {
			return false;
		}
		int followIndex = sizeOfVertexs - 1;
		boolean isOddNodes = false;
		
		for(int frontIndex = 0; frontIndex < sizeOfVertexs; frontIndex++) {
			PointVO frontPoint = mVertexs.get(frontIndex);
			PointVO followPoint = mVertexs.get(followIndex);
			
			if(frontPoint.getyPos() < yPos 
					&& followPoint.getyPos() >= yPos
					|| followPoint.getyPos() < yPos
					&& frontPoint.getyPos() >= yPos) {
				if(frontPoint.getxPos() + (yPos - frontPoint.getyPos()) / (followPoint.getyPos() - frontPoint.getyPos()) * (followPoint.getxPos() - frontPoint.getxPos()) < xPos) {
					isOddNodes = !isOddNodes;
				}
			}
			followIndex = frontIndex;
		}
		return isOddNodes;
	}
}
