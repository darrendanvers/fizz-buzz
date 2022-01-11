package dev.codestijl.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

public class FizzBuzzFactoryTest {

    @Test
    public void get_divisibleByThree_returnsFizz() {

        Assert.assertTrue(FizzBuzzFactory.get(3L) instanceof FizzImpl);
        Assert.assertTrue(FizzBuzzFactory.get(6L) instanceof FizzImpl);
        Assert.assertTrue(FizzBuzzFactory.get(9L) instanceof FizzImpl);
    }

    @Test
    public void get_divisibleByFive_returnsBuzz() {

        Assert.assertTrue(FizzBuzzFactory.get(5L) instanceof BuzzImpl);
        Assert.assertTrue(FizzBuzzFactory.get(10L) instanceof BuzzImpl);
        Assert.assertTrue(FizzBuzzFactory.get(20L) instanceof BuzzImpl);
    }

    @Test
    public void get_divisibleByTreeAndFive_returnsFizzBuzz() {

        Assert.assertTrue(FizzBuzzFactory.get(15L) instanceof FizzBuzzImpl);
        Assert.assertTrue(FizzBuzzFactory.get(30L) instanceof FizzBuzzImpl);
        Assert.assertTrue(FizzBuzzFactory.get(45L) instanceof FizzBuzzImpl);
    }

    @Test
    public void get_nonFizzBuzzNumbers_returnsNumber() {

        for (long i = -1_1000; i < 1_000; i++) {
            if (!(i % 5 == 0 || i % 3 == 0)) {
                Assert.assertTrue(FizzBuzzFactory.get(i) instanceof NumberImpl);
            }
        }
    }
}
