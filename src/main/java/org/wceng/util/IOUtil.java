package org.wceng.util;

import java.io.*;

public class IOUtil {

    /**
     * 将文件转换为字节数组
     * @param file 输入的文件
     * @return 转换后的字节数组
     * @throws IOException 如果读取文件出现问题
     */
    public static byte[] fileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
    }

    /**
     * 将输入流转换为字节数组
     * @param inputStream 输入流
     * @return 转换后的字节数组
     * @throws IOException 如果读取输入流出现问题
     */
    public static byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
    }

}
