package math.gaussianinteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GaussianIntegerFactorer_BruteForce implements GaussianIntegerFactorer {

	private static Logger logger = LogManager.getLogger();
	
	/**
	 * This main method does a simple test of a known factoring and prints the result.
	 * TODO: Move this into a test method.
	 */
	public static void main(String[] args) {
		GaussianIntegerFactorer_BruteForce factorer = new GaussianIntegerFactorer_BruteForce();

		GaussianInteger testNum = new GaussianInteger(528, 519);
		//GaussianInteger testNum = new GaussianInteger(3185, 2912);
		List<GaussianInteger> positiveDivisors = new ArrayList<GaussianInteger>(factorer.getPositiveDivisors(testNum));
		Collections.sort(positiveDivisors, new GaussianIntegerComparator());
		
		for(GaussianInteger positiveDivisor: positiveDivisors){
			logger.debug(positiveDivisor);
		}

		GaussianInteger sum = GaussianIntegerCalculator.sum(positiveDivisors);
		logger.debug("Sum: {}", sum);
		//System.out.println("Sum: " + sum);
	}

	@Override
	public Collection<GaussianInteger> getPositiveDivisors(GaussianInteger number){
		Set<GaussianInteger> positiveDivisors = new HashSet<GaussianInteger>();
		positiveDivisors.add(new GaussianInteger(1, 0));
		long maxFactor = Math.max(number.getA(), number.getB()) - 1;
		for(long i=0; i<=maxFactor; i++){
			for(long j=0; j<=maxFactor; j++){
				if(i==0 && j ==0){
					continue;
				}
				if(i==1 && j ==0){
					continue;
				}
				GaussianInteger factor1 = new GaussianInteger(i, j);
				GaussianInteger factor2 = GaussianIntegerCalculator.quotient(number, factor1);
				if(factor2 != null){
					//System.out.println(factor1 + " * " + factor2);
					if(factor1.getA() > 0 && factor1.getB() >= 0){
						positiveDivisors.add(factor1);
					}
					if(factor2.getA() > 0 && factor2.getB() >= 0){
						positiveDivisors.add(factor2);
					}
				}
			}
		}
		return positiveDivisors;
	}

	@Override
	public GaussianInteger getPositiveDivisorsSum(GaussianInteger number) {
		return GaussianIntegerCalculator.sum(getPositiveDivisors(number));
	}
}
