package test;

import com.sun.corba.se.spi.activation.Server;
import org.junit.Test;
import server.PiServer;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PiServerTest {

    @Test
    public void testPi() throws Exception {
        PiServer test = new PiServer();
        BigDecimal ret = test.pi(5);
        BigDecimal testc = new BigDecimal(3.14);
        assertEquals(testc,ret);
    }
}