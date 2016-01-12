package math.gaussianinteger;

import java.util.Random;

public class TestGenerator {

	GaussianIntegerFactorer gaussianIntegerFactorer;
	
	public static void main(String[] args) {
		TestGenerator testGenerator = new TestGenerator();
		testGenerator.createTests(100, 1000);
	}
	
	public void createTests(int numTests, int maxValue){
		gaussianIntegerFactorer = new GaussianIntegerFactorer_BruteForce();
		Random rand = new Random();
		
		for(int i=0; i<numTests; i++){
			int a = rand.nextInt(maxValue + 1);
			int b = rand.nextInt(maxValue + 1);
			if(a == 0 && b == 0){
				i--;
			} else{
				test(a, b);
			}
		}
	}
	
	public void test(int a, int b){
		
		GaussianInteger gaussian = new GaussianInteger(a, b);
		GaussianInteger sum = gaussianIntegerFactorer.getPositiveDivisorsSum(gaussian);
		
		System.out.println("{ new GaussianInteger(" + sum.getA() + ", " + sum.getB() + 
				"), new GaussianInteger(" + gaussian.getA() + ", " + gaussian.getB() + ") },");
		// output format: 
		//{ new GaussianInteger(3183, 2912), new GaussianInteger(3185, 2912) },
	}
}
