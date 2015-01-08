package server;

import interfaces.Calculator;
import interfaces.Server;

import java.math.BigDecimal;

public class PiServer implements Calculator, Server {


	/**
	 * @see interfaces.Calculator#pi(int)
	 */
	public BigDecimal pi(int anzahl_nachkommastellen) {

		BigDecimal sum2=new BigDecimal(0);
		int komma= 0;
		for (int i = 0; komma < anzahl_nachkommastellen; i++) {
			komma=sum2.toString().length();
			BigDecimal j = new BigDecimal(i);
			BigDecimal x = new BigDecimal(0);
			x=(new BigDecimal(1).divide(new BigDecimal(16).pow(i),anzahl_nachkommastellen,BigDecimal.ROUND_DOWN)).multiply(new BigDecimal(4).divide(new BigDecimal(8).multiply(j).add(new BigDecimal(1)),anzahl_nachkommastellen,BigDecimal.ROUND_DOWN).subtract(new BigDecimal(2).divide(new BigDecimal(8).multiply(j).add(new BigDecimal(4)),anzahl_nachkommastellen,BigDecimal.ROUND_DOWN)).subtract(new BigDecimal(1).divide(new BigDecimal(8).multiply(j).add(new BigDecimal(5)),anzahl_nachkommastellen,BigDecimal.ROUND_DOWN)).subtract(new BigDecimal(1).divide(new BigDecimal(8).multiply(j).add(new BigDecimal(6)),anzahl_nachkommastellen,BigDecimal.ROUND_DOWN)));
			sum2=x.add(sum2).setScale(anzahl_nachkommastellen,BigDecimal.ROUND_DOWN);
		}

		return sum2;
	}

}
