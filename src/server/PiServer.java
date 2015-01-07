package server;

import interfaces.Calculator;
import interfaces.Createable;

import java.math.BigDecimal;

public class PiServer implements Calculator, Createable {


	/**
	 * @see interfaces.Calculator#pi(int)
	 */
	public BigDecimal pi(int anzahl_nachkommastellen) {

		BigDecimal sum = new BigDecimal (0);
		for(int i=0; i<anzahl_nachkommastellen; i++){
			BigDecimal bd = new BigDecimal (i);
			BigDecimal two = new BigDecimal (2);
			BigDecimal one = new BigDecimal (1);
			BigDecimal temp = new BigDecimal (0);
			BigDecimal minone = new BigDecimal (-1);

			if(i%2 == 0) { // if the remainder of `i/2` is 0

				temp = bd.multiply(two);
				temp = temp.subtract(one);
				temp = minone.divide(temp);
				sum = sum.add(temp);

			}
			else {

				temp = bd.multiply(two);
				temp = temp.subtract(one);
				temp = one.divide(temp);
				sum = sum.add(temp);

			}
		}
	return sum;
	}

}
