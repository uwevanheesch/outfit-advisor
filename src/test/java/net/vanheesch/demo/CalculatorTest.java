package net.vanheesch.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

	@InjectMocks
	private Calculator sut;

	@Mock
	private MathFunctionProvider mathFunctionProviderStub;

	@Test
	void numbersAreAddedCorrectly() {
		// given
		int a = 10;
		int b = 20;
		// when
		int result = sut.add(a, b);
		// then
		assertThat(result).isEqualTo(30);
	}

	@Test
	void negativeNumbersAddedCorrectly() {
		// given
		int a = -10;
		int b = -20;
		// when
		int result = sut.add(a, b);
		// then
		assertThat(result).isEqualTo(-30);
	}

	@Test
	void businessValueCalculatedCorrectly() {
		// given
		double input = 10.;
		when(mathFunctionProviderStub.difficultCalculation(anyDouble())).thenReturn(1.);
		// when
		double result = sut.calculateBusinessValue(input);
		// then
		assertThat(result).isEqualTo(2);
	}
}
