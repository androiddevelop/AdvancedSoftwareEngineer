package cn.edu.ustc.ase.shape;

/**
 * 矩形
 * 
 * @author Yuedong Li
 * 
 */
public class Rectangle extends IShape {
	private static final long serialVersionUID = -7214709097279042096L;
	private Point startPoint; // 起点
	private Point endPoint = new Point(); // 终点

	@Override
	public boolean isMouseIn(Point point) {
		if (point.x > startPoint.x && point.x > endPoint.x || point.y > startPoint.y
				&& point.y > endPoint.y || point.x < startPoint.x && point.x < endPoint.x
				|| point.y < startPoint.y && point.y < endPoint.y)
			return false;
		return true;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
		endPoint.x = startPoint.x;
		endPoint.y = startPoint.y;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public Point getCenterPoint() {
		int x = (endPoint.x + startPoint.x) / 2;
		int y = (endPoint.y + startPoint.y) / 2;
		return new Point(x, y);
	}
}