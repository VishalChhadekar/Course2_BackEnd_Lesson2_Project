package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListProduct
 */
@WebServlet("/ListProduct")
public class ListProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String pro_id = request.getParameter("productId");
		String pro_name = request.getParameter("prd_name");
		String pro_brand= request.getParameter("prd_brand");
		String pro_model= request.getParameter("prd_model");
		
		
		String url = "jdbc:mysql://localhost:3306/productdetails";
		String userId = "root";
		String user_password = "Lucifer@23";
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, userId, user_password);

			// STEP 3: Create Statement Object
			PreparedStatement ps = con.prepareStatement("insert into productdetials values(?,?,?,?)");
			ps.setString(1, pro_id);
			ps.setString(2, pro_name);
			ps.setString(3, pro_brand);
			ps.setString(4, pro_model);
			int i =ps.executeUpdate();
			
			if(i>0) {
				out.println("<h1>You have successfuly added your product</h1>");
			}
			else {
				out.println("Error");
			}
			//5:
			ps.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
