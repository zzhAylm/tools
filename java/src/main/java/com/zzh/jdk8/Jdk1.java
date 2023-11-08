package com.zzh.jdk8;

import lombok.Data;

import java.util.Optional;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/1 09:25
 */
public class Jdk1 {

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        System.out.println("----------");
        Optional.ofNullable(zoo).map(Zoo::getDog).map(Dog::getAge).ifPresent(System.out::println);


        Optional<Zoo> zoo1 = Optional.ofNullable(zoo);

        Optional<Dog> dog = zoo1.map(Zoo::getDog);

        System.out.println(dog.map(Dog::getAge));

        Integer integer = Optional.ofNullable(zoo).map(o -> o.getDog()).map(d -> d.getAge()).filter(v -> v == 1).orElse(3);


        System.out.println(integer);
    }


    @Data
    static class Zoo {
        private Dog dog;
    }

    @Data
    static class Dog {
        private int age;
    }
}
