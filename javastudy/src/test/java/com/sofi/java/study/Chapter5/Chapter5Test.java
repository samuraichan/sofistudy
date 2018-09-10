package com.sofi.java.study.Chapter5;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Iterators;
import jdk.nashorn.internal.runtime.options.Option;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.rowset.spi.TransactionalWriter;

public class Chapter5Test {

    public enum DishType {
        MEAT, OTHER, FISH;
    }

    public class Dish {
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

    public class Trader {

        private String name;
        private String city;

        public Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        @Override public String toString() {
            return "Trader{" +
                   "name='" + name + '\'' +
                   ", city='" + city + '\'' +
                   '}';
        }
    }

    public class Transaction {
        private Trader trader;
        private int year;
        private int value;

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return trader;
        }

        public void setTrader(Trader trader) {
            this.trader = trader;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override public String toString() {
            return "Transaction{" +
                   "trader=" + trader +
                   ", year=" + year +
                   ", value=" + value +
                   '}';
        }
    }

    private Collection<Dish> dishes;

    private Collection<Transaction> transactions;

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

        Trader raul = new Trader("Raul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = new ArrayList<Transaction>() {{
            add(new Transaction(brian, 2011, 300));
            add(new Transaction(raul, 2012, 1000));
            add(new Transaction(raul, 2011, 400));
            add(new Transaction(mario, 2012, 710));
            add(new Transaction(mario, 2012, 700));
            add(new Transaction(alan, 2012, 950));
        }};
    }

    @Test
    public void testApplySquare() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> squares = numbers.stream().map((n) -> n=n*n).collect(Collectors.toList());

        assertThat(squares.toString()).isEqualTo("[1, 4, 9, 16, 25]");
    }

    @Test
    public void testAnyMatch() {
        assertThat(dishes.stream().anyMatch(Dish::isVegetarian)).isTrue();
    }

    @Test
    public void testAllMatchLessThanCalories() {
        assertThat(dishes.stream().allMatch((d) -> d.getCalories() < 1000)).isTrue();
    }

    @Test
    public void testNoneMatchalories() {
        assertThat(dishes.stream().noneMatch((d) -> d.getCalories() > 1000)).isTrue();
    }

    @Test
    public void testFindAny() {
        Optional<Dish> dish = dishes.stream().filter(Dish::isVegetarian).findAny();
        assertThat(dish.isPresent()).isTrue();
    }

    @Test
    public void testReduce() {
        assertThat(dishes.stream().filter(Dish::isVegetarian).count()).isEqualTo(3);

        // 'Map Reduce'
        assertThat(dishes.stream().filter(Dish::isVegetarian).map((d) -> 1).reduce(0, (a,b) -> a+b)).isEqualTo(3);
    }

    @Test
    public void testFindAllTransactions2011() {
        List<Transaction> trx = transactions.stream().filter((t) -> t.getYear() == 2011).sorted(
            (t1, t2) -> Integer.valueOf(t1.getValue()).compareTo(Integer.valueOf(t2.getValue()))
        ).collect(Collectors.toList());

        assertThat(trx.size()).isEqualTo(2);
        assertThat(trx.stream().findFirst().get().getValue()).isEqualTo(300);

    }

    @Test
    public void testTransactionUniqueCities() {
        List<String> cities = transactions.stream().map((t) -> t.getTrader().getCity()).distinct().collect(Collectors.toList());

        assertThat(cities).contains("Cambridge");
        assertThat(cities).contains("Milan");
        assertThat(cities.size()).isEqualTo(2);
    }

    @Test
    public void testFindTradersFromCambridgeSortByName() {
        List<Trader> traders = transactions.stream().filter((t) -> t.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getTrader).sorted(Comparator.comparing(Trader::getName)).distinct()
                .collect(Collectors.toList());

        assertThat(traders.size()).isEqualTo(3);
        assertThat(traders.stream().findFirst().get().getName()).isEqualTo("Alan");
    }

    @Test
    public void testAllTradersSorted() {
        
      String result = transactions.stream().map((t) -> t.getTrader().getName())
                                   .distinct().sorted().collect(Collectors.joining(" "));

      assertThat(result).isEqualTo("Alan Brian Mario Raul");
    }

    @Test
    public void testAnyTradersBasedInMilan() {
        assertThat(transactions.stream().map(Transaction::getTrader).anyMatch((tr) -> "Milan".equals(tr.getCity()))).isTrue();
    }

    @Test
    public void testPrintAllTransactionValuesFromCambridge() {
       transactions.stream().filter((t) -> "Cambridge".equals(t.getTrader().getCity())).forEach(
           (t) -> System.out.println(t.getValue())
       );
    }

    @Test
    public void testHighestValueOfAllTransaction() {
        assertThat(transactions.stream().max(Comparator.comparing(Transaction::getValue)).get().getValue()).isEqualTo(1000);

        // or using reduce

        assertThat(transactions.stream().map(Transaction::getValue).reduce(Integer::max).get()).isEqualTo(1000);

        // or using mapToInt
        assertThat(transactions.stream().mapToInt(Transaction::getValue).max().getAsInt()).isEqualTo(1000);
    }

    @Test
    public void testFindTrxWithLowestValue() {
        Transaction smallestValue = transactions.stream().min(Comparator.comparing(Transaction::getValue)).get();
        assertThat(smallestValue.getValue()).isEqualTo(300);
    }


    @Test
    public void testSumCaloriesOfMeats() {
        assertThat(dishes.stream().filter((d) -> DishType.MEAT == d.getType()).map(Dish::getCalories).reduce(Integer::sum).get()).isEqualTo(1900);

        // or using - limit cost of auto boxing

        assertThat(dishes.stream().filter((d) -> DishType.MEAT == d.getType()).mapToInt(Dish::getCalories).sum()).isEqualTo(1900);
    }

    @Test
    public void testGuavaPartition() {
        Iterators.partition(transactions.iterator(), 1000).forEachRemaining(transactions1 -> processTransactions(transactions1));
    }

    private void processTransactions(List<Transaction> transactions) {
        System.out.println("I have been called");
    }

    @Test
    public void testOptionalNull() {
        LocalDate result = null;

        // if can be null use, nullable and default to value if null
        assertThat(Optional.ofNullable(result).orElse(LocalDate.now())).isEqualTo(LocalDate.now());
    }

    @Test
    public void testFindFirstThrowsException() {
        List<String> strings = new ArrayList<>();
        strings.stream().filter((s) -> s.equals("dude")).findFirst().ifPresent(s -> System.out.println("DUDEEEEE"));
    }
}
