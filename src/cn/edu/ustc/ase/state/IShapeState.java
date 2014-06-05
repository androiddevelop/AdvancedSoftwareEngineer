package cn.edu.ustc.ase.state;

import cn.edu.ustc.ase.shape.Point;

/**
 * 绘画图形状态
 * 
 * @author Yuedong Li
 * 
 */
public interface IShapeState {
	public void modifyData(MouseState state, Point point); // 修改数据
}
