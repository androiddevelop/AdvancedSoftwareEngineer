package cn.edu.ustc.ase.factory;

import cn.edu.ustc.ase.shape.IShape;

/**
 * 图形工厂
 * 
 * @author Yuedong Li
 * 
 */
public interface IFactory {
	/**
	 * 生成图形
	 * 
	 * @return
	 */
	public IShape generateShape(String describe);
}
