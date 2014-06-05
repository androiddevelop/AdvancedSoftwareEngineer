package cn.edu.ustc.ase.state;

import cn.edu.ustc.ase.factory.ShapeFactory;
import cn.edu.ustc.ase.gui.PaintPane;
import cn.edu.ustc.ase.shape.Point;
import cn.edu.ustc.ase.shape.Rectangle;

/**
 * 矩形状态
 * 
 * @author Yuedong Li
 * 
 */
public class RectangleState implements IShapeState {

	@Override
	public void modifyData(MouseState state, Point point) {
		if (state == MouseState.PRESS) {
			Rectangle rectangle =(Rectangle) ShapeFactory.getInstance().generateShape(
					"rectangle");
			rectangle.setStartPoint(point);
			PaintPane.rectangles.add(rectangle);
			PaintPane.history.add(rectangle);
		} else if (state == MouseState.DRAG || state == MouseState.RELEASE) {
			Rectangle rectangle = PaintPane.rectangles
					.get(PaintPane.rectangles.size() - 1);
			rectangle.setEndPoint(point);
		}
	}
}