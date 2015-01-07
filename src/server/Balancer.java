package server;

import interfaces.CalculationBalancer;
import interfaces.Calculator;
import interfaces.Createable;

import java.math.BigDecimal;

public class Balancer implements Calculator, Createable, CalculationBalancer {

    @Override
    public void registerCalculator(Calculator calculator) {

    }

    @Override
    public void remove() {

    }

    @Override
    public BigDecimal pi(int anzahl_nachkommastellen) {
        return null;
    }
}
