package com.zzh.classloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Description: 自定义类加载器
 * @Author: zzh
 * @Crete 2023/3/7 20:24
 */
public class MyClassLoader extends ClassLoader {

    private String byteCodePath;

    /**
     *
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }


    // 自动以的，findClass的核心方法
    @Override
    protected Class<?> findClass(String name) {

        String path = byteCodePath + name + ".class";

        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
            baos = new ByteArrayOutputStream();
            int len;

            byte[] bytes = new byte[1024];
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            byte[] byteCodes = baos.toByteArray();

            return super.defineClass(null, byteCodes, 0, byteCodes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert baos != null;
                baos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
