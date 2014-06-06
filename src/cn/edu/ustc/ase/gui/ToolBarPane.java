package cn.edu.ustc.ase.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import cn.edu.ustc.ase.shape.Shape;
import cn.edu.ustc.ase.state.PaintState;
import cn.edu.ustc.ase.state.StateManager;
import cn.edu.ustc.ase.util.AseFilter;
import cn.edu.ustc.ase.util.ImageUtil;
import cn.edu.ustc.ase.util.PngFilter;

/**
 * 工具栏
 */
public class ToolBarPane extends JToolBar implements ActionListener {
	private static final long serialVersionUID = 1381967209975477486L;
	private PaintPane paintPane;
	private static StateManager stateManager = StateManager.getInstance(); // 状态管理器
	private JButton openBtn, saveBtn, exportBtn, circleBtn, rectangleBtn,
			lineBtn, dragBtn, commandBtn, aboutBtn;

	public ToolBarPane(PaintPane paintPane) {
		this.paintPane = paintPane;
		this.setOrientation(SwingConstants.HORIZONTAL);
		this.setFloatable(true);
		this.setBorderPainted(false);
		this.setRollover(true);
		setFunction();
	}

	/**
	 * 设置相应功能
	 */
	public void setFunction() {
		int width = 50; // 工具栏图标宽度
		int height = 50; // 工具栏图标高度
		openBtn = new JButton(new ImageIcon(ImageUtil.resizeImage(
				"png/open.png", width, height)));
		openBtn.addActionListener(this);
		this.add(openBtn);

		saveBtn = new JButton(new ImageIcon(ImageUtil.resizeImage(
				"png/save.png", width, height)));
		saveBtn.addActionListener(this);
		this.add(saveBtn);

		exportBtn = new JButton(new ImageIcon(ImageUtil.resizeImage(
				"png/export.png", width, height)));
		exportBtn.addActionListener(this);
		this.add(exportBtn);

		this.addSeparator(new Dimension(20, 40));

		circleBtn = new JButton(new ImageIcon(ImageUtil.resizeImage(
				"png/circle.png", width, height)));
		circleBtn.addActionListener(this);
		this.add(circleBtn);

		rectangleBtn = new JButton(new ImageIcon(ImageUtil.resizeImage(
				"png/rectangle.png", width, height)));
		rectangleBtn.addActionListener(this);
		this.add(rectangleBtn);

		lineBtn = new JButton(new ImageIcon(ImageUtil.resizeImage(
				"png/line.png", width, height)));
		lineBtn.addActionListener(this);
		this.add(lineBtn);

		dragBtn = new JButton(new ImageIcon(ImageUtil.resizeImage(
				"png/drag.png", width, height)));
		dragBtn.addActionListener(this);
		this.add(dragBtn);

		this.addSeparator(new Dimension(20, 40));

		commandBtn = new JButton(new ImageIcon(ImageUtil.resizeImage(
				"png/command.png", width, height)));
		commandBtn.addActionListener(this);
		this.add(commandBtn);

		this.addSeparator(new Dimension(20, 40));

		aboutBtn = new JButton(new ImageIcon(ImageUtil.resizeImage(
				"png/about.png", width, height)));
		aboutBtn.addActionListener(this);
		this.add(aboutBtn);

		openBtn.setToolTipText("打开文件");
		saveBtn.setToolTipText("保存文件");
		exportBtn.setToolTipText("导出文件");
		circleBtn.setToolTipText("圆形工具");
		rectangleBtn.setToolTipText("矩形工具");
		lineBtn.setToolTipText("连线工具");
		dragBtn.setToolTipText("拖动工具");
		commandBtn.setToolTipText("命令工具");
		aboutBtn.setToolTipText("关于程序");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openBtn) {
			String filePath = fileChoose("打开文件");
			if (filePath == null || filePath.length() == 0)
				return;
			try {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(filePath));
				PaintPane.history = (List<Shape>) in.readObject();
				in.close();
				paintPane.updateUI();
			} catch (IOException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			return;
		}
		if (e.getSource() == saveBtn) {
			String filePath = fileChoose("保存文件");
			if (filePath == null || filePath.length() == 0)
				return;
			try {
				filePath = filePath.endsWith(".ase") ? filePath : filePath
						+ ".ase";

				ObjectOutputStream os = new ObjectOutputStream(
						new FileOutputStream(filePath));
				os.writeObject(PaintPane.history);
				os.flush();
				os.close();

				JOptionPane.showMessageDialog(paintPane, "保存成功", "提示",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
		}
		if (e.getSource() == exportBtn) {
			String filePath = fileChoose("导出图片");
			if (filePath == null || filePath.length() == 0)
				return;
			filePath = filePath.endsWith(".png") ? filePath : filePath + ".png";
			ImageUtil.exportPng(filePath);
			JOptionPane.showMessageDialog(paintPane, "导出成功", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (e.getSource() == circleBtn) {
			setSelectState(circleBtn);
			stateManager.setState(PaintState.CIRCLE);
			return;
		}
		if (e.getSource() == rectangleBtn) {
			setSelectState(rectangleBtn);
			stateManager.setState(PaintState.RECTANGLE);
			return;
		}
		if (e.getSource() == lineBtn) {
			setSelectState(lineBtn);
			stateManager.setState(PaintState.LINE);
			return;
		}

		if (e.getSource() == dragBtn) {
			setSelectState(dragBtn);
			stateManager.setState(PaintState.DRAG);
			return;
		}

		if (e.getSource() == commandBtn) {
			CommandDialog commandDialog = new CommandDialog(paintPane);
			commandDialog.setSize(500, 400);
			commandDialog.setLocationRelativeTo(null);
			commandDialog.setVisible(true);
			return;
		}

		if (e.getSource() == aboutBtn) {
			JOptionPane.showMessageDialog(paintPane,
					"高级软件工程大作业\n李跃东 SA13011918", "关于程序",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

	/**
	 * 设置选择状态
	 */
	private void setSelectState(JButton btn) {
		openBtn.setBackground(Color.WHITE);
		saveBtn.setBackground(Color.WHITE);
		exportBtn.setBackground(Color.WHITE);
		circleBtn.setBackground(Color.WHITE);
		rectangleBtn.setBackground(Color.WHITE);
		lineBtn.setBackground(Color.WHITE);
		dragBtn.setBackground(Color.WHITE);
		commandBtn.setBackground(Color.WHITE);
		aboutBtn.setBackground(Color.WHITE);
		btn.setBackground(Color.GRAY);
	}

	/**
	 * 文件选择
	 * 
	 * @return
	 */
	public String fileChoose(String title) {
		UIManager.put("FileChooser.cancelButtonText", "取消"); // 修改取消
		if (title.indexOf("打开") != -1) {
			UIManager.put("FileChooser.openButtonText", "打开");// 修改打开
		} else if (title.indexOf("保存") != -1) {
			UIManager.put("FileChooser.openButtonText", "保存");// 修改打开
		} else {
			UIManager.put("FileChooser.openButtonText", "导出");// 修改打开
		}
		JFileChooser choose = new JFileChooser();
		if (title.indexOf("导出") != -1) {
			choose.setFileFilter(new PngFilter());
		} else {
			choose.setFileFilter(new AseFilter());
		}
		choose.setDialogTitle(title);
		int result = choose.showOpenDialog(this); // 打开文件操作对话框
		if (result == JFileChooser.APPROVE_OPTION) {
			String filePath = choose.getSelectedFile().getAbsolutePath();
			return filePath;
		}
		return null;
	}
}