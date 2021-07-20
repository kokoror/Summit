package com.summit.servlet;

import com.summit.dal.*;
import com.summit.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/traildelete")
public class TrailDelete extends HttpServlet {
	
	protected TrailsDao trailsDao;
	
	@Override
	public void init() throws ServletException {
		trailsDao = TrailsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String trailIdStr = req.getParameter("trailid");
        int trailId = Integer.parseInt(trailIdStr);
        
        if (trailIdStr == null || trailIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid trail id.");
        } else {
        	// display the TrailName
        	Trails trail = null;
			try {
				trail = trailsDao.getTrailsById(trailId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			req.setAttribute("trail", trail);
        }
        
        req.getRequestDispatcher("/traildelete.jsp").forward(req, resp);
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String trailIdStr = req.getParameter("trailid");
        int trailId = Integer.parseInt(trailIdStr);
        
        if (trailIdStr == null || trailIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid trail id.");
        } else {
        	// Delete the Trail
        	Trails trail = null;
			try {
				trail = trailsDao.getTrailsById(trailId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
	        	trail = trailsDao.delete(trail);
	        	// Update the message.
		        if (trail == null) {
		            messages.put("title", "Successfully deleted " + trail.getTrailName());
		            messages.put("disableSubmit", "true");
		            req.setAttribute("trail", trail);
		        } else {
		        	messages.put("title", "Failed to delete " + trail.getTrailName());
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/traildelete.jsp").forward(req, resp);
    }
}
