package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductDetials
 */
@WebServlet("/ProductDetials")
public class ProductDetials extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetials() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "jdbc:mysql://localhost:3306/productdetails";
		String userId = "root";
		String password = "Lucifer@23";
		
		String productId = request.getParameter("productId");
		System.out.println("getting product id");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, userId, password);
			// STEP 3: Create Statement Object
			Statement stmt = con.createStatement();
			System.out.println("class loaded and connection created");

			ResultSet rs = stmt.executeQuery("select * from productdetials where pId = '" + productId + "'");
			System.out.println("queriy executed");
			if (productId != "") {
				System.out.println("condition true, outer if");
				if (rs.next()) {
					System.out.println("conditio true, inner if");

					String product_Id = rs.getString("pId");
					String pName = rs.getString("pName");
					String pBrand = rs.getString("pBrand");
					String pModel = rs.getString("pModel");

					out.println("<h1>Product Details</h1> ");
					out.println("Product Id: " + product_Id);
					out.println("Product Name: " + pName);
					out.println("Product Brand : " + pBrand);
					out.println("Product Model : " + pModel);

				}
				else {
					System.out.println("condition false, inner else: Wrong product Id");
					out.println("<h1>Wrong product Id Or Product is not listed</h1>");
					out.println("<h1><a href='addProduct.html'>Click here to List your product</a></h1>");
					
				}
			} else {
				System.out.println("condition false: outer else part");
				System.out.println("Please enter product Id");
				out.println("<h1>Please enter product Id</h1>");
				out.println("<h1><a href='index.html'>Click here to Enter</a></h1>");
			}
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
