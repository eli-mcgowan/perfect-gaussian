package math.gaussianinteger;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GaussianIntegerCalculatorTest {

	@Test
	public void simpleSum() {
		GaussianInteger term1 = new GaussianInteger(5, 2);
		GaussianInteger term2 = new GaussianInteger(7, 12);

		GaussianInteger expected = new GaussianInteger(12, 14);
		assertEquals(expected, GaussianIntegerCalculator.sum(term1, term2));
	}

	@Test
	public void simpleProduct() {
		GaussianInteger term1 = new GaussianInteger(4, 5);
		GaussianInteger term2 = new GaussianInteger(3, 2);

		GaussianInteger expected = new GaussianInteger(2, 23);
		assertEquals(expected, GaussianIntegerCalculator.product(term1, term2));
	}

	@Test
	public void simpleDivision() {
		GaussianInteger numerator = new GaussianInteger(2, 23);
		GaussianInteger denominator = new GaussianInteger(3, 2);

		GaussianInteger expected = new GaussianInteger(4, 5);
		assertEquals(expected,
				GaussianIntegerCalculator.quotient(numerator, denominator));
	}

	@Test
	public void failedIntegerDivision() {
		GaussianInteger numerator = new GaussianInteger(3, 5);
		GaussianInteger denominator = new GaussianInteger(2, 6);

		assertEquals(null,
				GaussianIntegerCalculator.quotient(numerator, denominator));
	}

}
