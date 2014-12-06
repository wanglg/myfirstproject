package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

public class FileUtil {

	private static final String LOG_TAG = "FileUtil";

	public static String getSdDirectory() {
		return Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * @param src
	 *            源文件绝对路径
	 * @param dest
	 *            目标路径
	 * @return new file path if successful, or return null
	 */
	public static String copyFile(String src, String dest) {
		return copyFile(new File(src), new File(dest));
	}

	public static String copyFile(File src, File target) {
		return copyFileAndReName(src, target, null);

	}

	public static String copyFileAndReName(String src, String target,
			String newName) {

		return copyFileAndReName(new File(src), new File(target), newName);

	}

	/**
	 * 
	 * @param src
	 *            源文件
	 * @param target
	 *            目标路径
	 * @param newName
	 *            copy file name null = src's name
	 * @return 成功返回新文件的绝对路径 失败 null
	 */
	public static String copyFileAndReName(File src, File target, String newName) {

		if (src == null || !src.exists() || src.isDirectory()) {
			Log.v(LOG_TAG, "copyFile: file not exist or is directory, " + src);
			return null;
		}
		FileInputStream fi = null;
		FileOutputStream fo = null;
		try {
			fi = new FileInputStream(src);
			if (!target.exists()) {
				if (!target.mkdirs())
					return null;
			}

			if (newName == null || newName.equals("")) {
				newName = src.getName();
			}
			String destPath = makePath(target.getAbsolutePath(), newName);
			File destFile = new File(destPath);
			int i = 1;
			while (destFile.exists()) {
				String destName = getNameFromFilename(newName) + " " + i++
						+ "." + getExtFromFilename(src.getName());
				destPath = makePath(target.getAbsolutePath(), destName);
				destFile = new File(destPath);
			}

			if (!destFile.createNewFile())
				return null;

			fo = new FileOutputStream(destFile);
			int count = 102400;
			byte[] buffer = new byte[count];
			int read = 0;
			while ((read = fi.read(buffer, 0, count)) != -1) {
				fo.write(buffer, 0, read);
			}

			// TODO: set access privilege

			return destPath;
		} catch (FileNotFoundException e) {
			Log.e(LOG_TAG, "copyFile: file not found, " + src);
			e.printStackTrace();
		} catch (Exception e) {
			Log.e(LOG_TAG, "copyFile: " + e.toString());
		} finally {
			try {
				if (fi != null)
					fi.close();
				if (fo != null)
					fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	/**
	 * 以.为分界获取文件前缀名称
	 * 
	 * @param filename
	 * @return 文件前缀名称 不带.的返回全名
	 */
	public static String getNameFromFilename(String filename) {
		int dotPosition = filename.lastIndexOf('.');
		if (dotPosition != -1) {
			return filename.substring(0, dotPosition);
		}
		return filename;
	}

	public static String makePath(String path1, String path2) {
		if (path1.endsWith(File.separator))
			return path1 + path2;

		return path1 + File.separator + path2;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtFromFilename(String filename) {
		int dotPosition = filename.lastIndexOf('.');
		if (dotPosition != -1) {
			return filename.substring(dotPosition + 1, filename.length());
		}
		return "";
	}

}
