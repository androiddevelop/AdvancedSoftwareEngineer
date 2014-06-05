package cn.edu.ustc.ase;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import cn.edu.ustc.ase.gui.PaintPane;
import cn.edu.ustc.ase.gui.ToolBarPane;

/**
 * 主入口
 * 
 * @author Yuedong Li
 * 
 */
public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("高级软件工程");
		PaintPane paintPane = new PaintPane();
		paintPane.addListener();
		ToolBarPane toolBarPane = new ToolBarPane(paintPane);
		frame.add(toolBarPane, BorderLayout.NORTH);
		frame.add(paintPane, BorderLayout.CENTER);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}