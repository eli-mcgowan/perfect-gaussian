package math.gaussianinteger;



public class Main {

	private static GaussianInteger one = new GaussianInteger(1, 0);
	
	private GaussianIntegerFactorer gaussianIntegerFactorer;
	private GaussianIntegerComparator gaussianIntegerComparator;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Main main = new Main(new GaussianIntegerFactorer_BruteForce());
		main.testGaussians(2, 200);
		long endTime = System.currentTimeMillis();
		System.out.println("Total time: " + (endTime - startTime));
	}
	
	public Main(GaussianIntegerFactorer gaussianIntegerFactorer) {
		this.gaussianIntegerFactorer = gaussianIntegerFactorer;
		this.gaussianIntegerComparator = new GaussianIntegerComparator();
	}
	
	public void testGaussians(int startNum, int endNum){
		for(long max = startNum; max <= endNum; max++){
			System.out.println("Testing: " + max);
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
			System.out.println("Perfect!: " + gaussianInteger);
			System.exit(0);
		}
	}
	
	public void findAmicable(GaussianInteger gaussianInteger){
		GaussianInteger divisorsSum = gaussianIntegerFactorer.getPositiveDivisorsSum(gaussianInteger);
		
		GaussianInteger nextSum = gaussianIntegerFactorer.getPositiveDivisorsSum(divisorsSum);
		if(gaussianInteger.equals(nextSum)){
			System.out.println("Amicable!: " + gaussianInteger + ", " + divisorsSum);
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
		System.out.println("findAliquotSequence: " + startNumber + " - " + currentNumber);
		
		GaussianInteger sum = gaussianIntegerFactorer.getPositiveDivisorsSum(currentNumber);
		
		if(gaussianIntegerComparator.compare(sum, startNumber) > 0){
			// stop searching when sum exceeds start number - this prevents trailing off into infinity
			System.out.println("stop searching - sum > startNumber: " + sum + " > " + startNumber);
			return;
		}
		if(sum.equals(startNumber)){
			// This doesn't keep track of the sequence, so this will simply be a starting point
			System.out.println("Aliquot found! " + startNumber);
			System.exit(0);
		}
		if(sum.equals(one)){
			System.out.println("stop searching - prime in sequence: " + startNumber);
			return;
		}
		findAliquotCycle(startNumber, sum);
	}
}
