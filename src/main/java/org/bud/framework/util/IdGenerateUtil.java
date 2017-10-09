package org.bud.framework.util;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * Created by chenlong
 * Date：2017/7/30
 * time：11:10
 */
public class IdGenerateUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generate16Id() {
        String uuid =  generateUUID();
        String nextId = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(uuid.getBytes());
            byte tmp[] = md.digest();
            char str[] = new char[16];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                //只取高位
                str[k++] = hexDigits[(byte0 >>> 4 & 0xf) % 10];
                // str[k++] = hexDigits[byte0 & 0xf];
            }
            nextId = new String(str);  // 换后的结果转换为字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextId;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            System.out.println(generate16Id());
        }
    }

    /**
     * 生成固定位数随机数
     * @return
     */
    private static String getFixLenthRandom(int strLength) {
        strLength += 1;
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        // 返回固定的长度的随机数
        return fixLenthString.substring(2, strLength + 1);
    }

}
