package com.dechain.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MD5加密工具类
 * @author happyqing
 */
public class MD5Util {

    private static final Log log = LogFactory.getLog(MD5Util.class);

    /**
     * 字符串的MD5
     * @param filePath
     * @return
     */
    public static String encode(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
            //System.out.println("result: " + buf.toString());// 32位的加密
            //System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 文件的MD5
     * @param filePath
     * @return
     */
    public static String getFileMD5(String filePath){
        String value = null;
        FileInputStream in = null;
        try {
            File file = new File(filePath);
            in = new FileInputStream(file);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int c;
            while ((c = in.read(buffer)) != -1) {
                md5.update(buffer, 0, c);
            }
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16).toUpperCase();
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
        return value;
    }

    public static void main(String args[]) {
        String str = "在我们的程序中，不管是什么，都会有安全问题，今天就说的是MD5加密的方法\n" +
                "\n" +
                "MD5是哈希算法，也就是 从明文A到密文B很容易，但是从密文B到明文A几乎不可能\n" +
                "\n" +
                "也就是说，给你密文，是几乎无法通过解密来得到明文的。\n" +
                "\n" +
                "这个一般用于存储密码。也就是数据库里存的是密文，管理员只能看到密文，而看不到明文。";
        Long b1 = System.currentTimeMillis();
        System.out.println(MD5Util.encode(str));
        Long e1 = System.currentTimeMillis();
        System.out.println("MD5.encode耗时:"+(e1-b1));
    }

}
