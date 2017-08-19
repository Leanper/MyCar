package com.ygst.cenggeche.utils;

import android.content.Context;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

/**
 * RSA加密工具
 *
 * @author Administrator
 */
public class RSAUtil {
    public static final String RSA_PUBLIC = "";
    private static final String ALGORITHM = "RSA";

    /**
     * 得到公钥
     *
     * @param algorithm
     * @param bysKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    private static PublicKey getPublicKeyFromX509(String algorithm,
                                                  String bysKey) throws NoSuchAlgorithmException, Exception {
        byte[] decodeKey = Base64.decode(bysKey, Base64.DEFAULT);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodeKey);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }

    /**
     * 使用公钥加密
     *
     * @param content
     * @return
     */
    public static String encryptByPublic(Context context, String content) {
        try {
            String pubKey = getPublicKeyFromAssets(context);
            PublicKey publicKey = getPublicKeyFromX509(ALGORITHM, pubKey);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] plaintext = content.getBytes();
            byte[] output = cipher.doFinal(plaintext);

            String str = Base64.encodeToString(output, Base64.DEFAULT);
            return replaceBlank(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取公钥
     *
     * @return
     */
    private static String getPublicKeyFromAssets(Context context) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context
                    .getResources().getAssets().open("public_key.pem"));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null) {
                if (line.charAt(0) == '-') {
                    continue;
                }
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //去空格，换行等
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}  