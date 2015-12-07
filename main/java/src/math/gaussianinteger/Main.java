package math.gaussianinteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class Main {
	private static Logger logger = LogManager.getLogger();
	private static GaussianInteger one = new GaussianInteger(1, 0);
	
	private GaussianIntegerFactorer gaussianIntegerFactorer;
	private GaussianIntegerComparator gaussianIntegerComparator;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Main main = new Main(new GaussianIntegerFactorer_BruteForce());
		main.testGaussians(2, 200);
		long endTime = System.currentTimeMillis();
		logger.info("Total time: " + (endTime - startTime));
	}
	
	public Main(GaussianIntegerFactorer gaussianIntegerFactorer) {
		this.gaussianIntegerFactorer = gaussianIntegerFactorer;
		this.gaussianIntegerComparator = new GaussianIntegerComparator();
	}
	
	public void testGaussians(int startNum, int endNum){
		for(long max = startNum; max <= endNum; max++){
			logger.info("Testing: " + max);
			for(long i=0; i<max; i++){
				testGaussian(max, i);
				testGaussian(i, max);
			}
			testGaussian(max, max);
		}
	}

	public void testGaussian(long a, long b){
		findAliquotCycle(new GaussianInteger(a, b));
	}
	
	public void findPerfect(GaussianInteger gaussianInteger){
		GaussianInteger divisorsSum = gaussianIntegerFactorer.getPositiveDivisorsSum(gaussianInteger);
		// Perfect number test
		if(gaussianInteger.equals(divisorsSum)){
			logger.warn("Perfect!: " + gaussianInteger);
			System.exit(0);
		}
	}
	
	public void findAmicable(GaussianInteger gaussianInteger){
		GaussianInteger divisorsSum = gaussianIntegerFactorer.getPositiveDivisorsSum(gaussianInteger);
		
		GaussianInteger nextSum = gaussianIntegerFactorer.getPositiveDivisorsSum(divisorsSum);
		if(gaussianInteger.equals(nextSum)){
			logger.warn("Amicable!: " + gaussianInteger + ", " + divisorsSum);
			System.exit(0);
		}
	}
	
	/**
	 * Also finds perfect numbers and amicable numbers.
	 */
	public void findAliquotCycle(GaussianInteger gaussianInteger){
		findAliquotCycle(gaussianInteger, gaussianInteger);
	}
	
	/**
	 * Also finds perfect numbers and amicable numbers.
	 */
	public void findAliquotCycle(GaussianInteger startNumber, GaussianInteger currentNumber){
		logger.debug("findAliquotSequence: " + startNumber + " - " + currentNumber);
		
		GaussianInteger sum = gaussianIntegerFactorer.getPositiveDivisorsSum(currentNumber);
		
		if(gaussianIntegerComparator.compare(sum, startNumber) > 0){
			// stop searching when sum exceeds start number - this prevents trailing off into infinity
			logger.debug("stop searching - sum > startNumber: " + sum + " > " + startNumber);
			return;
		}
		if(sum.equals(startNumber)){
			// This doesn't keep track of the sequence, so this will simply be a starting point
			logger.warn("Aliquot found! " + startNumber);
			System.exit(0);
		}
		if(sum.equals(one)){
			logger.debug("stop searching - prime in sequence: " + startNumber);
			return;
		}
		findAliquotCycle(startNumber, sum);
	}
}
