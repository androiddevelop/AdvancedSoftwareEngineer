package cn.edu.ustc.ase.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * ase(高级软件工程)文件过滤器
 * 
 * @author Yuedong Li
 * 
 */
public class AseFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		String name = f.getName();
		return name.toLowerCase().endsWith(".ase") || f.isDirectory();
	}

	@Override
	public String getDescription() {
		return ".ase";
	}

}
