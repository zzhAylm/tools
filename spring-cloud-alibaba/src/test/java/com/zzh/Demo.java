package com.zzh;


import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;


public class Demo {

    @Test
    public void test() throws Exception {
        String value = "mrbird's blog";
        // 加密算法
        String algorithm = "RSA";
        // 转换模式
        String transform = "RSA/ECB/PKCS1Padding";
        // 实例化秘钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 初始化，秘钥长度512~16384位，64倍数
        keyPairGenerator.initialize(512);
        // 生成秘钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 公钥
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("RSA公钥: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        // 私钥
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("RSA私钥: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        // ------ 测试公钥加密，私钥解密 ------
        Cipher cipher = Cipher.getInstance(transform);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] pubEncryptBytes = cipher.doFinal(value.getBytes());
        System.out.println("RSA公钥加密后数据: " + Base64.getEncoder().encodeToString(pubEncryptBytes));

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] priDecryptBytes = cipher.doFinal(pubEncryptBytes);
        System.out.println("RSA私钥解密后数据: " + new String(priDecryptBytes));

        // ------ 测试私钥加密，公钥解密 ------
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] priEncryptBytes = cipher.doFinal(value.getBytes());
        System.out.println("RSA私钥加密后数据: " + Base64.getEncoder().encodeToString(priEncryptBytes));

        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] pubDecryptBytes = cipher.doFinal(priEncryptBytes);
        System.out.println("RSA公钥解密后数据: " + new String(pubDecryptBytes));
    }
}
