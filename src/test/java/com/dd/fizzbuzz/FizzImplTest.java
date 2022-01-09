package com.dd.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

public class FizzImplTest {

    @Test
    public void get_returnsFizz() {

        Assert.assertEquals("Fizz", new FizzImpl().get());
    }
}
