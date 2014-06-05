package cn.edu.ustc.ase.state;

import cn.edu.ustc.ase.factory.ShapeFactory;
import cn.edu.ustc.ase.gui.PaintPane;
import cn.edu.ustc.ase.shape.Circle;
import cn.edu.ustc.ase.shape.Point;

/**
 * 圆形状态
 * 
 * @author Yuedong Li
 * 
 */
public class CircleState implements IShapeState {

	@Override
	public void modifyData(MouseState state, Point point) {
		if (state == MouseState.PRESS) {
			Circle circle = (Circle) ShapeFactory.getInstance().generateShape(
					"circle");
			circle.setStartPoint(point);
			PaintPane.circles.add(circle);
			PaintPane.history.add(circle);
		} else if (state == MouseState.DRAG || state == MouseState.RELEASE) {
			Circle circle = PaintPane.circles
					.get(PaintPane.circles.size() - 1);
			circle.setEndPoint(point);
		}
	}
}