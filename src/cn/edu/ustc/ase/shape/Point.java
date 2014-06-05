package cn.edu.ustc.ase.shape;

import java.io.Serializable;

/**
 * 点
 * 
 * @author Yuedong Li
 * 
 */
public class Point implements Serializable{
	private static final long serialVersionUID = -5434724405103085361L;
	public int x;
	public int y;

	public Point() {
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 设置点的坐标
	 * 
	 * @param x
	 * @param y
	 */
	public void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString(){
		return x+" "+y;
	}
}