package cn.edu.ustc.ase.state;

import cn.edu.ustc.ase.factory.ShapeFactory;
import cn.edu.ustc.ase.gui.PaintPane;
import cn.edu.ustc.ase.shape.IShape;
import cn.edu.ustc.ase.shape.Line;
import cn.edu.ustc.ase.shape.Point;
import cn.edu.ustc.ase.util.ShapeUtil;

/**
 * 连线状态
 * 
 * @author Yuedong Li
 * 
 */
public class LineState implements IShapeState {
	private static boolean isSelectFirstShape; // 是否选中第一个图形

	@Override
	public void modifyData(MouseState state, Point point) {
		if (state == MouseState.PRESS) {
			Line line = (Line) ShapeFactory.getInstance().generateShape("line");
			line.setSelected(true);
			IShape shape = ShapeUtil.getSelectShape(point);
			if (shape != null) {
				line.setShape1(shape);
				shape.setSelected(true);
				PaintPane.lines.add(line);
				PaintPane.history.add(line);
				isSelectFirstShape = true;
			} else {
				isSelectFirstShape = false;
			}
		} else if (state == MouseState.DRAG) {
			if (isSelectFirstShape) {
				Line line = PaintPane.lines.get(PaintPane.lines.size() - 1);
				IShape shape = ShapeUtil.getSelectShape(point);
				if (shape != null && shape != line.getShape1()) {
					line.setShape2(shape);
					shape.setSelected(true);
				} else {
					if (line.getShape2() != null) { // 清空高亮显示的图形
						line.getShape2().setSelected(false);
						line.setShape2(null);
					}
					line.setTmpPoint(point);
				}
			}
		} else if (state == MouseState.RELEASE) {
			if (isSelectFirstShape) {
				Line line = PaintPane.lines.get(PaintPane.lines.size() - 1);
				IShape shape = ShapeUtil.getSelectShape(point);
				if (shape != null) {
					line.setShape2(shape);
					shape.setSelected(false); // 取消选中状态
					line.getShape1().setSelected(false); // 取消选中状态
					line.setSelected(false); // 取消选中状态
				} else {
					line.getShape1().setSelected(false); // 清楚第一个图形选中状态
					PaintPane.lines.remove(line); // 清楚线
					PaintPane.history.remove(line);

				}
			}
		}
	}
}