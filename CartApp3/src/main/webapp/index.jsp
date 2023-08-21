
<%@page import="com.bg.connection.DbCon"%>
<%@page import="com.bg.dao.ItemDao,com.bg.model.Item"%>
<%@page import="java.util.*,com.bg.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="com.bg.model.User,com.bg.service.impl.AbsOffer" %>
    <%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth!=null)
    {
    	request.setAttribute("auth", auth);
    }
    ItemDao ido = new ItemDao(DbCon.getConnection());
    List<Item> items = ido.getAllItems();
    ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
    if (cart_list != null) {
    	request.setAttribute("cart_list", cart_list);
    }
   
    %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to shopping cart</title>
<%@include file="includes/head.jsp" %>
</head>
<body>

<%@include file="includes/navbar.jsp" %>

<div class = "container">
<div class="card-header my-3"> All Products </div>
<div class = "row">
    <% if(!items.isEmpty())
    {
    	for(Item i:items)
    	{
    			%>
    	<div class = "col-md-3 my-3">
        <div class="card w-100" style="width:18rem;"> 
           <img class="card-img-top" src=<%=i.getImg_path() %> alt="Card image cap" width="40" height="100">
           <div class="card-body">
              	<h5 class="card-title"><%=i.getItem_name() %> </h5>
  				<h6 class="price"> Price: $<%=i.getPrice() %> </h6>
				<%if (i.getDiscOffers() != null) { 
					 ArrayList<AbsOffer> ofrs = i.getDiscOffers();
				     for(AbsOffer j:ofrs){ 
				     ArrayList <String> ks = j.displayOffer(i.getItem_id()); 
				     for (String k: ks) {%>
				<h6 class="price">Hurry !! Limited Offer <%= k %></h6>
				<%}}} %>
				
				<p class="card-text">  </p>
				<div class="mt-3 d-flex justify-contect-between">
				<a href="add-to-cart?id=<%=i.getItem_id() %>" class="btn btn-dark my-2">AddtoCart</a> 
		        <a href="order-now?id=<%=i.getItem_id() %>" class="btn btn-primary my-2">BuyNow</a>
				
				</div>
           
           </div>
        </div>
    </div>	
    	
    	<% }}%>
   
   
    
</div> 


<%@include file="includes/footer.jsp" %>
</body>
</html>