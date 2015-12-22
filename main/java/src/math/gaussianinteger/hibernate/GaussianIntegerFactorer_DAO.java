package math.gaussianinteger.hibernate;

import java.util.Collection;

import math.gaussianinteger.GaussianInteger;
import math.gaussianinteger.GaussianIntegerFactorer;

public class GaussianIntegerFactorer_DAO implements GaussianIntegerFactorer {

	/**
	 * Used to get Positive Divisors and to factor values that are not in the database.
	 */
	private GaussianIntegerFactorer unPersistedGaussianIntegerFactorer;
	private GaussianIntegerDivisorsSumDAO gaussianIntegerDivisorsSumDAO;
	
	public GaussianIntegerFactorer_DAO(
			GaussianIntegerDivisorsSumDAO gaussianIntegerDivisorsSumDAO,
			GaussianIntegerFactorer unPersistedGaussianIntegerFactorer) {
		super();
		this.unPersistedGaussianIntegerFactorer = unPersistedGaussianIntegerFactorer;
		this.gaussianIntegerDivisorsSumDAO = gaussianIntegerDivisorsSumDAO;
	}

	@Override
	public Collection<GaussianInteger> getPositiveDivisors(
			GaussianInteger number) {
		return unPersistedGaussianIntegerFactorer.getPositiveDivisors(number);
	}

	@Override
	public GaussianInteger getPositiveDivisorsSum(GaussianInteger number) {
		GaussianIntegerDivisorsSum sum = gaussianIntegerDivisorsSumDAO.load(number);
		if(sum != null){
			return new GaussianInteger(sum.getSumA(), sum.getSumB());
		}
		// Compute and save sum
		GaussianInteger newSum = unPersistedGaussianIntegerFactorer.getPositiveDivisorsSum(number);
		gaussianIntegerDivisorsSumDAO.save(new GaussianIntegerDivisorsSum(number, newSum));
		return newSum;
	}

}
