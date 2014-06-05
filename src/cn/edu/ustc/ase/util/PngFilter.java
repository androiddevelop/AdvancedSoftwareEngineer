package cn.edu.ustc.ase.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * png文件过滤器
 * 
 * @author Yuedong Li
 * 
 */
public class PngFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		String name = f.getName();
		return name.toLowerCase().endsWith(".png") || f.isDirectory();
	}

	@Override
	public String getDescription() {
		return ".png";
	}

}
