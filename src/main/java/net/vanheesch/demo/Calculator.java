package net.vanheesch.demo;

public class Calculator {

	private final MathFunctionProvider mathFunctionProvider;

	public Calculator(MathFunctionProvider mathFunctionProvider) {
		this.mathFunctionProvider = mathFunctionProvider;
	}

	public int add(int a, int b) {
		return a + b;
	}

	public double calculateBusinessValue(Double effort) {
		double base = mathFunctionProvider.difficultCalculation(effort);
		return base + 1;
	}


}
