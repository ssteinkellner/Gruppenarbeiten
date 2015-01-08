package server;

import interfaces.CalculationBalancer;
import interfaces.Calculator;
import interfaces.Createable;
import interfaces.Server;

import java.math.BigDecimal;

public class Balancer implements Calculator, Server, CalculationBalancer {


	/**
	 * @see interfaces.CalculationBalancer#registerCalculator(Calculator)
	 */
    @Override
    public void registerCalculator(Calculator calculator) {

    }


	/**
	 * @see interfaces.CalculationBalancer#removeCalculator(Calculator)
	 */
    @Override
    public void removeCalculator(Calculator calculator) {

    }

	/**
	 * @see interfaces.Calculator#pi(int)
	 */
    @Override
    public BigDecimal pi(int anzahl_nachkommastellen) {
        return null;
    }
}
