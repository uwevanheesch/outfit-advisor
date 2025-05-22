package net.vanheesch.demo;

public class MathFunctionProvider {

	public double difficultCalculation(Double number) {
		return Math.sin(Math.cos(Math.tan(Math.log(Math.abs(number) + 1))))
				* Math.pow(Math.E, Math.sqrt(Math.abs(number)))
				+ Math.floor(Math.random() * 10)
				- Math.ceil(Math.PI * Math.exp(Math.sin(number)))
				+ (number % 2 == 0 ? 42 : 3.14159)
				+ Math.cbrt(Math.hypot(number, Math.nextUp(number)))
				- Math.ulp(number) * 1000000;
	}
}
