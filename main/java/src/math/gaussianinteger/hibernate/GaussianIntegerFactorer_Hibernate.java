package math.gaussianinteger.hibernate;

import math.gaussianinteger.GaussianIntegerFactorer;

/**
 * This implementation proved to be incredibly slow (20x slower than BruteForce alone)
 *
 */
public class GaussianIntegerFactorer_Hibernate extends GaussianIntegerFactorer_DAO {
	
	public GaussianIntegerFactorer_Hibernate(
			GaussianIntegerFactorer unPersistedGaussianIntegerFactorer) {
		super(new GaussianIntegerDivisorsSum_HibernateDAO(), unPersistedGaussianIntegerFactorer);
	}


}
