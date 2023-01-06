package com.zzh.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/1/4 21:50
 */
public class Cas3 {

    //cas的缺点：循环时间长，消耗越来越大
    // ABA 问题
    //  解决ABA问题： 版本号，戳记流水
    // AtomicStampedReference
    public static void main(String[] args) {
        Book javaBook = new Book(1, "java");
        AtomicStampedReference<Book> reference = new AtomicStampedReference<>(javaBook, 0);

        Book mysqlBook = new Book(2, "mysql");
        reference.compareAndSet(javaBook, mysqlBook, reference.getStamp(),
                reference.getStamp() + 1);

    }


}

@Data
@NoArgsConstructor
class Book {
    private Integer id;
    private String name;


    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
