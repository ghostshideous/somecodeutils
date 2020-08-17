package com.example.readword.lambda;

import com.example.readword.exceltest.ExcelModel;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Qin
 * @version 1.0
 * @description
 * @since 2020/8/15
 */
public class LambdaTest {


    @Test
    public void testLambda() {
        ArrayList<ExcelModel> intArr = new ArrayList<>();
        intArr.add(new ExcelModel(1, "Lee", 18));
        intArr.add(new ExcelModel(2, "stone", 30));
        intArr.add(new ExcelModel(6, "john", 14));
        intArr.forEach(System.out::println);
        Predicate<ExcelModel> filter = p -> p.getAge() > 18;
        System.out.println("=============");
//        List<ExcelModel> collect = intArr.stream().filter(filter).collect(Collectors.toList());
        Optional<ExcelModel> max = intArr.stream().min((a, b) -> a.getAge().compareTo(b.getAge()));
        List<Integer> collect = intArr.stream().map(p -> p.getId()).collect(Collectors.toList());
        collect.forEach(p -> System.out.println(p));
        System.out.println("=============");
        Map<String, List<ExcelModel>> collect1 = intArr.stream().collect(Collectors.groupingBy(ExcelModel::getName));
        for (String integer : collect1.keySet()) {
            System.out.println(integer + "==》" + collect1.get(integer));

        }

    }

    @Test
    public void testLambda2() {
        ArrayList<User> intArr = new ArrayList<>();
        intArr.add(new User("1", "Joe", "zhen", "admin", "/dafd/fa", "0", "U9H930kfs", new Date(), new Date(), 41, new BigDecimal("56")));
        intArr.add(new User("2", "Smy", "zhen", "admin", "/dafd/fa", "1", "U9H930kfs", new Date(), new Date(), 41, new BigDecimal("5")));
        intArr.add(new User("3", "Dondbal", "zhen", "admin", "/dafd/fa", "5", "U9H930kfs", new Date(), new Date(), 41, new BigDecimal("18")));

//        Map<Integer, List<User>> collect = intArr.stream().collect(Collectors.groupingBy(User::getAge));
//
//        for (Map.Entry<Integer, List<User>> integerListEntry : collect.entrySet()) {
//            System.out.print(integerListEntry.getKey());
//            System.out.println(",  ," + integerListEntry.getValue());
//        }
//        List<User> y = intArr.stream().filter(p -> p.getUsername().endsWith("l")).collect(Collectors.toList());
//        List<User> y = intArr.stream().distinct().collect(Collectors.toList());

//        final List<User> y = intArr.stream().sorted(Comparator.comparing(o -> o.getUserId())).collect(Collectors.toList());
//        y.forEach(System.out::println);
        Map<String, User> map = intArr.stream().collect(Collectors.toMap(User::getPicPath, t -> t, (k1, k2) -> k2));
        for (String s : map.keySet()) {
            System.out.println(s + "==>" + map.get(s));
        }

        OptionalDouble average = intArr.stream().mapToInt(User::getAge).average();
        System.out.println(average);


    }


}
