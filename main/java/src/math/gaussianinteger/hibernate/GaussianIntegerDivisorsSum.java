package math.gaussianinteger.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import math.gaussianinteger.GaussianInteger;

@Entity
public class GaussianIntegerDivisorsSum {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long dbId;
	private long a;
	private long b;
	private long sumA;
	private long sumB;

	GaussianIntegerDivisorsSum(){
		// required for Hibernate
	}
	
	public GaussianIntegerDivisorsSum(GaussianInteger gaussianInteger, GaussianInteger sum) {
		this.a = gaussianInteger.getA();
		this.b = gaussianInteger.getB();
		this.sumA = sum.getA();
		this.sumB = sum.getB();
	}
	
	public long getDbId() {
		return dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	public long getB() {
		return b;
	}

	public void setB(long b) {
		this.b = b;
	}

	public long getSumA() {
		return sumA;
	}

	public void setSumA(long sumA) {
		this.sumA = sumA;
	}

	public long getSumB() {
		return sumB;
	}

	public void setSumB(long sumB) {
		this.sumB = sumB;
	}

	@Override
	public String toString() {
		return "GaussianIntegerDivisorsSum [dbId=" + dbId + ", a=" + a + ", b="
				+ b + ", sumA=" + sumA + ", sumB=" + sumB + "]";
	}

}
