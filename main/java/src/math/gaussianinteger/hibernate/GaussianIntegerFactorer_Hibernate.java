package math.gaussianinteger.hibernate;

import java.util.Collection;

import math.gaussianinteger.GaussianInteger;
import math.gaussianinteger.GaussianIntegerFactorer;

/**
 * This implementation proved to be incredibly slow (20x slower than BruteForce alone)
 *
 */
public class GaussianIntegerFactorer_Hibernate implements GaussianIntegerFactorer {

	/**
	 * Used to get Positive Divisors and to factor values that are not in the database.
	 */
	private GaussianIntegerFactorer unPersistedGaussianIntegerFactorer;
	private GaussianIntegerDivisorsSum_HibernateDAO gaussianIntegerDivisorsSum_HibernateDAO;
	
	public GaussianIntegerFactorer_Hibernate(
			GaussianIntegerFactorer unPersistedGaussianIntegerFactorer) {
		super();
		this.unPersistedGaussianIntegerFactorer = unPersistedGaussianIntegerFactorer;
		this.gaussianIntegerDivisorsSum_HibernateDAO = new GaussianIntegerDivisorsSum_HibernateDAO();
	}

	@Override
	public Collection<GaussianInteger> getPositiveDivisors(
			GaussianInteger number) {
		return unPersistedGaussianIntegerFactorer.getPositiveDivisors(number);
	}

	@Override
	public GaussianInteger getPositiveDivisorsSum(GaussianInteger number) {
		GaussianIntegerDivisorsSum sum = gaussianIntegerDivisorsSum_HibernateDAO.load(number);
		if(sum != null){
			return new GaussianInteger(sum.getSumA(), sum.getSumB());
		}
		// Compute and save sum
		GaussianInteger newSum = unPersistedGaussianIntegerFactorer.getPositiveDivisorsSum(number);
		gaussianIntegerDivisorsSum_HibernateDAO.save(new GaussianIntegerDivisorsSum(number, newSum));
		return newSum;
	}

}
