package cn.edu.ustc.ase.shape;

/**
 * 圆
 * 
 * @author Yuedong Li
 * 
 */
public class Circle extends Shape {
	private static final long serialVersionUID = -6828009523554415690L;
	private Point start; // 画圆的起始点
	private Point center = new Point(); // 中心
	private int radius; // 半径

	@Override
	public boolean isMouseIn(Point point) {
		if ((center.x - point.x) * (center.x - point.x) + (center.y - point.y)
				* (center.y - point.y) <= radius * radius)
			return true;
		return false;
	}

	public Point getCenter() {
		return center;
	}

	/**
	 * 设置鼠标画圆时起始位置
	 * 
	 * @param start
	 */
	public void setStartPoint(Point start) {
		this.start = start;
		center.x = start.x;
		center.y = start.y;
		radius = 0;
	}

	/**
	 * 设置圆心坐标
	 * 
	 * @param center
	 */
	public void setCenterPoint(Point center) {
		this.center = center;
	}

	/**
	 * 设置鼠标画圆时终止位置
	 * 
	 * @param end
	 */
	public void setEndPoint(Point end) {
		center.x = (end.x + start.x) / 2;
		center.y = (end.y + start.y) / 2;
		setRadius((int) (Math.sqrt((end.x - start.x) * (end.x - start.x)
				+ (end.y - start.y) * (end.y - start.y)) / 2));
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public Point getCenterPoint() {
		return center;
	}
}