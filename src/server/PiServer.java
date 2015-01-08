package server;

import interfaces.Calculator;
import interfaces.Createable;

import java.math.BigDecimal;

public class PiServer implements Calculator, Createable {


	/**
	 * @see interfaces.Calculator#pi(int)
	 */
	public BigDecimal pi(int anzahl_nachkommastellen) {
		BigDecimal two = new BigDecimal (2);
		BigDecimal one = new BigDecimal (1);
		BigDecimal minone = new BigDecimal (-1);
		BigDecimal temp, bd;


		BigDecimal sum = new BigDecimal (0);
		for(int i=0; i<anzahl_nachkommastellen; i++){
			bd = new BigDecimal (i);
			temp = new BigDecimal(0);

			temp = bd.multiply(two);
			temp = temp.subtract(one);
			if(i%2 == 0) {
				temp = minone.divide(temp);
			} else {
				temp = one.divide(temp);
			}
			sum = sum.add(temp);
		}
		return sum;
	}

}
