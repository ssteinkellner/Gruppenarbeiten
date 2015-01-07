package test;

import java.lang.Math;

/**
 * Created by rene on 07.01.15.
 */
public class Test {


    public static void main(String[] args) {

         double sum = 0;
         double temp =0;

        for (double i = 0; i < 50000; i++) {
            temp = Math.pow(16,i);
            temp = 1/temp;
            sum = (temp* ( 4/(8*i+1) - 2/(8*i+4) - 1/(8*i+5) - 1/(8*i+6)))+sum;
        }


        System.out.print(sum);
    }
}
