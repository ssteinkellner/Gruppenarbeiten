package test;

import java.math.BigDecimal;

public class PiTest {
	private static BigDecimal eins, zwei, vier, fuenf, sechs, acht, zehnsechs;

	public static void main(String[] args){
		eins = new BigDecimal (1);
		zwei = new BigDecimal (2);
		vier = new BigDecimal (4);
		fuenf = new BigDecimal (5);
		sechs = new BigDecimal (6);
		acht = new BigDecimal (8);
		zehnsechs = new BigDecimal (16);
		
		BigDecimal sum = new BigDecimal (0);
		BigDecimal tempsum, index;
		BigDecimal temp0, temp1, temp2, temp3, temp4;

		for (int i = 0; i < 10 ; i++) {

			index = new BigDecimal(i+1);
			temp0 = eins.divide(zehnsechs.pow(i + 1));
			temp1 = bruch(index, vier, eins);
			temp2 = bruch(index, zwei, vier);
			temp3 = bruch(index, eins, fuenf);
			temp4 = bruch(index, eins, sechs);
			
			tempsum = temp1.subtract(temp2).subtract(temp3).subtract(temp4);
			tempsum = temp0.multiply(tempsum);
			sum = sum.add(tempsum);
			System.out.println(tempsum);
		}
		System.out.println(sum);

	}
	
	private static BigDecimal bruch(BigDecimal index, BigDecimal top, BigDecimal bottom){
		BigDecimal temp = acht.multiply(index).add(bottom);
		
		return top.divide(temp, BigDecimal.ROUND_DOWN);
	}
}
