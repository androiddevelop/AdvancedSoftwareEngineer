package cn.edu.ustc.ase.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.edu.ustc.ase.gui.PaintPane;
import cn.edu.ustc.ase.shape.Circle;
import cn.edu.ustc.ase.shape.IShape;
import cn.edu.ustc.ase.shape.Line;
import cn.edu.ustc.ase.shape.Point;
import cn.edu.ustc.ase.shape.Rectangle;

/**
 * 图片处理类
 * 
 * @author Yuedong Li
 * 
 */
public class ImageUtil {

	private static boolean isGenerateJar = false; // 是否生成jar,生成jar时请不要仅仅修改此处,还需将png文件夹复制到src目录

	/**
	 * 缩放图片
	 * 
	 * @param imagePath
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resizeImage(String imagePath, int width,
			int height) {
		try {
			BufferedImage image;
			if (isGenerateJar)
				image = ImageIO.read(ImageUtil.class
						.getResourceAsStream("/"+imagePath));
			else
				image = ImageIO.read(new File(imagePath));
			BufferedImage out = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D gg = out.createGraphics();
			gg.drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(),
					image.getHeight(), null);
			gg.dispose();
			return out;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 导出面板对应图片文件
	 * 
	 * @param desPath
	 */
	public static void exportPng(String desPath) {
		try {
			BufferedImage buff = new BufferedImage(800, 500,
					BufferedImage.TYPE_INT_ARGB_PRE);
			Graphics2D gg = buff.createGraphics();
			gg.setStroke(new BasicStroke(3));
			gg.setColor(Color.WHITE);
			gg.fillRect(0, 0, 800, 500);
			gg.setColor(Color.BLACK);
			for (IShape shape : PaintPane.history) {
				if (shape instanceof Circle) { // 画圆
					Circle circle = (Circle) shape;
					Point point = circle.getCenter();
					int r = circle.getRadius();
					gg.fillArc(point.x - r, point.y - r, 2 * r, 2 * r, 0, 360);
				} else if (shape instanceof Rectangle) { // 画线
					Rectangle rectangle = (Rectangle) shape;
					Point startPoint = rectangle.getStartPoint();
					Point endPoint = rectangle.getEndPoint();
					gg.fillRect(startPoint.x, startPoint.y, endPoint.x
							- startPoint.x, endPoint.y - startPoint.y);
				} else if (shape instanceof Line) {
					Line line = (Line) shape;
					Point point1 = line.getShape1().getCenterPoint();
					IShape shape2 = line.getShape2();
					Point point2;
					if (shape2 == null) {
						point2 = line.getTmpPoint();
					} else {
						point2 = shape2.getCenterPoint();
					}
					gg.drawLine(point1.x, point1.y, point2.x, point2.y);
				}
			}
			gg.dispose();
			ImageIO.write(buff, "png", new File(desPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
