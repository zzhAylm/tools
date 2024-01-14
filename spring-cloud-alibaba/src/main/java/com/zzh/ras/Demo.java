package com.zzh.ras;

import java.security.*;
import java.util.Base64;


public class Demo {

    /***
     *
     * */

    public static void main(String[] args) throws NoSuchAlgorithmException {

        StringBuilder value = new StringBuilder();
        for (int i = 0; i <= 29; i++) {
            value.append("18cm");
        }
        System.out.println("待加密内容长度: " + value.toString().length());
        // 加密算法
        String algorithm = "RSA";
        // 实例化秘钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 初始化，秘钥长度512~16384位，64倍数
        keyPairGenerator.initialize(1024);
        // 生成秘钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 公钥
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("RSA公钥: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        // 私钥
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("RSA私钥: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        // ------ 测试公钥加密，私钥解密 ------
        String pubEncrypt = RsaUtil.encrypt(publicKey, value.toString());
        System.out.println("RSA公钥加密后数据: " + pubEncrypt);

        String priDecrypt = RsaUtil.decrypt(privateKey, pubEncrypt);
        System.out.println("RSA私钥解密后数据: " + priDecrypt);

    }
}
