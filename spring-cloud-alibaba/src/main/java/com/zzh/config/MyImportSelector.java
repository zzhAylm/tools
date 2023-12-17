package com.zzh.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com.zzh.domain.Apple",
                "com.zzh.domain.Banana",
                "com.zzh.domain.Watermelon"
        };
    }
}
