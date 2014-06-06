package cn.edu.ustc.ase.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import cn.edu.ustc.ase.gui.PaintPane;
import cn.edu.ustc.ase.shape.Shape;

/**
 * ase矢量文件转换为png图片
 * 
 * @author Yuedong Li
 * 
 */
public class AseToPng {

	public static void main(String[] args) {
		//直接将ase文件转化为图片
		String asePath = "/home/honest/Desktop/abc.ase";
		String desPath = "/home/honest/Desktop/abc.png";
		generateImage(asePath, desPath);
	}

	/**
	 * 转换ase文件位png文件
	 * 
	 * @param asePath
	 *            ase文件路径
	 * @param desPath
	 *            目标文件路径
	 */
	@SuppressWarnings("unchecked")
	public static void generateImage(String asePath, String desPath) {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(asePath));
			PaintPane.history = (List<Shape>) in.readObject();
			in.close();
			ImageUtil.exportPng(desPath);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}