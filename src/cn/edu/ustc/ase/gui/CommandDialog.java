package cn.edu.ustc.ase.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import cn.edu.ustc.ase.factory.ShapeFactory;
import cn.edu.ustc.ase.shape.Circle;
import cn.edu.ustc.ase.shape.IShape;
import cn.edu.ustc.ase.shape.Point;
import cn.edu.ustc.ase.shape.Rectangle;

/**
 * 命令面板
 * 
 * @author Yuedong Li
 * 
 */
public class CommandDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = -1507581947906970790L;
	private JTextArea cmdTa;
	private JButton cmdBtn;
	private PaintPane paintPane;

	public CommandDialog(PaintPane paintPane) {
		this.paintPane = paintPane;
		this.setTitle("命令控制台");
		JLabel label = new JLabel(
				"<html>命令实例如下:<br>1 创建一个圆心(100,100),半径是20的圆<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp circle#100#100#20<br>"
						+ "<br>2 创建一个圆心(100,100),半径是20,放大1.5倍的圆<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp circle#100#100#20#1.5<br>"
						+ "<br>3 创建一个起点是(100,100),宽是50,高是50的矩形<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp rectangle#100#100#50#50<br><br>"
						+ "<br>在下面输入命令,每行一条,可以输入多行命令:<br></html>");
		cmdTa = new JTextArea();
		cmdTa.setSize(500, 50);
		cmdBtn = new JButton("执 行");
		cmdBtn.addActionListener(this);
		this.add(label, BorderLayout.NORTH);
		this.add(cmdTa, BorderLayout.CENTER);
		this.add(cmdBtn, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String texts = cmdTa.getText().trim();
		if (texts.length() == 0) {
			JOptionPane.showMessageDialog(paintPane, "输入为空", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String[] textArr = texts.split("\\n");
		ShapeFactory shapeFactory = ShapeFactory.getInstance();
		List<IShape> cmdShape = new ArrayList<IShape>();
		for (int i = 0; i < textArr.length; i++) { // 检查每一条命令是否正确,将正确的加入暂存序列中
			String text = textArr[i];
			String[] item = text.split("#");
			if (text.matches("circle#\\d+#\\d+#\\d+")) {
				Circle circle = (Circle) shapeFactory.generateShape(item[0]);
				circle.setCenterPoint(new Point(Integer.valueOf(item[1]),
						Integer.valueOf(item[2])));
				circle.setRadius(Integer.valueOf(item[3]));
				cmdShape.add(circle);
			} else if (text.matches("circle#\\d+#\\d+#\\d+#\\d+(\\.\\d+)?")) {
				Circle circle = (Circle) shapeFactory.generateShape(item[0]);
				circle.setCenterPoint(new Point(Integer.valueOf(item[1]),
						Integer.valueOf(item[2])));
				circle.setRadius((int) (Integer.valueOf(item[3]) * Double
						.valueOf(item[4])));
				cmdShape.add(circle);
			} else if (text.matches("rectangle#\\d+#\\d+#-?\\d+#-?\\d+")) {
				Rectangle rectangle = (Rectangle) shapeFactory
						.generateShape(item[0]);
				rectangle.setStartPoint(new Point(Integer.valueOf(item[1]),
						Integer.valueOf(item[2])));
				rectangle.setEndPoint(new Point(Integer.valueOf(item[1])
						+ Integer.valueOf(item[3]), Integer.valueOf(item[2])
						+ Integer.valueOf(item[4])));
				System.out.println(rectangle.getStartPoint().x + " "
						+ rectangle.getStartPoint().y + " "
						+ rectangle.getEndPoint().x + " "
						+ rectangle.getEndPoint().y);
				cmdShape.add(rectangle);
			} else {
				JOptionPane
						.showMessageDialog(paintPane, "第" + (i + 1)
								+ "行命令格式错误,请检查!", "提示",
								JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		// 命令均正确后执行
		for (IShape shape : cmdShape)
			PaintPane.history.add(shape);
		this.setVisible(false); // 隐藏对话框
		paintPane.updateUI(); // 更新画板

		JOptionPane.showMessageDialog(paintPane, "执行成功", "提示",
				JOptionPane.INFORMATION_MESSAGE);
		cmdTa.setText("");
	}
}