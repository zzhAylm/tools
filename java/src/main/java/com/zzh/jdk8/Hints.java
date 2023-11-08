package com.zzh.jdk8;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Hints {
    Hint[] value();
}


@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Hints.class)
@interface Hint {
    String value();
}
