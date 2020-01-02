package com.ruoyi.common.utils.file;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Aibal.He
 * @date 2019/12/31 5:53 PM
 */
public class ImageUtils {

    public static final int[][] X_SOBEL = new int[][] { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
    public static final int[][] Y_SOBEL = new int[][] { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
    public static final int BLOCK_PIXEL_RADIUS = 5;

    public static void main(String[] args){
        //ImageUtils.draw("1");
    }

    public static String draw(String fileName, int row,int cel){
        //filePath = "/home1/pintu/uploadPath/2019/12/27/3b35c95a5d2e21b73582570da15b6261.jpg";
        String filePath =RuoYiConfig.getDownloadPath() + fileName.substring(fileName.indexOf("profile") + 7);
        File file = new File(filePath);
        //获取图片存放路径
        if (file.exists()) {
            return cutImageToBase64AboutColor(file,row,cel);
        }
        return null;

    }

    public static List<String> generate(String filePath, int row,int cel){
        //filePath = "/home1/pintu/uploadPath/2019/12/27/3b35c95a5d2e21b73582570da15b6261.jpg";//RuoYiConfig.getDownloadPath() + fileName.substring(fileName.indexOf("profile") + 7);
        File file = new File(filePath);
        //获取图片存放路径
        if (file.exists()) {
            List<String> list = cutImageToBase64(file,row,cel);
            return list;
        }
        return null;

    }

    public static String cutImageToBase64AboutColor(File sourceFile, int row,int cel) {
        String lettersCommaSeparated = null;
        try {
            BufferedImage source = ImageIO.read(sourceFile);
            int width = source.getWidth(); // 图片宽度
            int height = source.getHeight(); // 图片高度
            int cWidth = width/cel; // 切片宽度
            int cHeight = height/row; // 切片高度
            BufferedImage image = null;
            List<String> list = new ArrayList<>();
            for (int i = row-1; i >=0; i--) {
                for(int j= 0;j<cel;j++){
                    // x坐标,y坐标,宽度,高度
                    BASE64Encoder encoder = new BASE64Encoder();

                    image = source.getSubimage(j*cWidth,i*cHeight,cWidth,cHeight);
                    Map<Integer, Integer> colorIndexMap = new HashMap<Integer, Integer>();
                    for (int imageRow = 0; imageRow < cHeight; imageRow++) {
                        for (int imageCol = 0; imageCol < cWidth; imageCol++) {
                            int pixelValue = image.getRGB(imageCol, imageRow);
                            if (!colorIndexMap.containsKey(pixelValue)) {
                                colorIndexMap.put(pixelValue, 1);
                            }else{
                                colorIndexMap.put(pixelValue,colorIndexMap.get(pixelValue)+1);
                            }
                        }
                    }
                    //获取颜色最多的一个
                    int maxNum = 0;
                    int pixel = 0;
                    for(Map.Entry<Integer,Integer> entry :colorIndexMap.entrySet()){
                        if(entry.getValue()>maxNum){
                            maxNum = entry.getValue();
                            pixel = entry.getKey();
                        }

                    }

                    int alpha = (pixel >> 24) & 0xFF;
                    int red = (pixel >> 16) & 0xFF;
                    int green = (pixel >> 8) & 0xFF;
                    int blue = pixel & 0xFF;
                    //System.out.println(red+","+green+","+blue);
                    list.add(red+"|"+green+"|"+blue);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "PNG", baos);
                    byte[] bytes = baos.toByteArray();
                    System.out.println("data:image/jpeg;base64,"+encoder.encodeBuffer(bytes));
                    baos.close();
                }

            }
            lettersCommaSeparated= String.join(",", list);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return lettersCommaSeparated;
    }

    public static List<String> cutImageToBase64(File sourceFile, int row,int cel) {
        String filePath = RuoYiConfig.getUploadPath();
        List<String> list = new LinkedList<>();
        try {
            BufferedImage source = ImageIO.read(sourceFile);
            int width = source.getWidth(); // 图片宽度
            int height = source.getHeight(); // 图片高度
            int cWidth = width/cel; // 切片宽度
            int cHeight = height/row; // 切片高度
            BufferedImage image = null;
            int no  = 0;
            for (int i = row-1; i >=0; i--) {
                for(int j= 0;j<cel;j++){
                    // x坐标,y坐标,宽度,高度
                    //BASE64Encoder encoder = new BASE64Encoder();
                    image =  source.getSubimage(j*cWidth,i*cHeight,cWidth,cHeight);
                    String fileName = DateUtils.datePath() + "/" + FileUploadUtils.encodingFilename(""+no) + "." + "jpg";
                    ImageIO.write(image, "jpg", new File(filePath+"/"+fileName));
                    list.add(FileUploadUtils.getPathFileName(filePath,fileName));
                   // ByteArrayOutputStream baos = new ByteArrayOutputStream();
                   // ImageIO.write(image, "PNG", baos);
                   // byte[] bytes = baos.toByteArray();
                   // System.out.println("data:image/jpeg;base64,"+encoder.encodeBuffer(bytes));
                   // baos.close();
                    no++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static BufferedImage removeBlendPixels(BufferedImage resultImg, int raidus) {
        int width = resultImg.getWidth();
        int height = resultImg.getHeight();
        int[] pixels = new int[width * height];
       // getRGB(image, 0, 0, width, height, pixels);
        // 创建处理结果
       // BufferedImage resultImg = createCompatibleDestImage(image, null);
        //setRGB(resultImg, 0, 0, width, height, pixels);
        // 灰度化与梯度求取
        byte[] grayData = getGrayData(pixels, width, height);
        byte[] binaryData = getGrident(grayData, width, height);
        int index = 0;
        for (int row = 1; row < height - 1; row++) {
            for (int col = 1; col < width - 1; col++) {
                index = row * width + col;
                int pixel = (binaryData[index] & 0xff);
                if (pixel > 0) {
                    // 半径扫描操作
                    int mindis = Integer.MAX_VALUE;
                    int minrow = -1;
                    int mincol = -1;
                    int nr = 0;
                    int nc = 0;
                    int index2 = 0;
                    for (int subrow = -raidus; subrow <= raidus; subrow++) {
                        nr = row + subrow;
                        if (nr < 0 || nr >= height) {
                            continue;
                        }
                        for (int subcol = -raidus; subcol <= raidus; subcol++) {
                            nc = col + subcol;
                            if (nc < 0 || nc >= width) {
                                continue;
                            }
                            index2 = nr * width + nc;
                            int value = (binaryData[index2] & 0xff);
                            if (value == 0) {
                                int distance = distanceColor(resultImg.getRGB(nc, nr), resultImg.getRGB(col, row));
                                if (distance < mindis) {
                                    mindis = distance;
                                    minrow = nr;
                                    mincol = nc;
                                }
                            }
                        }
                    }
                    resultImg.setRGB(col, row, resultImg.getRGB(mincol, minrow));
                }
            }
        }
        return resultImg;
    }
    public static int distanceColor(int rgb, int rgb2) {
        // Color one
        int r1 = (rgb >> 16) & 0xff;
        int g1 = (rgb >> 8) & 0xff;
        int b1 = rgb & 0xff;
        // Color two
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >> 8) & 0xff;
        int b2 = rgb2 & 0xff;
        // distance
        int rr = r1 - r2;
        int gg = g1 - g2;
        int bb = b1 - b2;
        int sum = (int) Math.sqrt(rr * rr + gg * gg + bb * bb);
        return sum;
    }
    public static byte[] getGrayData(int[] inPixels, int width, int height) {
        // 图像灰度化
        byte[] outPixels = new byte[width * height];
        int index = 0;
        for (int row = 0; row < height; row++) {
            int tr = 0, tg = 0, tb = 0;
            for (int col = 0; col < width; col++) {
                index = row * width + col;
                tr = (inPixels[index] >> 16) & 0xff;
                tg = (inPixels[index] >> 8) & 0xff;
                tb = inPixels[index] & 0xff;
                int gray = (int) (0.299 * tr + 0.587 * tg + 0.114 * tb);
                outPixels[index] = (byte) (gray & 0xff);
            }
        }
        return outPixels;
    }
    public static byte[] getGrident(byte[] inPixels, int width, int height) {
        byte[] outPixels = new byte[width * height];
        int index = 0;
        for (int row = 0; row < height; row++) {
            int tr = 0;
            for (int col = 0; col < width; col++) {
                if (row == 0 || col == 0 || (row == height - 1) || (col == width - 1)) {
                    index = row * width + col;
                    outPixels[index] = (byte) (0x00);
                    continue;
                }
                int xg = 0, yg = 0;
                for (int sr = -1; sr <= 1; sr++) {
                    for (int sc = -1; sc <= 1; sc++) {
                        int nrow = row + sr;
                        int ncol = col + sc;
                        if (nrow < 0 || nrow >= height) {
                            nrow = 0;
                        }
                        if (ncol < 0 || ncol >= width) {
                            ncol = 0;
                        }
                        index = nrow * width + ncol;
                        tr = (inPixels[index] & 0xff);
                        xg += X_SOBEL[sr + 1][sc + 1] * tr;
                        yg += Y_SOBEL[sr + 1][sc + 1] * tr;
                    }
                }
                index = row * width + col;
                int g = (int) Math.sqrt(xg * xg + yg * yg);
                outPixels[index] = (byte) ((g) & 0xff);
            }
        }
        return outPixels;
    }

}
