package com.bg.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bg.connection.DbCon;
import com.bg.dao.DiscountItemDao;
import com.bg.model.Item;
import com.bg.service.Offer;

public abstract class AbsOffer implements Offer {
     
	public ArrayList<String> displayOffer(int pItemId) {
		ArrayList<String> descList=new ArrayList<String>();
		try {
			descList = new DiscountItemDao(DbCon.getConnection()).getOfferDesc(pItemId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descList;
		
	}

	public int applyOffer(Item product) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int applyOffer(int amount) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
