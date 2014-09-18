package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	/**
     * COPYÎÄ¼þ Î´²âÊÔ
     * @param srcFile String
     * @param desFile String
     * @return boolean
     */
    public static boolean copyToFile(String srcFile, String desFile) {
        File scrfile = new File(srcFile);
        if (scrfile.isFile() == true) {
            int length;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(scrfile);
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            File desfile = new File(desFile);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(desfile, false);
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            desfile = null;
            length = (int) scrfile.length();
            byte[] b = new byte[length];
            try {
                fis.read(b);
                fis.close();
                fos.write(b);
                fos.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            scrfile = null;
            return false;
        }
        scrfile = null;
        return true;
    }

}
