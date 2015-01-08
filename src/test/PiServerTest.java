package test;

import server.PiServer;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PiServerTest {



    public void testPi() throws Exception {
        PiServer test = new PiServer();
        BigDecimal ret = test.pi(5);
        BigDecimal testc = new BigDecimal(3.14);
        assertEquals(testc,ret);
    }
}