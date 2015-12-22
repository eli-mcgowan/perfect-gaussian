package math.gaussianinteger.hibernate;

import math.gaussianinteger.GaussianInteger;

public interface GaussianIntegerDivisorsSumDAO {

	/* Method to LOAD a new GaussianIntegerDivisorsSum in the database */
	GaussianIntegerDivisorsSum load(GaussianInteger gaussianInteger);

	/* Method to SAVE a new GaussianIntegerDivisorsSum in the database */
	void save(GaussianIntegerDivisorsSum gaussianIntegerDivisorsSum);
}
