package net.vanheesch.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

	@InjectMocks
	private Calculator sut;

	@Mock
	private MathFunctionProvider mathFunctionProviderStub;

	@Test
	void positiveNumbersAdded() {
		// given
		// when
		int result = sut.add(1, 3);
		// then
		assertThat(result).isEqualTo(4);
	}

	@Test
	void negativeNumbersAdded() {
		// given
		// when
		int result = sut.add(-1, -3);
		// then
		assertThat(result).isEqualTo(-4);
	}

	@Test
	void businessValueCalculated() {
		// given
		given(mathFunctionProviderStub.difficultCalculation(anyDouble())).willReturn(1.);
		// when
		double result = sut.calculateBusinessValue(1.);
		// then
		assertThat(result).isEqualTo(2.);
	}

	@Test
	void thisIsABadTestAsItDoesNotStubANonDeterministicCollaborator() {
		// given
		Calculator calculator = new Calculator(new MathFunctionProvider());
		// when
		double result = calculator.calculateBusinessValue(1.);
		// then
		assertThat(result).isEqualTo(42);
	}
}
