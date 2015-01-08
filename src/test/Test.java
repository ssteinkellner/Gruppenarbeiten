package test;

import java.math.BigDecimal;

/**
 * Created by rene on 07.01.15.
 */
public class Test {


    public static void main(String[] args) {

         double sum = 0;
         double temp =0;
         double tempsum =0;

        for (double i = 0; i < 50000; i++) {
            temp = 1/Math.pow(16,i);
            tempsum = temp* ( 4/(8*i+1) - 2/(8*i+4) - 1/(8*i+5) - 1/(8*i+6));
            sum = sum + tempsum;
        }


        System.out.println(sum);






        BigDecimal eins = new BigDecimal (1);
        BigDecimal zwei = new BigDecimal (2);
        BigDecimal vier = new BigDecimal (4);
        BigDecimal fuenf = new BigDecimal (5);
        BigDecimal sechs = new BigDecimal (6);
        BigDecimal acht = new BigDecimal (8);
        BigDecimal zehnsechs = new BigDecimal (16);
        BigDecimal sum1 = new BigDecimal (0);
        BigDecimal tempx = new BigDecimal (0);
        BigDecimal temp1 = new BigDecimal (0);
        BigDecimal temp2 = new BigDecimal (0);
        BigDecimal temp3 = new BigDecimal (0);
        BigDecimal temp4 = new BigDecimal (0);



        for (int i = 0; i < 10 ; i++) {


            BigDecimal j = new BigDecimal(i+1);
            tempx=eins.divide(zehnsechs.pow(i + 1));

            temp1=acht.multiply(j);
            temp1=temp1.add(eins);
            temp1=vier.divide(temp1,20,BigDecimal.ROUND_DOWN);

            temp2=acht.multiply(j);
            temp2=temp2.add(vier);
            temp2=zwei.divide(temp2,20,BigDecimal.ROUND_DOWN);

            temp3=acht.multiply(j);
            temp3=temp3.add(fuenf);
            temp3=eins.divide(temp3,20,BigDecimal.ROUND_DOWN);

            temp4=acht.multiply(j);
            temp4=temp4.add(sechs);
            temp4=eins.divide(temp4,20,BigDecimal.ROUND_DOWN);

            sum1 = temp1.subtract(temp2).subtract(temp3).subtract(temp4);
            sum1 = temp1.multiply(sum1);
        }
        System.out.println(sum1);





    }
}
