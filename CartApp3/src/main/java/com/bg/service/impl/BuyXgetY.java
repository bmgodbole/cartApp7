package com.bg.service.impl;

import com.bg.model.Item;
import com.bg.service.Offer;

public class BuyXgetY extends AbsOffer {

	public int x, y;
	public String displayInfo;
	public BuyXgetY(int x , int y)
	{
		this.x=x;
		this.y=y;
		displayInfo = "Buy "+x+" Get "+y+" Free";
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
