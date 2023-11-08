package com.zzh.sync;

import java.io.IOException;
import java.nio.file.*;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/10/27 17:15
 */
public class Test04 {

    public static void main(String[] args) throws IOException {
        // 创建 WatchService 对象
        WatchService watchService = FileSystems.getDefault().newWatchService();

// 初始化一个被监控文件夹的 Path 类:
        Path path = Paths.get("workingDirectory");
// 将这个 path 对象注册到 WatchService（监控服务） 中去
        WatchKey watchKey = path.register(watchService,StandardWatchEventKinds.ENTRY_CREATE);
    }
}
