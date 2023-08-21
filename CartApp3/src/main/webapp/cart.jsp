<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.bg.service.impl.AbsOffer,com.bg.connection.DbCon,com.bg.model.User,java.util.ArrayList,com.bg.model.CartItem,java.util.List,com.bg.dao.ItemDao" %>
    <%User auth = (User) request.getSession().getAttribute("auth");
    if(auth!=null)
    {
    	request.setAttribute("auth", auth);
    }
    ArrayList<CartItem> cartList = (ArrayList<CartItem>)session.getAttribute("cart-list");
    List<CartItem> cartProducts = null;
    if(cartList != null)
    {
    	ItemDao ido = new ItemDao(DbCon.getConnection());
    	cartProducts = ido.getCartProducts(cartList);
    //	out.print(cartList);
    	//out.print(cartProducts);
    	request.setAttribute("cart_list", cartProducts);
    	session.setAttribute("cart-list", cartProducts);
        int sum = ido.getCartTotal(new ArrayList<CartItem>(cartProducts));
        request.setAttribute("cartTotal", sum);
    }
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<%@include file="includes/head.jsp" %>
<style type="text/css">
.table tbody td
{
   vertical-align:middle;
   }
   .btn-incre , .btn-decre
   {
   box-shadow:none;
   font-size:25px;
   }
 </style>
</head>
<body>
<%@include file="/includes/navbar.jsp"%>
<div class = "container">
<div class="d-flex py-3"><h3>Total Price :$ ${(cartTotal>0)?cartTotal:0}</h3> 
<a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a>
</div>
<table class="table table-light">
<thead>
<tr>
<th scope="col"> Name </th>
<th scope="col"> Unit Price </th>
<th scope="col"> Total Price </th>
<th scope="col"> Discount </th>
<th scope="col"> Buy Now </th>
<th scope="col"> Cancel </th>
</tr> </thead>
<tbody>

<% if(cartProducts != null){
    for(CartItem c:cartProducts)
    { %>
       <tr>
<td> <%=c.getItem_name() %> </td>
<td><%=c.getPrice() %> </td>
<td><%=c.getTotal_price() %></td>
<td><%if (c.getDiscOffers() != null) { 
					 ArrayList<AbsOffer> ofrs = c.getDiscOffers();
				     for(AbsOffer j:ofrs){ 
				     ArrayList <String> ks = j.displayOffer(c.getItem_id()); 
				     for (String k: ks) {%>
				<h6 class="price">Hurry !! Limited Offer <%= k %></h6>
				<%}}} %>
				</td>
<td> 
<form action="order-now" method="post" class="form-inline">
<input type="hidden" name="id<%=c.getItemId() %>" value = <%=c.getItemId() %> class="form-input">
<div class="form-group d-flex justify-content=between w-50">
	<a class="btn bnt-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getItemId()%>"><i class="fas fa-plus-square"></i></a> 
	<input type="text" name="quantity" value = <%=c.getQuantity() %> class="form-control w-50"  maxlength="8" >
	<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getItemId()%>"><i class="fas fa-minus-square"></i></a>
    <a href="order-now?id=<%=c.getItemId() %>&quantity=<%=c.getQuantity() %>" class="btn btn-sm btn-primary">Buy</a> 

</div>

</form>
</td>
<td><a href="remove-from-cart?id=<%=c.getItemId() %>" class="btn btn-sm btn-danger">Remove</a></td>
</tr>
          
   <% }}%>

</tbody>
</table>
</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>