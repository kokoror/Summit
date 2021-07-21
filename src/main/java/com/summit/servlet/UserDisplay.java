package com.summit.servlet;


import com.summit.dal.UsersDao;
import com.summit.model.Trails;
import com.summit.model.Users;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/displayusers")
public class UserDisplay extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	//	doPost(req, resp);
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		messages.put("disableSubmit", "initial");
		req.getRequestDispatcher("/displayusers.jsp").forward(req, resp);

	}
	
		@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
			Map<String, String> messages = new HashMap<String, String>();
			req.setAttribute("messages", messages);
			Users users = null;

			// Retrieve and validate name.
			String username = req.getParameter("uname");
			if (username == null || username.trim().isEmpty()) {
					messages.put("title", "Username not present. Please create a username");
					messages.put("disableSubmit", "false");
			}
			else {
				try {
					users = usersDao.getUserByUserName(username);
					messages.put("disableSubmit", "true");
					if(users==null) {
						messages.put("title", "Username not present. Please create a username");
						messages.put("disableSubmit", "false");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				req.setAttribute("user", users);
			}
			req.getRequestDispatcher("/displayusers.jsp").forward(req, resp);
	}
}