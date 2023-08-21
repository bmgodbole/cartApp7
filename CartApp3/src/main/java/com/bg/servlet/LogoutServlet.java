package com.bg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
       if(request.getSession().getAttribute("auth")!=null)
       {
    	   request.getSession(false).removeAttribute("auth");
    	   request.getSession(false).invalidate();  
    	   response.sendRedirect("login.jsp");
       }
       else{
    	   response.sendRedirect("index.jsp");
       }
	}


}
