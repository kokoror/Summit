package com.summit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.summit.model.Activities;
import com.summit.model.TrailActivities;
import com.summit.model.Trails;


public class TrailActivitiesDao {
	protected ConnectionManager connectionManager;

	private static TrailActivitiesDao instance = null;
	protected TrailActivitiesDao() {
		connectionManager = new ConnectionManager();
	}
	public static TrailActivitiesDao getInstance() {
		if(instance == null) {
			instance = new TrailActivitiesDao();
		}
		return instance;
	}
	

	public TrailActivities create(TrailActivities trailActivity) throws SQLException {
		String insertTrailActivity =
				"INSERT INTO TrailActivities(TrailId,ActivityId) " +
				"VALUES(?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;

			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertTrailActivity,
						Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setInt(1, trailActivity.getTrail().getTrailId());
				insertStmt.setInt(2, trailActivity.getActivity().getActivityId());

				insertStmt.executeUpdate();
				
				resultKey = insertStmt.getGeneratedKeys();
				int trailActivityId = -1;
				if(resultKey.next()) {
					trailActivityId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				
				trailActivity.setTrailActivityId(trailActivityId);;
				return trailActivity;
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(insertStmt != null) {
					insertStmt.close();
				}
			}
		}
	
	public TrailActivities getTrailActivityById(int trailActivityId) throws SQLException {
		String selectTrailActivity =
				"SELECT TrailActivityId,TrailId,ActivityId " +
				"FROM TrailActivities " +
				"WHERE TrailActivityId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectTrailActivity);
				
				selectStmt.setInt(1, trailActivityId);
				results = selectStmt.executeQuery();
				
				TrailsDao trailsDao = TrailsDao.getInstance();
				ActivitiesDao activitiesDao = ActivitiesDao.getInstance();

				if(results.next()) {
					int trailId = results.getInt("TrailId");
					int activityId = results.getInt("ActivityId");
					
					Trails trail = trailsDao.getTrailsById(trailId);
					Activities activity = activitiesDao.getActivityById(activityId);
			
					
					TrailActivities trailActivity = new TrailActivities(trailActivityId, trail, activity);
					
					return trailActivity;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
		}
	
	

	
	public List<TrailActivities> getTrailActivityByTrailId(int trailId) throws SQLException {
		
		List<TrailActivities> trailActivities = new ArrayList<TrailActivities>();
		String selectTrailActivity =
				"SELECT TrailActivityId,TrailId,ActivityId " +
				"FROM TrailActivities " +
				"WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrailActivity);
			
			selectStmt.setInt(1, trailId);
			results = selectStmt.executeQuery();
			
			TrailsDao trailsDao = TrailsDao.getInstance();
			ActivitiesDao activitiesDao = ActivitiesDao.getInstance();
		
			while(results.next()) {
				int trailActivityId = results.getInt("TrailActivityId");
				int resultTrailId = results.getInt("TrailId");
				int activityId = results.getInt("ActivityId");
				
				Trails trail = trailsDao.getTrailsById(resultTrailId);
				Activities activity = activitiesDao.getActivityById(activityId);
		
				
				TrailActivities trailActivity = new TrailActivities(trailActivityId, trail, activity);

				trailActivities.add(trailActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return trailActivities;
	}
	
	
	public List<TrailActivities> getTrailActivityByActivityId(int activityId) throws SQLException {
		
		List<TrailActivities> trailActivities = new ArrayList<TrailActivities>();
		String selectTrailActivity =
				"SELECT TrailActivityId,TrailId,ActivityId " +
				"FROM TrailActivities " +
				"WHERE ActivityId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrailActivity);
			
			selectStmt.setInt(1, activityId);
			results = selectStmt.executeQuery();
			
			TrailsDao trailsDao = TrailsDao.getInstance();
			ActivitiesDao activitiesDao = ActivitiesDao.getInstance();
		
			while(results.next()) {
				int trailActivityId = results.getInt("TrailActivityId");
				int trailId = results.getInt("TrailId");
				int resultActivityId = results.getInt("ActivityId");
				
				Trails trail = trailsDao.getTrailsById(trailId);
				Activities activity = activitiesDao.getActivityById(resultActivityId);
		
				
				TrailActivities trailActivity = new TrailActivities(trailActivityId, trail, activity);

				trailActivities.add(trailActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return trailActivities;
	}
	
	
	public TrailActivities delete(TrailActivities trailActivity) throws SQLException {
		String deleteTrailActivity = "DELETE FROM TrailActivities WHERE TrailActivityId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTrailActivity);
			deleteStmt.setInt(1, trailActivity.getTrailActivityId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
