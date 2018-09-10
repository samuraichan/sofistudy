package com.sofi.java.study.Chapter4;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;

public class Chapter4Test {

    private enum DishType {
        MEAT, OTHER, FISH;
    }

    private class Dish {
        private String name;
        private Integer calories;
        private boolean vegetarian;
        private DishType type;

        public Dish(String name, Integer calories, boolean vegetarian, DishType type) {
            this.name = name;
            this.calories = calories;
            this.vegetarian = vegetarian;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCalories() {
            return calories;
        }

        public void setCalories(Integer calories) {
            this.calories = calories;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public void setVegetarian(boolean vegetarian) {
            this.vegetarian = vegetarian;
        }

        public DishType getType() {
            return type;
        }

        public void setType(DishType type) {
            this.type = type;
        }

        @Override public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Dish)) {
                return false;
            }

            Dish dish = (Dish)o;

            if (isVegetarian() != dish.isVegetarian()) {
                return false;
            }
            return getType() == dish.getType();
        }

        @Override public int hashCode() {
            int result = (isVegetarian() ? 1 : 0);
            result = 31 * result + getType().hashCode();
            return result;
        }
    }

    private Collection<Dish> dishes;

    @Before
    public void setup() {
        dishes = new ArrayList<Dish>() {{
            add(new Dish("port", 800, false, DishType.MEAT));
            add(new Dish("beef", 700, false, DishType.MEAT));
            add(new Dish("chichen", 400, false, DishType.MEAT));
            add(new Dish("french fries", 530, true, DishType.OTHER));
            add(new Dish("rice", 350, true, DishType.OTHER));
            add(new Dish("pizza", 550, true, DishType.OTHER));
            add(new Dish("prawns", 300, false, DishType.FISH));
            add(new Dish("salmon", 450, false, DishType.FISH));
        }};
    }

    @Test
    public void testHighCalorieDish() {
        List<String> dishNames = dishes.stream().filter((d) -> d.getCalories() > 300)
                                       .map(Dish::getName).collect(Collectors.toList());
        assertThat(dishNames.toString()).isEqualTo("[port, beef, chichen, french fries, rice, pizza, salmon]");
    }

    @Test
    public void testHighCalorieDishWithLimit() {
        final int ceiling = 5;

        List<String> dishNames = dishes.stream().filter((d) -> d.getCalories() > 300).limit(ceiling)
                                       .map(Dish::getName).collect(Collectors.toList());
        assertThat(dishNames.toString()).isEqualTo("[port, beef, chichen, french fries, rice]");
        assertThat(dishNames.size()).isEqualTo(ceiling);
    }

    @Test
    public void testFilterOnlyVegetarian() {
        assertThat(
            dishes.stream().filter(Dish::isVegetarian).count()
        ).isEqualTo(3);
    }

    @Test
    public void testFilterDistinct() {
        List<String> filtered = dishes.stream().filter(Dish::isVegetarian).distinct().map(Dish::getName).collect(Collectors.toList());

        // do you know why this is a french fry?
        // it is because I wrote a bad equals() and hash() methods for Dish that does NOT
        // guarantee a unique hash key
        assertThat(filtered.size()).isEqualTo(1);
        assertThat(filtered.stream().findFirst().get()).isEqualTo("french fries");
    }

    @Test
    public void testStreamReturnDishNameLength() {
        List<Integer> nameLengths = dishes.stream().map((d) -> d.getName().length()).collect(Collectors.toList());
        assertThat(nameLengths.toString()).isEqualTo("[4, 4, 7, 12, 4, 5, 6, 6]");

        // OR you an use two pipelined 'maps'
        nameLengths = dishes.stream().map(Dish::getName).map((name) -> name.length()).collect(Collectors.toList());
        assertThat(nameLengths.toString()).isEqualTo("[4, 4, 7, 12, 4, 5, 6, 6]");
    }

    

}
