package com.qrsx.shop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class FileUtils {

	/**
	 * 字节输入流
	 */
	public static byte[] readInputStream(String filename) {
		try {
			File file = new File(filename);
			InputStream in = new FileInputStream(file);
			byte[] b = new byte[(int) file.length()];
			for (int i = 0; i < b.length; i++) {
				b[i] = (byte) in.read();
			}
			in.close();
			return b;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字节输出流：
	 * 
	 */
	public static boolean writeOutputStream(String filename, byte[] b) {
		try {
			File file = new File(filename);
			OutputStream out = new FileOutputStream(file);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
