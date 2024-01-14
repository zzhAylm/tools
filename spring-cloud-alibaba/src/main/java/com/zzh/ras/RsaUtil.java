package com.zzh.ras;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * RSA分段加解密
 * 针对秘钥长度为1024bits
 */
public class RsaUtil {

    // 最大加密块长度 1024/8 - 11
    private static final int MAX_ENCRYPT_BLOCK = 117;
    // 最大解密块长度 1024/8
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final String TRANSFORM = "RSA/ECB/PKCS1Padding";

    /**
     * 公钥加密
     *
     * @param publicKey 公钥
     * @param value     待加密值
     * @return 加密值
     */
    public static String encrypt(PublicKey publicKey, String value) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Cipher cipher = Cipher.getInstance(TRANSFORM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = value.getBytes();
            int length = bytes.length;

            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (length - offSet > 0) {
                if (length - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(bytes, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(bytes, offSet, length - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 私钥解密
     *
     * @param privateKey 私钥
     * @param encrypt    带解密值
     * @return 解密值
     */
    public static String decrypt(PrivateKey privateKey, String encrypt) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Cipher cipher = Cipher.getInstance(TRANSFORM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] bytes = Base64.getDecoder().decode(encrypt);
            int length = bytes.length;

            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (length - offSet > 0) {
                if (length - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(bytes, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(bytes, offSet, length - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
