package com.zzh;


import org.junit.jupiter.api.Test;

import java.security.*;
import java.util.Base64;

public class RsaSignatureDemo {

    @Test
    public void test() throws Exception {
        String value = "mrbird's blog";
        // 非对称加密算法
        String algorithm = "RSA";
        // 签名算法，RSA+SHA
        String signAlgorithm = "SHA256withRSA";

        // ----- 公私钥生成 --------
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

        // ----- 私钥加签 ---------
        // 获取签名对象
        Signature signature = Signature.getInstance(signAlgorithm);
        signature.initSign(privateKey);
        signature.update(value.getBytes());
        byte[] sign = signature.sign();
        System.out.println("签名值: " + Base64.getEncoder().encodeToString(sign));

        // ----- 公钥验签 ---------
        signature.initVerify(publicKey);
        signature.update(value.getBytes());
        System.out.println("验签结果: " + signature.verify(sign));

    }
}
