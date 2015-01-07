package test;

import java.math.BigDecimal;

/**
 * Created by rene on 07.01.15.
 */
public class Test {


    public static void main(String[] args) {

        int sum = 0;

        for (double i = 0; i < 10; i++) {
            if (i % 2 == 0) // if the remainder of `i/2` is 0
                sum += -1 / (2 * i - 1);
            else
                sum += 1 / (2 * i - 1);
        }


        System.out.print(sum);
    }
}
