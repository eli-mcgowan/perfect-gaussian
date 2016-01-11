package math.gaussianinteger;

import java.util.Arrays;

public interface GaussianIntegerFactorerTestData {

	public Iterable<Object[]> data = Arrays.asList(new Object[][] {
			{ new GaussianInteger(3183, 2912), new GaussianInteger(3185, 2912) },
			{ new GaussianInteger(3024, 3071), new GaussianInteger(2912, 3185) },
			{ new GaussianInteger(21, 1), new GaussianInteger(19, 19) },
			{ new GaussianInteger(4, 3), new GaussianInteger(5, 0) },
			{ new GaussianInteger(4, 3), new GaussianInteger(0, 5) },
			{ new GaussianInteger(1, 0), new GaussianInteger(1, 0) },
			{ new GaussianInteger(1, 0), new GaussianInteger(0, 1) },
			{ new GaussianInteger(365, 364), new GaussianInteger(100, 0) },
			{ new GaussianInteger(365, 364), new GaussianInteger(0, 100) },
			{ new GaussianInteger(733, 516), new GaussianInteger(100, 100) },
			{ new GaussianInteger(11, 7), new GaussianInteger(8, 2) },
	      });
}
