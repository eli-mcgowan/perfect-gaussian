package math.gaussianinteger;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GaussianIntegerFactorer_BruteForceTest {

	@Test
	public void testSums(){
		GaussianIntegerFactorer gaussianIntegerFactorer = new GaussianIntegerFactorer_BruteForce();
		
		assertEquals(new GaussianInteger(3183, 2912), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(3185, 2912)));
		assertEquals(new GaussianInteger(3024, 3071), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(2912, 3185)));
		assertEquals(new GaussianInteger(21, 1), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(19, 19)));
		assertEquals(new GaussianInteger(4, 3), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(5, 0)));
		assertEquals(new GaussianInteger(4, 3), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(0, 5)));
		assertEquals(new GaussianInteger(1, 0), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(1, 0)));
		assertEquals(new GaussianInteger(1, 0), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(0, 1)));
		assertEquals(new GaussianInteger(365, 364), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(100, 0)));
		assertEquals(new GaussianInteger(365, 364), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(0, 100)));
		assertEquals(new GaussianInteger(733, 516), gaussianIntegerFactorer.getPositiveDivisorsSum(new GaussianInteger(100, 100)));
	}
	
}
