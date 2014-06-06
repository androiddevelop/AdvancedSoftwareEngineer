package cn.edu.ustc.ase.util;

import cn.edu.ustc.ase.gui.PaintPane;
import cn.edu.ustc.ase.shape.Shape;
import cn.edu.ustc.ase.shape.Point;

/**
 * 图形工具
 * 
 * @author Yuedong Li
 * 
 */
public class ShapeUtil {

	/**
	 * 得到选中的图形
	 * 
	 * @return
	 */
	public static Shape getSelectShape(Point point) {
		int len = PaintPane.history.size();
		for (int i = len - 1; i >= 0; i--) {
			Shape shape = PaintPane.history.get(i);
			if(shape.isMouseIn(point)){
				return shape;
			}
		}
		return null;
	}
}
