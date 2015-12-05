package math.gaussianinteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GaussianIntegerFactorer_BruteForce implements GaussianIntegerFactorer {
	
	/**
	 * This main method does a simple test of a known factoring and prints the result.
	 * TODO: Move this into a test method.
	 */
	public static void main(String[] args) {
		GaussianIntegerFactorer_BruteForce factorer = new GaussianIntegerFactorer_BruteForce();
		factorer.getPositiveDivisors(new GaussianInteger(3185, 2912));
		
		List<GaussianInteger> positiveDivisors = new ArrayList<GaussianInteger>(factorer.getPositiveDivisors(new GaussianInteger(3185, 2912)));
		Collections.sort(positiveDivisors, new GaussianIntegerComparator());
		
		for(GaussianInteger positiveDivisor: positiveDivisors){
			System.out.println(positiveDivisor);
		}
		
		GaussianInteger sum = factorer.getPositiveDivisorsSum(new GaussianInteger(3185, 2912));
		System.out.println("Sum: " + sum);
	}
	
	public Collection<GaussianInteger> getPositiveDivisors(GaussianInteger number){
		Set<GaussianInteger> positiveDivisors = new HashSet<GaussianInteger>();
		positiveDivisors.add(new GaussianInteger(1, 0));
		long maxFactor = Math.max(number.getA(), number.getB()) - 1;
		for(long i=1; i<=maxFactor; i++){
			for(long j=1; j<=maxFactor; j++){
				GaussianInteger factor1 = new GaussianInteger(i, j);
				GaussianInteger factor2 = GaussianIntegerCalculator.quotient(number, factor1);
				if(factor2 != null){
					if(factor1.getA() >= 0 && factor1.getB() >= 0){
						positiveDivisors.add(factor1);
					}
					if(factor2.getA() >= 0 && factor2.getB() >= 0){
						positiveDivisors.add(factor2);
					}
				}
			}
		}
		return positiveDivisors;
	}
	
	private GaussianInteger getPositiveDivisorsSum(GaussianInteger number){
		int a = 0;
		int b = 0;
		for(GaussianInteger factor: getPositiveDivisors(number)){
			a += factor.getA();
			b += factor.getB();
		}
		return new GaussianInteger(a, b);
	}
}
