package com.dd;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the Configuration class.
 */
public class ConfigurationTest {

    @Test
    public void parseCommandLine_noArguments_works() {

        Configuration configuration = Configuration.builder().parseCommandLine();
        Assert.assertEquals(1, configuration.getStart());
        Assert.assertEquals(100, configuration.getEnd());
        Assert.assertFalse(configuration.isDebugEnabled());
    }

    @Test
    public void parseCommandLine_debugOnly_works() {

        String[] argArray = {"-d"};
        Configuration configuration = Configuration.builder().parseCommandLine(argArray);

        Assert.assertEquals(1, configuration.getStart());
        Assert.assertEquals(100, configuration.getEnd());
        Assert.assertTrue(configuration.isDebugEnabled());
    }

    @Test
    public void parseCommandLine_startOnly_works() {

        String[] argArray = {"-s", "-10"};
        Configuration configuration = Configuration.builder().parseCommandLine(argArray);

        Assert.assertEquals(-10, configuration.getStart());
        Assert.assertEquals(100, configuration.getEnd());
        Assert.assertFalse(configuration.isDebugEnabled());
    }

    @Test
    public void parseCommandLine_endOnly_works() {

        String[] argArray = {"-e", "10000"};
        Configuration configuration = Configuration.builder().parseCommandLine(argArray);

        Assert.assertEquals(1, configuration.getStart());
        Assert.assertEquals(10_000, configuration.getEnd());
        Assert.assertFalse(configuration.isDebugEnabled());
    }

    @Test
    public void parseCommandLine_debugAndStart_works() {

        String[] argArray = {"-d", "-s", "-15"};
        Configuration configuration = Configuration.builder().parseCommandLine(argArray);

        Assert.assertEquals(-15, configuration.getStart());
        Assert.assertEquals(100, configuration.getEnd());
        Assert.assertTrue(configuration.isDebugEnabled());
    }

    @Test
    public void parseCommandLine_debugAndEnd_works() {

        String[] argArray = {"-e", "3456", "-d"};
        Configuration configuration = Configuration.builder().parseCommandLine(argArray);

        Assert.assertEquals(1, configuration.getStart());
        Assert.assertEquals(3_456, configuration.getEnd());
        Assert.assertTrue(configuration.isDebugEnabled());
    }

    @Test
    public void parseCommandLine_printUsage_works() {

        String[] argArray = {"-e", "3456", "-d", "-h"};
        Configuration configuration = Configuration.builder().parseCommandLine(argArray);

        Assert.assertTrue(configuration.isPrintUsage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseCommandLine_debugSetTwice_throwsException() {

        String[] argArray = {"-d", "-d"};
        Configuration.builder().parseCommandLine(argArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseCommandLine_startSetTwice_throwsException() {

        String[] argArray = {"-s", "10", "-s", "100"};
        Configuration.builder().parseCommandLine(argArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseCommandLine_missingStartValue_throwsException() {

        String[] argArray = {"-s"};
        Configuration.builder().parseCommandLine(argArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseCommandLine_missingEndValue_throwsException() {

        String[] argArray = {"-e"};
        Configuration.builder().parseCommandLine(argArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseCommandLine_unknownArgument_throwsException() {

        String[] argArray = {"-s", "10", "uuu", "-e", "100"};
        Configuration.builder().parseCommandLine(argArray);
    }
}
