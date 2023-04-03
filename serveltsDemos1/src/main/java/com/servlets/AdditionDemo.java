package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AdditionDemo extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String n1=req.getParameter("n1");
		String n2=req.getParameter("n2");
		int result=Integer.parseInt(n1)+Integer.parseInt(n2);
		PrintWriter out=res.getWriter();
		out.println("<p>"+result+"</p>");
		
	}

}
