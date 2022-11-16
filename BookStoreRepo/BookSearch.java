package search;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookSearch
 */
@WebServlet("/BookSearch")
public class BookSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String book = request.getParameter("bookName");
		Connection connection = null;
		try {
	           DBConnector.getDBConnection();
	           connection = DBConnector.connection;
	           PreparedStatement preparedStmt = null;
	           
	           if (book.isEmpty()) {
	              System.out.println("Inputted book DNE!");
	            } else {
	               String bookSelectSQL ="SELECT * FROM BookSearch WHERE BOOK_NAME='" + book + "'";
	               preparedStmt = connection.prepareStatement(bookSelectSQL);
	            
	            }
	           
	           ResultSet rs = preparedStmt.executeQuery();
	           PrintWriter out = response.getWriter();
	   	        String title = "Placeholder Results!";
	   	        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	   	       response.setContentType("text/html");
	   	       out.println(docType + //
	   	              "<html>\n" + //
	   	              "<head><title>" + title + "</title></head>\n" + //
	   	              "<body bgcolor=\"#f0f0f0\">\n" + //
	   	              "<h2 align=\"center\">" + title + "</h2>\n");
	           while (rs.next()) {
	        	   String bookName = rs.getString("BOOK_NAME").trim();
	        	   String bookAuthor = rs.getString("BOOK_AUTHOR").trim();
	        	   Double price = rs.getDouble("BOOK_PRICE");
	        	   String bookISBN = rs.getString("BOOK_ISBN").trim();
	        	   String bookURL = rs.getString("BOOK_URL").trim();
	        	   
	        	   out.println(docType + //
	 	   	              "<ul>\n" + //

	 	   	              "  <li><b>Book Name</b>: " + bookName + "\n" + //
	 	   	              "  <li><b>Author</b>: " + bookAuthor + "\n" + //
	 	   	              "  <li><b>Book Price</b>: " + price + "\n" + //
	 	   	              "  <li><b>ISBN</b>: " + bookISBN + "\n" + //
	 	   	             
	 	   	              "</ul>\n");   
	        	   
	        	   out.println("<a href=" + bookURL + ">View book!</a> <br>");
	 	           }
	 	           out.println("<a href=/BookStoreRepo/index.html>Back to HomePage!</a> <br>");
	 		   	   out.println("</body></html>");
	 	           
	 	           
	 	           
	 	           connection.close();
	           
		 }catch (Exception e) {
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
