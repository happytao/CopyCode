package com.xt.garbage.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @author:DIY
 * @date: 2021/4/6
 */
public class DesUtil {
    //密钥
    private final static  String SECRET_KEY = "shouxinyizhanshouxinyizhan";

    //向量
    private final static String IV = "sxyz2021";

    //加解密编码方式
    private final static String ENCODING = "utf-8";

    /**
     * 3DES加密
     * @param plainText
     * @return
     * @throws Exception
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encode(String plainText) throws Exception {
        Key desKey = null;
        DESedeKeySpec spec = new DESedeKeySpec(SECRET_KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
        desKey = keyFactory.generateSecret(spec);
        //Cipher n,密码，暗号
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, desKey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(ENCODING));
        return Base64.getEncoder().encodeToString(encryptData);
    }

    /**
     * 3DES解密
     * @param encryptText
     * @return
     * @throws Exception
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decode(String encryptText) throws Exception {
        Key desKey = null;
        DESedeKeySpec spec = new DESedeKeySpec(SECRET_KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
        desKey = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE,desKey,ips);
        byte[] decryptData = cipher.doFinal(Base64.getDecoder().decode(encryptText.replace(" ","")));
        return new String(decryptData,ENCODING);
    }
}
