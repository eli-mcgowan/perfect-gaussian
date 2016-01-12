package math.gaussianinteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is fast and passes the limited number of tests - prove this is valid/invalid
 *
 */
public class GaussianIntegerFactorer_SquareRoot implements GaussianIntegerFactorer {

	private static Logger logger = LogManager.getLogger();
	
	/**
	 * This main method does a simple test of a known factoring and prints the result.
	 * TODO: Move this into a test method.
	 */
	public static void main(String[] args) {
		GaussianIntegerFactorer_SquareRoot factorer = new GaussianIntegerFactorer_SquareRoot();
		
		GaussianInteger testNum = new GaussianInteger(528, 519);
		//GaussianInteger testNum = new GaussianInteger(3185, 2912);
		List<GaussianInteger> positiveDivisors = new ArrayList<GaussianInteger>(factorer.getPositiveDivisors(testNum));
		Collections.sort(positiveDivisors, new GaussianIntegerComparator());
		
		for(GaussianInteger positiveDivisor: positiveDivisors){
			logger.debug(positiveDivisor);
		}

		GaussianInteger sum = GaussianIntegerCalculator.sum(positiveDivisors);
		logger.debug("Sum: {}", sum);
		System.out.println("Sum: " + sum);
	}

	@Override
	public Collection<GaussianInteger> getPositiveDivisors(GaussianInteger number){
		Set<GaussianInteger> positiveDivisors = new HashSet<GaussianInteger>();
		positiveDivisors.add(new GaussianInteger(1, 0));
		long componentSum = number.getA() + number.getB();
		long sqrtComponentSum = (long) Math.sqrt(componentSum);
		
		for(long i=0; i<=sqrtComponentSum; i++){
			for(long j=0; j<=sqrtComponentSum; j++){
				if(i==0 && j ==0){
					continue;
				}
				if(i==1 && j ==0){
					continue;
				}
				GaussianInteger factor1 = new GaussianInteger(i, j);
				GaussianInteger factor2 = GaussianIntegerCalculator.quotient(number, factor1);
				if(factor2 != null){
					if(factor1.getA() > 0 && factor1.getB() >= 0){
						positiveDivisors.add(factor1);
					}
					if(factor2.getA() > 0 && factor2.getB() >= 0){
						// This may never be true
						positiveDivisors.add(factor2);
						//System.out.println(factor1 + " / " + factor2);
					} else if(factor2.getA() > 0 && factor1.getA() > 0){
						// ex. (180 - 105i) means (105 + 180i) is a factor
						GaussianInteger factor3 = new GaussianInteger(-1 * factor2.getB(), factor2.getA());
						if(factor3.getA() > 0 && factor3.getB() >=0 ){
							positiveDivisors.add(factor3);
							//System.out.println(factor1 + " * " + factor2 + " -> " + factor3);
						}
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
