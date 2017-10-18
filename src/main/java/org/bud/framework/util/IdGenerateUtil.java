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
    private static String getFixLenthRandom(int length) {
        //35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < length){
            //生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }

}
