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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String userID = "JMD";
	private final String password = "JMD";
	private final String userID2 = "test";
	private final String password2 = "test";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		
		// get Cookie value
		Cookie[] cookies = request.getCookies();
		String cookieName = user;
		
		if(userID.equals(user) && password.equals(pwd)||userID2.equals(user) && password2.equals(pwd)){
			Cookie loginCookie = new Cookie("user",user);	
			
			// Collect user information
			// get client IP 
		    String   ipAddress = request.getRemoteAddr();  
	
			// get Useragent
		    String userAgent = request.getHeader("User-Agent");
			
		
			try {
			String storeCookie = loginCookie.toString();
			
			
			// Add cookie and User infon into Hashmap
			String hash256 = ChookyCookie.StoreCookie(user, ipAddress, userAgent);
			
			// Add user state into Hashmap
			String State = "Active";
			String UserStateHash = ChookyCookie.StoreUserState(user, State);
			
			} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			//setting cookie to expiry in 30 mins
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			response.sendRedirect("LoginSuccess.jsp");
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}

}

