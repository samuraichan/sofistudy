package com.sofi.java.study.Chapter1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class Chapter1Test {

    private List<Integer> numbers;

    private class Color {

        String color;

        public Color (String color) {
            this.color = color;
        }

        public boolean isBlue() {
            return "blue".equals(color);
        }
    }

    @Before
    public void setup() {
        numbers = new ArrayList<Integer>() {{
            add(3);
            add(4);
            add(7);
            add(10);
            add(99);
            add(1);
            add(33);
        }};
    }

    @Test
    public void testFilterPredicate() {
        assertEquals(4, filterListByPredicate(numbers, (x) -> x.intValue() > 1 && x.intValue() <= 10).size());
        assertEquals(3, filterListByPredicate(numbers, (x) -> x.intValue() >= 10).size());
    }

    @Test
    public void testFilterByStream() {
        assertEquals(4, filterNumbersByStream(numbers, (x) -> x.intValue() > 1 && x.intValue() <= 10).size());
        assertEquals(3, filterNumbersByStream(numbers, (x) -> x.intValue() >= 10).size());

        // Streams will handle empty references just fiune
        assertEquals(0, filterNumbersByStream(new ArrayList<Integer>(), (x) -> x.intValue() >= 10).size());

        // streams itself is not Null Proof
        assertThatThrownBy(() -> {
            filterNumbersByStream(null, (x) -> x != null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testMethodReference() {
        List<Color> colors = new ArrayList<Color>() {{
            add(new Color("blue"));
            add(new Color("orange"));
            add(new Color("blue"));
            add(new Color("green"));

        }};

        // using method reference filter the color list
        assertThat(filterListByPredicate(colors, Color::isBlue).size()).isEqualTo(2);
    }

    // own custom way of doing something similar to people().stream().filter(...)
    private <T> List<T> filterListByPredicate(List<T> col, Predicate<T> predicate) {
        List<T> temp = new ArrayList<>();

        col.forEach(
            (c) -> {
                if (predicate.test(c)) {
                    temp.add(c);
                }
            }
        );

        return temp;
    }

    private <T> List<T> filterNumbersByStream(List<T> numbers, Predicate<T> condition) {
        return (numbers.stream().filter(condition).collect(Collectors.toList()));
    }
}
