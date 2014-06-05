package cn.edu.ustc.ase.factory;

import cn.edu.ustc.ase.shape.Circle;
import cn.edu.ustc.ase.shape.IShape;
import cn.edu.ustc.ase.shape.Line;
import cn.edu.ustc.ase.shape.Rectangle;

/**
 * 图形工厂
 * 
 * @author Yuedong Li
 * 
 */
public class ShapeFactory implements IFactory {

	private static ShapeFactory shapeFactory;

	private ShapeFactory() {
	}

	/**
	 * 获取图形工厂的实例
	 * 
	 * @return
	 */
	public static ShapeFactory getInstance() {
		if (shapeFactory == null)
			shapeFactory = new ShapeFactory();
		return shapeFactory;
	}

	@Override
	public IShape generateShape(String describe) {
		IShape shape = null;
		switch (describe) {
		case "circle":
			shape = new Circle();
			break;
		case "rectangle":
			shape = new Rectangle();
			break;
		case "line":
			shape = new Line();
			break;
		}
		return shape;
	}
}