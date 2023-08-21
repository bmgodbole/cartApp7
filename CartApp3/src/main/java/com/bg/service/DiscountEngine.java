package com.bg.service;

import java.util.ArrayList;

import com.bg.model.CartItem;
import com.bg.service.impl.AbsOffer;

public class DiscountEngine {
public double applydiscounts (ArrayList<CartItem> cart )
{
	double discountTotal = 0;
	ArrayList<AbsOffer> Offers = null;
	for(CartItem c:cart )
	{
	
		for(AbsOffer offer:Offers)
		{
			
		}
	}
		
	return discountTotal;
}
}
