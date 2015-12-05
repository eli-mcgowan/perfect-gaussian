package math.gaussianinteger;

import java.util.Collection;

public class GaussianIntegerCalculator {

	public static GaussianInteger sum(GaussianInteger int1, GaussianInteger int2){
		return new GaussianInteger(int1.getA() + int2.getA(), int1.getB() + int2.getB());
	}
	
	public static GaussianInteger sum(Collection<GaussianInteger> gaussianIntegers){
		int a = 0;
		int b = 0;
		for(GaussianInteger factor: gaussianIntegers){
			a += factor.getA();
			b += factor.getB();
		}
		return new GaussianInteger(a, b);
	}
	
	public static GaussianInteger product(GaussianInteger int1, GaussianInteger int2){
		long a = (int1.getA() * int2.getA()) - (int1.getB() * int2.getB());
		long b = (int1.getA() * int2.getB()) + (int1.getB() * int2.getA());
		return new GaussianInteger(a, b);
	}
	
	/**
	 * Returns null if this does not result in an integer
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static GaussianInteger quotient(GaussianInteger numerator, GaussianInteger denominator){
		GaussianInteger conjugate = new GaussianInteger(denominator.getA(), -1 * denominator.getB());
		
		GaussianInteger numeratorConjugate = product(numerator, conjugate);
		GaussianInteger denominatorConjugate = product(denominator, conjugate);
		
		if(denominatorConjugate.getB() != 0){
			throw new RuntimeException("Error computing quotient. denominatorConjugate should have no imaginary component");
		}
		
		GaussianInteger quotient = new GaussianInteger((numeratorConjugate.getA() / denominatorConjugate.getA()), (numeratorConjugate.getB() / denominatorConjugate.getA()));
		
		if(! numerator.equals(product(denominator, quotient))){
			return null;
		}
		
		return quotient;
	}
	
}
