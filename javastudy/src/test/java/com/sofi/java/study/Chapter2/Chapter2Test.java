package com.sofi.java.study.Chapter2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Chapter2Test {

    private List<Apple> apples;

    private class Apple {
        private String color;
        private Integer weight;

        public Apple(String color, Integer weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public Integer getWeight() {
            return weight;
        }
    }

    @Before
    public void setup() {
        apples = new ArrayList<Apple>() {{
            add(new Apple("green", 100));
            add(new Apple("red", 75));
            add(new Apple("orange", 125));
        }};
    }

    @Test
    public void testFilterPredicate() {

        prettyPrint(apples, new ColorPrettyPrint());
        prettyPrint(apples, Apple::getColor);

        prettyPrint(apples, new WeightPrettyPrint());
        prettyPrint(apples, (a) -> a.getWeight().toString());

        prettyPrint(apples, new CustomPretyyPrint());
        prettyPrint(apples, (a) -> {
            return String.format("%s [%s]", a.color, (a.getWeight() > 75 ? "Heavy" : "Lighg"));
        });
    }

    // own custom way of doing something similar to people().stream().filter(...)
    private void prettyPrint(List<Apple> col, PrettyPrint supplier) {
        col.forEach(
            (c) -> {
                String result = supplier.print(c);
                System.out.println(result);
            }
        );
    }

    // new school way, define functional interface then use lambda expressions OR parameterize method references
    @FunctionalInterface
    private interface PrettyPrint {
        String print(Apple apple);
    }

    // old school way below
    private class ColorPrettyPrint implements PrettyPrint {
        @Override public String print(Apple apple) {
            return apple.getColor();
        }
    }

    private class WeightPrettyPrint implements PrettyPrint {
        @Override public String print(Apple apple) {
            return apple.getWeight().toString();
        }
    }

    private class CustomPretyyPrint implements PrettyPrint {
        @Override public String print(Apple a) {
            return String.format("%s [%s]", a.color, (a.getWeight() > 75 ? "Heavy" : "Lighg"));
        }
    }
}
