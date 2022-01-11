package dev.codestijl.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

public class NumberImplTest {

    @Test
    public void get_smallNumber_returnsNumber() {

        Assert.assertEquals("1", NumberImpl.of(1).get());
    }

    @Test
    public void get_bigNumber_returnsNumber() {

        Assert.assertEquals("145,203", NumberImpl.of(145_203).get());
    }

    @Test
    public void get_zero_returnsNumber() {

        Assert.assertEquals("0", NumberImpl.of(0).get());
    }

    @Test
    public void get_bigNegativeNumber_returnsNumber() {

        Assert.assertEquals("-159,392,456", NumberImpl.of(-159_392_456).get());
    }

    @Test
    public void get_smallNegativeNumber_returnsNumber() {

        Assert.assertEquals("-2", NumberImpl.of(-2).get());
    }
}
