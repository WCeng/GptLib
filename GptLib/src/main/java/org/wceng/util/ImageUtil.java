package org.wceng.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ImageUtil {

    /**
     * 将图片文件转换为Base64编码字符串
     *
     * @param filePath 图片文件路径
     * @return Base64编码字符串
     * @throws IOException 如果读取文件或转换过程中发生错误
     */
    public static String encodeImageToBase64(String filePath) throws IOException {
        byte[] imageBytes = Files.readAllBytes(Path.of(filePath));
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}