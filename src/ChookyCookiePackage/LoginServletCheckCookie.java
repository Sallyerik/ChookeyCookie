package ChookyCookiePackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ChookyCookiePackage.ChookyCookie;


/**
 * Servlet implementation class LoginServletCheckCookie
 */
@WebServlet("/LoginServletCheckCookie")
public class LoginServletCheckCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String CookieValue = null;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")) CookieValue = cookie.getValue();			
			//System.out.println("In login check Cookie value is : "+ CookieValue);
			// Collect user information
		  }
		}

			// get client IP 
		       String   ipAddress = request.getRemoteAddr();  
		
			// get Useragent
		       String userAgent = request.getHeader("User-Agent");
		    
		    // get previous Referer
		       String Referer= request.getHeader("Referer");
		       
	       
		       String valuePost = request.getParameter("q");
		       switch (valuePost) {
		         case "Accounts":
		        	 try {
						   boolean isKeyPresent = ChookyCookie.validateChookyCookie(CookieValue, ipAddress, userAgent);
						   if(isKeyPresent == true & Referer.contains("LoginSuccess.jsp")){
							   boolean statekey = ChookyCookie.validateChookyCookieUS("Active", CookieValue);
							   if (statekey == true) {
							   RequestDispatcher rd=request.getRequestDispatcher("AccountStatus.jsp");
							   ChookyCookie.StoreUserState(CookieValue, "Accounts");
							   rd.forward(request, response);
							   }else {
								   RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
									PrintWriter out= response.getWriter();
									out.println("<font color=red>This acction is not allow, User state is not valid.</font>");
									rd.include(request, response);
							   }
						   }else {
							   RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
								PrintWriter out= response.getWriter();
								out.println("<font color=red>This acction is not allow, User session values are not correct or referer is not AccountStatus.jsp .</font>");
								rd.include(request, response);
						   }
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		           break;
		         case "Balance":
		        	 try {
						   boolean isKeyPresent = ChookyCookie.validateChookyCookie(CookieValue, ipAddress, userAgent);
						   if(isKeyPresent == true & Referer.contains("LoginServletCheckCookie")){
							   boolean statekey = ChookyCookie.validateChookyCookieUS("Accounts", CookieValue);
							   if (statekey == true) {
							   RequestDispatcher rd=request.getRequestDispatcher("Balance.jsp");
							   ChookyCookie.StoreUserState(CookieValue, "Balance");
							   rd.forward(request, response);
							   }else {
								   RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
									PrintWriter out= response.getWriter();
									out.println("<font color=red>This acction is not allow, User state is not valid.</font>");
									rd.include(request, response);
							   }
						   }else {
							   RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
								PrintWriter out= response.getWriter();
								out.println("<font color=red>This acction is not allow, User session values are not correct or referer is not LoginServletCheckCookie.</font>");
								rd.include(request, response);
						   }
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		           break;
		         case "BAccounts":
		        	 try {
						   boolean isKeyPresent = ChookyCookie.validateChookyCookie(CookieValue, ipAddress, userAgent);
						   if(isKeyPresent == true & Referer.contains("LoginServletCheckCookie")){
							   boolean statekey = ChookyCookie.validateChookyCookieUS("Balance", CookieValue);
							   if (statekey == true) {
							   RequestDispatcher rd=request.getRequestDispatcher("AccountStatus.jsp");
							   ChookyCookie.StoreUserState(CookieValue, "Accounts");
							   rd.forward(request, response);
							   }else {
								   RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
									PrintWriter out= response.getWriter();
									out.println("<font color=red>This acction is not allow, User state is not valid.</font>");
									rd.include(request, response);
							   }
						   }else {
							   RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
								PrintWriter out= response.getWriter();
								out.println("<font color=red>This acction is not allow, User session values are not correct or referer is not LoginServletCheckCookie.</font>");
								rd.include(request, response);
						   }
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		           break;
		       }
		       
		
		if(CookieValue == null) response.sendRedirect("login.html");

	}

}
