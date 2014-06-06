package cn.edu.ustc.ase.shape;

import java.io.Serializable;

/**
 * 形状抽象类
 * 
 * @author Yuedong Li
 * 
 */
public abstract class Shape implements Serializable{
	private static final long serialVersionUID = 7418042969885868230L;
	private boolean isSelected = false; // 用于判断连线的一端是否在该图形上

	/**
	 * 鼠标是否处于图形区域内
	 * 
	 * @param point
	 *            鼠标坐标
	 * @return 是否在区域内
	 */
	public abstract boolean isMouseIn(Point point);

	/**
	 * 得到中心点
	 * 
	 * @return
	 */
	public abstract Point getCenterPoint();

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
