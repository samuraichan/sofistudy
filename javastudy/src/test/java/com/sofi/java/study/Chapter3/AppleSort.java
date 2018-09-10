package com.sofi.java.study.Chapter3;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppleSort {

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
    public void setUp() {
        apples = new ArrayList<Apple>() {{
            add(new Apple("Green", 100));
            add(new Apple("Blue", 78));
            add(new Apple("Orange", 20));
            add(new Apple("Yellow", 200));
        }};
    }

    @Test
    public void testOldSchoolComparator() {
        Comparator<Apple> comparator = new Comparator<Apple>() {
            @Override public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        };

        apples.sort(comparator);
        
        assertEquals(apples.stream().findFirst().get().getWeight(), Integer.valueOf(20));
    }

    @Test
    public void testNewComparator() {
        Comparator<Apple> comparator = Comparator.comparingInt(Apple::getWeight);
        apples.sort(comparator);
        assertEquals(apples.stream().findFirst().get().getWeight(), Integer.valueOf(20));

        comparator = Comparator.comparing(Apple::getWeight);
        apples.sort(comparator);
        assertEquals(apples.stream().findFirst().get().getWeight(), Integer.valueOf(20));

        comparator = Comparator.comparing(Apple::getWeight);
        apples.sort(comparator.reversed());
        assertEquals(apples.stream().findFirst().get().getWeight(), Integer.valueOf(200));

        // sort on color and NOT weight
        comparator = Comparator.comparing(Apple::getColor);
        apples.sort(comparator);
        assertEquals(apples.stream().findFirst().get().getWeight(), Integer.valueOf(78));

        comparator = (apple1, apple2) -> (apple1.getColor().compareTo(apple2.getColor()));
        apples.sort(comparator);
        assertEquals(apples.stream().findFirst().get().getWeight(), Integer.valueOf(78));

    }


    //private Comparator<Apple> compare
}
