package com.bg.service.impl;

import com.bg.model.Item;
import com.bg.service.Offer;

public class PercentageOffer extends AbsOffer {

	private int mPercentage;
	public String displayInfo;
	public PercentageOffer(int pPercentage) {
		this.mPercentage= pPercentage;
		this.displayInfo = pPercentage + "% OFF";
	}
	@Override
	public int applyOffer(Item product) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int applyOffer(int amount) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
