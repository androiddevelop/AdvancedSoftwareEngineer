package cn.edu.ustc.ase.state;

import cn.edu.ustc.ase.shape.Circle;
import cn.edu.ustc.ase.shape.IShape;
import cn.edu.ustc.ase.shape.Point;
import cn.edu.ustc.ase.shape.Rectangle;
import cn.edu.ustc.ase.util.ShapeUtil;

/**
 * 拖拽状态
 * 
 * @author Yuedong Li
 * 
 */
public class DragState implements IShapeState {
	private static boolean isSelectShape; // 是否选中图形
	private static Point startPoint; // 起始点

	@Override
	public void modifyData(MouseState state, Point point) {
		if (state == MouseState.PRESS) {
			IShape shape = ShapeUtil.getSelectShape(point);
			if (shape != null) {
				isSelectShape = true;
				startPoint = point;
			} else {
				isSelectShape = false;
			}
		} else if (state == MouseState.DRAG || state == MouseState.RELEASE) {
			if (isSelectShape) {
				IShape shape = ShapeUtil.getSelectShape(point);
				if (shape != null) {
					if (shape instanceof Circle) { // 修改圆形参数
						Circle circle = (Circle) shape;
						Point center = circle.getCenter();
						circle.setCenterPoint(new Point(center.x + point.x
								- startPoint.x, center.y + point.y
								- startPoint.y));
					} else if (shape instanceof Rectangle) { // 修改矩形参数
						Rectangle rectangle = (Rectangle) shape;
						Point point1 = rectangle.getStartPoint();
						Point point2 = rectangle.getEndPoint();
						point1.setPoint(point1.x + point.x - startPoint.x,
								point1.y + point.y - startPoint.y);
						point2.setPoint(point2.x + point.x - startPoint.x,
								point2.y + point.y - startPoint.y);
					}
					startPoint = point; // 重置起始点
				}
			}
		}
	}
}