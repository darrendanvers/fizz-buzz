package dev.codestijl.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

public class BuzzImplTest {

    @Test
    public void get_returnsBuzz() {

        Assert.assertEquals("Buzz", new BuzzImpl().get());
    }
}
