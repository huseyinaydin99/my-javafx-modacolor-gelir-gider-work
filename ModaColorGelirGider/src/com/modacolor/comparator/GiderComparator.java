package com.modacolor.comparator;

import java.util.Comparator;

import com.modacolor.model.Gider;

public class GiderComparator implements Comparator<Gider> {

	@Override
	public int compare(Gider arg0, Gider arg1) {
		return arg0.getGiderID().compareTo(arg1.getGiderID());
	}

}
