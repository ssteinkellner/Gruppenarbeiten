package test;

import org.junit.Test;
import server.PiServer;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PiServerTest {


    @Test
    public void testPiKommazeichen() throws Exception {
        PiServer test = new PiServer();
        BigDecimal ret = test.pi(5);
        // plus 2 weil erste stelle und komma dazugezählt werden
        assertEquals(5+2,ret.toString().length());
    }

}