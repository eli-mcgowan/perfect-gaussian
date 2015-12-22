package math.gaussianinteger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class Main {
	private static Logger logger = LogManager.getLogger();
	private static GaussianInteger one = new GaussianInteger(1, 0);
	
	private GaussianIntegerFactorer gaussianIntegerFactorer;
	private GaussianIntegerComparator gaussianIntegerComparator;
	
	public static void main(String[] args) {
		if(args.length != 2){
			throw new IllegalArgumentException("Start number and end number required.");
		}
		
		int startNum = Integer.parseInt(args[0]);
		int endNum = Integer.parseInt(args[1]);
		
		if(startNum < 2 || endNum < 2){
			throw new IllegalArgumentException("Start number and end number must b greater than 2");
		}
		
		if(startNum > endNum){
			throw new IllegalArgumentException("Start number must be less than or equal to end number.");
		}
		
		//Main main = new Main(new GaussianIntegerFactorer_Hibernate(new GaussianIntegerFactorer_BruteForce()));
		//Main main = new Main(new GaussianIntegerFactorer_DAO(new GaussianIntegerDivisorsSum_RAM_DAO(), new GaussianIntegerFactorer_BruteForce()));
		Main main = new Main(new GaussianIntegerFactorer_BruteForce());
		main.testGaussians(startNum, endNum);
	}
	
	public Main(GaussianIntegerFactorer gaussianIntegerFactorer) {
		this.gaussianIntegerFactorer = gaussianIntegerFactorer;
		this.gaussianIntegerComparator = new GaussianIntegerComparator();
	}
	
	public void testGaussians(int startNum, int endNum){
		long startTime = System.currentTimeMillis();
		for(long max = startNum; max <= endNum; max++){
			logger.info("Testing: {}", max);
			for(long i=0; i<max; i++){
				testGaussian(max, i);
				testGaussian(i, max);
			}
			testGaussian(max, max);
			long currentTime = System.currentTimeMillis();
			logger.info("Total time: {} / {}", + (currentTime - startTime), getNiceTime(currentTime - startTime));
		}
		long endTime = System.currentTimeMillis();
		logger.info("Total time: {} / {}", + (endTime - startTime), getNiceTime(endTime - startTime));
	}
	
	/**
	 * Uses multiple processors to during the tests. 
	 * 4 processors was about 3 times as fast as 1 but had me worried about burning out my home PC.
	 * 
	 */
	public void testGaussians_MultiCore(int startNum, int endNum){
		int numProcessors = Runtime.getRuntime().availableProcessors();
		logger.info("Using {} processors.", numProcessors);
		long startTime = System.currentTimeMillis();
		for(long max = startNum; max <= endNum; max++){ExecutorService exec = Executors.newFixedThreadPool(numProcessors);
			logger.info("Using {} processors.", numProcessors);
			logger.info("Testing: {}", max);
			final int localMax = (int)max;
			for(long i=0; i<max; i++){
				final int localI = (int)i;
				exec.submit(new Runnable(){
					@Override
					public void run() {
						testGaussian(localMax, localI);
						testGaussian(localI, localMax);
					}
				});
			}
			exec.submit(new Runnable(){
				@Override
				public void run() {
					testGaussian(localMax, localMax);
				}
			});
			testGaussian(max, max);
			exec.shutdown();
			try {
				exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
			  logger.error(e);
			}
			long currentTime = System.currentTimeMillis();
			logger.info("Total time: {} / {}", + (currentTime - startTime), getNiceTime(currentTime - startTime));
		}
		long endTime = System.currentTimeMillis();
		logger.info("Total time: {} / {}", + (endTime - startTime), getNiceTime(endTime - startTime));
	}

	public void testGaussian(long a, long b){
		findAliquotCycle(new GaussianInteger(a, b));
	}
	
	public void findPerfect(GaussianInteger gaussianInteger){
		GaussianInteger divisorsSum = gaussianIntegerFactorer.getPositiveDivisorsSum(gaussianInteger);
		// Perfect number test
		if(gaussianInteger.equals(divisorsSum)){
			logger.warn("Perfect!: {}", gaussianInteger);
			System.exit(0);
		}
	}
	
	public void findAmicable(GaussianInteger gaussianInteger){
		GaussianInteger divisorsSum = gaussianIntegerFactorer.getPositiveDivisorsSum(gaussianInteger);
		
		GaussianInteger nextSum = gaussianIntegerFactorer.getPositiveDivisorsSum(divisorsSum);
		if(gaussianInteger.equals(nextSum)){
			logger.warn("Amicable!: {}, {}", gaussianInteger, divisorsSum);
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
		logger.debug("findAliquotSequence: {} - {}", startNumber, currentNumber);
		
		GaussianInteger sum = gaussianIntegerFactorer.getPositiveDivisorsSum(currentNumber);
		
		if(gaussianIntegerComparator.compare(sum, startNumber) > 0){
			// stop searching when sum exceeds start number - this prevents trailing off into infinity
			logger.debug("stop searching - sum > startNumber: {} > {}", sum, startNumber);
			return;
		}
		if(sum.equals(startNumber)){
			// This doesn't keep track of the sequence, so this will simply be a starting point
			logger.warn("Aliquot found! {}", startNumber);
			System.exit(0);
		}
		if(sum.equals(one)){
			logger.debug("stop searching - prime in sequence: {}", startNumber);
			return;
		}
		findAliquotCycle(startNumber, sum);
	}
	
	public String getNiceTime(final long durationInMillis){
		long millis = durationInMillis % 1000;
		long durationInSeconds = durationInMillis / 1000;
		long seconds = durationInSeconds % 60;
		long durationInMinutes = durationInSeconds / 60;
		long minutes = durationInMinutes % 60;
		long hours = durationInMinutes / 60;
		StringBuilder sb = new StringBuilder();
		if(hours > 0){
			sb.append(hours).append("h ");
		}
		if(hours > 0 || minutes > 0){
			sb.append(minutes).append("m ");
		}
		if(hours > 0 || minutes > 0 || seconds > 0){
			sb.append(seconds).append("s ");
		}
		if(hours > 0 || minutes > 0 || seconds > 0 || millis > 0){
			sb.append(millis).append("millis ");
		}
		return sb.toString();
	}
}
