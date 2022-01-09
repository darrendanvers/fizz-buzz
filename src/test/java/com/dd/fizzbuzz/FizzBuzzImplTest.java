package com.dd.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

public class FizzBuzzImplTest {

    @Test
    public void get_returnsFizzBuzz() {

        Assert.assertEquals("FizzBuzz", new FizzBuzzImpl().get());
    }
}
