package com.zzh.websocket.resources;

import org.springframework.core.io.ResourceLoader;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/13 18:54
 */
public class ResourceLoaderAware implements org.springframework.context.ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader=resourceLoader;
    }
}
