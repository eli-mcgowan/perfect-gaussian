package math.gaussianinteger;

import java.util.Comparator;

public class GaussianIntegerComparator implements Comparator<GaussianInteger> {

	@Override
	public int compare(GaussianInteger o1, GaussianInteger o2) {
		if(o1.getA() == o2.getA()){
			return Long.compare(o1.getB(), o2.getB());
		}
		return Long.compare(o1.getA(), o2.getA());
	}

}
