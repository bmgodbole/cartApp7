package com.bg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bg.model.DiscountItem;
import com.bg.service.Offer;
import com.bg.service.impl.AbsOffer;
import com.bg.service.impl.BuyXgetY;
import com.bg.service.impl.PercentageOffer;

public class DiscountItemDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public DiscountItemDao(Connection con)
	{
		this.con = con;
	}

   public DiscountItem getItemDiscountObj(int pid) 
   {
	   DiscountItem discitem = null;
	   query = "Select * from item_discount where prod_d_id = ?";
	   try {
			pst = this.con.prepareStatement(query);
		    pst.setInt(1, pid);
		    rs=pst.executeQuery();
		
		while(rs.next())
		{
			discitem = new DiscountItem();
			discitem.setDisc_id(rs.getInt("disc_id"));
			discitem.setProd_d_id(rs.getInt("prod_d_id"));
			discitem.setDiscount_val(rs.getInt("discount_val"));
			discitem.setDiscount_percent(rs.getInt("discount_percent"));
			if(rs.getDate("valid_until")!=null)
			{
		      discitem.setValid_until(rs.getDate("valid_until").toString());
			}
		}
		
		
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   
	return discitem;
	   
   }
   public Offer getOffer()
   {
	return null;
	   
   }
   
   public ArrayList<String> getOfferDesc(int pId)
   {
	   ArrayList<String> offerDesc = new ArrayList<String>() ;
		query = "Select disc_desc from item_discount where prod_d_id = ?";
		try {
			pst = this.con.prepareStatement(query);
		    pst.setInt(1, pId);
		    rs=pst.executeQuery();
		    while(rs.next())
			{
		        String desc = rs.getString("disc_desc").toString();
		        offerDesc.add(desc);
			 }
			
		
         } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return offerDesc;
	   
   }
   
   public ArrayList<AbsOffer> getOffers(int pId)
   {
	ArrayList<AbsOffer> itemOffers = new ArrayList<AbsOffer>() ;
	query = "Select * from item_discount where prod_d_id = ?";
	AbsOffer itemOffer=null;
	try {
		pst = this.con.prepareStatement(query);
	    pst.setInt(1, pId);
	    rs=pst.executeQuery();
	
	    while(rs.next())
		{
		    String oft = rs.getString("discount_category").toString();
		//	offerTypes.add(oft);
		   switch(oft)
		   {
		   case "PERCENTAGE":
		    itemOffer = new PercentageOffer(rs.getInt("ATTR1"));
		   itemOffers.add(itemOffer);
		   break;
		   case "BUYXGETY" :
			itemOffer = new BuyXgetY(rs.getInt("ATTR1"),rs.getInt("ATTR2"));
			itemOffers.add(itemOffer);
		   break;
		   
		   default:
		   break;
		   
		   }
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return itemOffers;
	   
   }
}
