package com.summit.servlet;

import com.summit.dal.TrailsDao;
import com.summit.model.Trails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/usercreate")
public class TrailCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/TrailCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String trailIdStr = req.getParameter("trailId");
        if (trailIdStr == null || trailIdStr.trim().isEmpty()) {
            messages.put("success", "Invalid TrailId");
        } else {
        	// Create the Trail.
        	int trailId = Integer.parseInt(trailIdStr);
        	String trailName = req.getParameter("trailName");
        	String area = req.getParameter("area");
        	String city = req.getParameter("city");
        	String state = req.getParameter("state");
        	String country = req.getParameter("country");
        	String latitudeStr = req.getParameter("latitude");
        	double latitude = Double.parseDouble(latitudeStr);
        	String longitudeStr = req.getParameter("longitude");
        	double longitude = Double.parseDouble(longitudeStr);
        	String elevationGainStr = req.getParameter("elevationGain");
        	double elevationGain = Double.parseDouble(elevationGainStr);
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Trails trail = new Trails(trailId, trailName, area, city, state, country, latitude, longitude, elevationGain, Trails.DifficultyLevel.EASY, Trails.RouteType.OUTBACK);
	        	trail = trailsDao.create(trail);
	        	messages.put("success", "Successfully created " + "trailId" + "trailName");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }
}