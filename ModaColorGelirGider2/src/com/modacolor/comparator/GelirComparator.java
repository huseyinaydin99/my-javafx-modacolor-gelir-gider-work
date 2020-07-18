package com.modacolor.comparator;

import java.util.Comparator;

import com.modacolor.model.Gelir;

public class GelirComparator implements Comparator<Gelir> {

	@Override
	public int compare(Gelir arg0, Gelir arg1) {
		return arg0.getGelirID().compareTo(arg1.getGelirID());
	}


}
