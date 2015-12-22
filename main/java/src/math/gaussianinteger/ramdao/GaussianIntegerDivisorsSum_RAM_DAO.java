package math.gaussianinteger.ramdao;

import java.util.HashMap;
import java.util.Map;

import math.gaussianinteger.GaussianInteger;
import math.gaussianinteger.hibernate.GaussianIntegerDivisorsSum;
import math.gaussianinteger.hibernate.GaussianIntegerDivisorsSumDAO;

public class GaussianIntegerDivisorsSum_RAM_DAO implements GaussianIntegerDivisorsSumDAO {

	private Map<GaussianInteger, GaussianInteger> sums;
	
	public GaussianIntegerDivisorsSum_RAM_DAO(){
		sums = new HashMap<GaussianInteger, GaussianInteger>();
	}
	
	
	@Override
	public GaussianIntegerDivisorsSum load(GaussianInteger gaussianInteger) {
		GaussianInteger sum = sums.get(gaussianInteger);
		if(sum == null){
			return null;
		}
		return new GaussianIntegerDivisorsSum(gaussianInteger, sum);
	}

	@Override
	public void save(GaussianIntegerDivisorsSum gaussianIntegerDivisorsSum) {
		GaussianInteger gaussianInteger = new GaussianInteger(gaussianIntegerDivisorsSum.getA(), gaussianIntegerDivisorsSum.getB());
		GaussianInteger sum = new GaussianInteger(gaussianIntegerDivisorsSum.getSumA(), gaussianIntegerDivisorsSum.getSumB());
		sums.put(gaussianInteger, sum);
	}

}
