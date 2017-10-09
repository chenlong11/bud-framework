package org.bud.framework.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by chenlong
 * Date：2017/9/27
 * time：9:43
 */
public class EncryptUtil {

    /**
     * sha1加密
     *
     * @param valueToDigest
     * @return
     */
    public static String SHA1Encrypt(String valueToDigest) {
        return DigestUtils.sha1Hex(valueToDigest);
    }

    /**
     * sha256加密
     *
     * @param valueToDigest
     * @return
     */
    public static String SHA256Encrypt(String valueToDigest) {
        return DigestUtils.sha256Hex(valueToDigest);
    }

    /**
     * md5加密
     *
     * @param valueToDigest
     * @return
     */
    public static String MD5Encrypt(String valueToDigest) {
        return DigestUtils.md5Hex(valueToDigest);
    }

    /**
     * HmacSha256加密
     *
     * @param key
     * @param valueToDigest
     * @return
     */
    public static String HmacSha256Encrypt(String key, String valueToDigest) {
        return HmacUtils.hmacSha256Hex(key, valueToDigest);
    }

    public static String base64Encrypt(String valueToDigest) {
        return new String(Base64.encodeBase64(valueToDigest.getBytes()));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码集错误,您目前指定的编码集是:" + charset);
        }
    }

}
