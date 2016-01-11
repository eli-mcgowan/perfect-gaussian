package math.gaussianinteger;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GaussianIntegerFactorer_BruteForceTest {

	private GaussianIntegerFactorer gaussianIntegerFactorer;
	private GaussianInteger gaussianInteger;
	private GaussianInteger sum;
	
	
	 @Parameters
	   public static Iterable<Object[]> data()  {
	      return GaussianIntegerFactorerTestData.data;
	   }
	   
	public GaussianIntegerFactorer_BruteForceTest(GaussianInteger sum, GaussianInteger gaussianInteger) {
		this.gaussianInteger = gaussianInteger;
		this.sum = sum;
	}

	@Test
	public void testSums() {
		assertEquals(sum, gaussianIntegerFactorer.getPositiveDivisorsSum(gaussianInteger));
	}

	@Before
	public void setUp() {
		gaussianIntegerFactorer = new GaussianIntegerFactorer_BruteForce();
	}
}
