package com.summit.servlet;

import com.summit.dal.ReviewsDao;
import com.summit.dal.TrailActivitiesDao;
import com.summit.dal.TrailFeaturesDao;
import com.summit.dal.TrailsDao;
import com.summit.dal.UsersDao;
import com.summit.model.Reviews;
import com.summit.model.TrailActivities;
import com.summit.model.TrailFeatures;
import com.summit.model.Trails;
import com.summit.model.Users;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@WebServlet("/userreviews")
public class UserReviews extends HttpServlet {
	
	protected UsersDao usersDao;
	protected ReviewsDao reviewsDao;
	protected TrailFeaturesDao trailFeaturesDao;
	protected TrailActivitiesDao trailActivitiesDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reviews> reviews = new ArrayList<Reviews>();

        // trailid is retrieved from the URL query string.
        String username = req.getParameter("username");
				Users user = null;
				try {
					user = usersDao.getUserByUserName(username);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				if (username == null || username.trim().isEmpty() || user==null) {
            messages.put("success", "Please enter a valid trail id.");
        } else {
        	// Retrieve trails, and store as a message.
        	try {
        		reviews = reviewsDao.getReviewsByUserName(username);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying reviews of " + username);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
//        	messages.put("previousFirstName", firstName);
        }
				req.setAttribute("user", user);
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/userreviews.jsp").forward(req, resp);
	}
	
//	@Override
//    public void doPost(HttpServletRequest req, HttpServletResponse resp)
//    		throws ServletException, IOException {
//        // Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//
//        List<BlogUsers> blogUsers = new ArrayList<BlogUsers>();
//        
//        // Retrieve and validate name.
//        // firstname is retrieved from the form POST submission. By default, it
//        // is populated by the URL query string (in FindUsers.jsp).
//        String firstName = req.getParameter("firstname");
//        if (firstName == null || firstName.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid name.");
//        } else {
//        	// Retrieve BlogUsers, and store as a message.
//        	try {
//            	blogUsers = blogUsersDao.getBlogUsersFromFirstName(firstName);
//            } catch (SQLException e) {
//    			e.printStackTrace();
//    			throw new IOException(e);
//            }
//        	messages.put("success", "Displaying results for " + firstName);
//        }
//        req.setAttribute("blogUsers", blogUsers);
//        
//        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
//    }
}

