package com.zzh.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2023/11/1 09:49
 */
public class jdk2 {

    public static void main(String[] args) {
        List<String[]> listOfArrays = Arrays.asList(
                new String[]{"apple", "banana", "cherry"},
                new String[]{"orange", "grape", "pear"},
                new String[]{"kiwi", "melon", "pineapple"}
        );

        List<String[]> mapResult = listOfArrays.stream()
                .map(array -> Arrays.stream(array).map(String::toUpperCase).toArray(String[]::new))
                .collect(Collectors.toList());

        System.out.println("Using map:");
        System.out.println(mapResult);

        List<String> flatMapResult = listOfArrays.stream()
                .flatMap(array -> Arrays.stream(array).map(String::toUpperCase))
                .collect(Collectors.toList());

        System.out.println("Using flatMap:");
        System.out.println(flatMapResult);






        // 使用flatMap的代码
        Optional<String[]> strings1 = Optional.ofNullable(listOfArrays)
                .flatMap(array -> array.stream().findFirst());

        strings1.ifPresent(strings -> System.out.println(Arrays.toString(strings)));
        String cityUsingFlatMap = strings1
                .map(strings ->strings[0])
                .orElse("Unknown");

        System.out.println("User's city using flatMap: " + cityUsingFlatMap);

//        // 不使用flatMap的代码
//        Optional<Optional<Address>> optionalAddress = getUserById(userId)
//                .map(OptionalExample::getAddressByUser);
//
//        String cityWithoutFlatMap;
//        if (optionalAddress.isPresent()) {
//            Optional<Address> addressOptional = optionalAddress.get();
//            if (addressOptional.isPresent()) {
//                Address address = addressOptional.get();
//                cityWithoutFlatMap = address.getCity();
//            } else {
//                cityWithoutFlatMap = "Unknown";
//            }
//        } else {
//            cityWithoutFlatMap = "Unknown";
//        }

//        System.out.println("User's city without flatMap: " + cityWithoutFlatMap);
    }
}
