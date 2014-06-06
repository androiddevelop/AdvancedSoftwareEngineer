package cn.edu.ustc.ase.shape;

/**
 * 连线
 * 
 * @author Yuedong Li
 * 
 */
public class Line extends Shape {
	private static final long serialVersionUID = -855456283678986529L;
	private Shape shape1; // 连线2端的图形
	private Shape shape2; // 连线2端的图形
	private Point tmpPoint = new Point(); // 选中一个图形后,在拖拽中的临时点

	/**
	 * 连线不能拖动
	 */
	@Override
	public boolean isMouseIn(Point point) {
		return false;
	}

	public Shape getShape1() {
		return shape1;
	}

	public void setShape1(Shape shape1) {
		this.shape1 = shape1;
		Point point = shape1.getCenterPoint();
		tmpPoint.x = point.x;
		tmpPoint.y = point.y;
	}

	public Shape getShape2() {
		return shape2;
	}

	public void setShape2(Shape shape2) {
		this.shape2 = shape2;
	}

	public Point getTmpPoint() {
		return tmpPoint;
	}

	public void setTmpPoint(Point tmpPoint) {
		this.tmpPoint = tmpPoint;
	}

	@Override
	public Point getCenterPoint() {
		Point point1 = shape1.getCenterPoint();
		Point point2 = shape2.getCenterPoint();
		int x = (point1.x + point2.x) / 2;
		int y = (point1.y + point2.y) / 2;
		return new Point(x, y);
	}
}