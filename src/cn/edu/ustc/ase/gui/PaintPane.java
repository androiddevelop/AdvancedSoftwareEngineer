package cn.edu.ustc.ase.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cn.edu.ustc.ase.shape.Circle;
import cn.edu.ustc.ase.shape.Shape;
import cn.edu.ustc.ase.shape.Line;
import cn.edu.ustc.ase.shape.Point;
import cn.edu.ustc.ase.shape.Rectangle;
import cn.edu.ustc.ase.state.CircleState;
import cn.edu.ustc.ase.state.DragState;
import cn.edu.ustc.ase.state.LineState;
import cn.edu.ustc.ase.state.MouseState;
import cn.edu.ustc.ase.state.RectangleState;
import cn.edu.ustc.ase.state.StateManager;

/*
 * 主画板
 */
public class PaintPane extends JPanel implements MouseListener,
		MouseMotionListener {
	private static final long serialVersionUID = -5298183648587558881L;
	private static StateManager stateManager = StateManager.getInstance(); // 状态管理器
	public static List<Circle> circles = new ArrayList<Circle>(); // 所有的圆
	public static List<Rectangle> rectangles = new ArrayList<Rectangle>(); // 所有的矩形
	public static List<Line> lines = new ArrayList<Line>(); // 所有的连线
	public static List<Shape> history = new ArrayList<Shape>(); // 所有的图形
	private MouseState mouseState; // 鼠标当前状态
	private Point mousePoint; // 鼠标位置

	// 四种状态
	private CircleState circleState = new CircleState();
	private RectangleState rectangleState = new RectangleState();
	private LineState lineState = new LineState();
	private DragState dragState = new DragState();

	/**
	 * 添加监听器
	 */
	public void addListener() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch (stateManager.getState()) {
		case NONE:
			break;
		case CIRCLE:
			circleState.modifyData(mouseState, mousePoint);
			break;
		case RECTANGLE:
			rectangleState.modifyData(mouseState, mousePoint);
			break;
		case LINE:
			lineState.modifyData(mouseState, mousePoint);
			break;
		case DRAG:
			dragState.modifyData(mouseState, mousePoint);
			break;
		}

		Graphics2D gg = (Graphics2D) g;
		gg.setStroke(new BasicStroke(3));

		// 开始画图
		// 首先连线画线,再换图形,这样可以在再次画连线时不突出显示内部线
		for (Shape shape : history) {
			// 设置画笔颜色
			if (shape.isSelected())
				gg.setColor(Color.GREEN);
			else
				gg.setColor(Color.BLACK);

			if (shape instanceof Line) {
				Line line = (Line) shape;
				Point point1 = line.getShape1().getCenterPoint();
				Shape shape2 = line.getShape2();
				Point point2;
				if (shape2 == null) {
					point2 = line.getTmpPoint();
				} else {
					point2 = shape2.getCenterPoint();
				}
				gg.drawLine(point1.x, point1.y, point2.x, point2.y);
			}
		}
		for (Shape shape : history) {
			// 设置画笔颜色
			if (shape.isSelected())
				gg.setColor(Color.GREEN);
			else
				gg.setColor(Color.BLACK);

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
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		sendMouseState(e, MouseState.PRESS);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		sendMouseState(e, MouseState.RELEASE);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		sendMouseState(e, MouseState.DRAG);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	/**
	 * 发送鼠标状态,同步
	 */
	public synchronized void sendMouseState(MouseEvent e, MouseState state) {
		mouseState = state;
		mousePoint = new Point(e.getX(), e.getY());
		updateUI();
	}
}