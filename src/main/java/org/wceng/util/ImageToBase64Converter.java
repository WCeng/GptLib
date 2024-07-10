package org.wceng.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class ImageToBase64Converter {

    /**
     * 将图片文件转换为Base64编码字符串
     *
     * @param imagePath 图片文件路径
     * @return Base64编码字符串
     */
    public static String convertImageToBase64(String imagePath) {
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte[] imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);
            return Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.err.println("Image file not found: " + imagePath);
        } catch (IOException e) {
            System.err.println("Error reading the image: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null; // 或者返回一个默认值，如 ""
    }
}

