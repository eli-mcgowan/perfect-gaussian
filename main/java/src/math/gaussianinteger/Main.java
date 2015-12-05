package math.gaussianinteger;

public class Main {

	private GaussianIntegerFactorer gaussianIntegerFactorer;
	
	public static void main(String[] args) {
		Main main = new Main(new GaussianIntegerFactorer_BruteForce());
		main.testGaussians(2, 200);
	}
	
	public Main(GaussianIntegerFactorer gaussianIntegerFactorer) {
		this.gaussianIntegerFactorer = gaussianIntegerFactorer;
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
		testGaussian(new GaussianInteger(a, b));
	}
	
	public void testGaussian(GaussianInteger gaussianInteger){
		GaussianInteger divisorsSum = gaussianIntegerFactorer.getPositiveDivisorsSum(gaussianInteger);
		
		// Perfect number test
		if(gaussianInteger.equals(divisorsSum)){
			System.out.println("Perfect!: " + gaussianInteger);
			System.exit(0);
		}
		
		// Amicable number test
		GaussianInteger nextSum = gaussianIntegerFactorer.getPositiveDivisorsSum(divisorsSum);
		if(gaussianInteger.equals(nextSum)){
			System.out.println("Amicable!: " + gaussianInteger + ", " + divisorsSum);
			System.exit(0);
		}
		
		// Aliquot Sequence test
		// TODO: Aliquot Sequence test
	}
}
