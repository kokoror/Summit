package com.summit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.summit.model.Activities;
import com.summit.model.InterestedActivities;
import com.summit.model.Users;


public class InterestedActivitiesDao {
	protected ConnectionManager connectionManager;

	private static InterestedActivitiesDao instance = null;
	protected InterestedActivitiesDao() {
		connectionManager = new ConnectionManager();
	}
	public static InterestedActivitiesDao getInstance() {
		if(instance == null) {
			instance = new InterestedActivitiesDao();
		}
		return instance;
	}
	

	public InterestedActivities create(InterestedActivities interestedActivity) throws SQLException {
		String insertInterestedActivity =
				"INSERT INTO InterestedActivities(UserName,ActivityId) " +
				"VALUES(?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;

			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertInterestedActivity,
						Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setString(1, interestedActivity.getUserName().getUsername());
				insertStmt.setInt(2, interestedActivity.getActivity().getActivityId());

				insertStmt.executeUpdate();
				
				resultKey = insertStmt.getGeneratedKeys();
				int interestedActivityId = -1;
				if(resultKey.next()) {
					interestedActivityId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				
				interestedActivity.setFavoriteActivityId(interestedActivityId);;
				return interestedActivity;
				
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
	
	public InterestedActivities getInterestedActivityById(int interestedActivityId) throws SQLException {
		String selectInterestedActivity =
				"SELECT FavoriteActivityId,UserName,ActivityId " +
				"FROM InterestedActivities " +
				"WHERE FavoriteActivityId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectInterestedActivity);
				
				selectStmt.setInt(1, interestedActivityId);
				results = selectStmt.executeQuery();
				
				UsersDao usersDao = UsersDao.getInstance();
				ActivitiesDao activitiesDao = ActivitiesDao.getInstance();

				if(results.next()) {
					String userName = results.getString("UserName");
					int activityId = results.getInt("ActivityId");
					
					Users user = usersDao.getUserByUserName(userName);
					Activities activity = activitiesDao.getActivityById(activityId);
					
					InterestedActivities InterestedActivity = new InterestedActivities(interestedActivityId, user, activity);
					
					return InterestedActivity;
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
	
	

	
	public List<InterestedActivities> getInterestedActivityByUserName(String userName) throws SQLException {
		
		List<InterestedActivities> interestedActivities = new ArrayList<InterestedActivities>();
		String selectInterestedActivity =
				"SELECT FavoriteActivityId,UserName,ActivityId " +
				"FROM InterestedActivities " +
				"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectInterestedActivity);
			
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			ActivitiesDao activitiesDao = ActivitiesDao.getInstance();
					
			
			while(results.next()) {
				
				int interestedActivityId = results.getInt("FavoriteActivityId");
				String resultUserName = results.getString("UserName");
				int activityId = results.getInt("ActivityId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Activities activity = activitiesDao.getActivityById(activityId);
				
				InterestedActivities interestedActivity = new InterestedActivities(interestedActivityId, user, activity);

				interestedActivities.add(interestedActivity);
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
		return interestedActivities;
	}
	
	
	
	public List<InterestedActivities> getInterestedActivityByActivityId(int activityId) throws SQLException {
		
		List<InterestedActivities> interestedActivities = new ArrayList<InterestedActivities>();
		String selectInterestedActivity =
				"SELECT FavoriteActivityId,UserName,ActivityId " +
				"FROM InterestedActivities " +
				"WHERE ActivityId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectInterestedActivity);
			
			selectStmt.setInt(1, activityId);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			ActivitiesDao activitiesDao = ActivitiesDao.getInstance();
					
			
			while(results.next()) {
				
				int interestedActivityId = results.getInt("FavoriteActivityId");
				String userName = results.getString("UserName");
				int resultActivityId = results.getInt("ActivityId");
				
				Users user = usersDao.getUserByUserName(userName);
				Activities activity = activitiesDao.getActivityById(resultActivityId);
				
				InterestedActivities interestedActivity = new InterestedActivities(interestedActivityId, user, activity);

				interestedActivities.add(interestedActivity);
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
		return interestedActivities;
	}
	
	
	public InterestedActivities delete(InterestedActivities interestedActivity) throws SQLException {
		String deleteInterestedActivity = "DELETE FROM InterestedActivities WHERE FavoriteActivityId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteInterestedActivity);
			deleteStmt.setInt(1, interestedActivity.getFavoriteActivityId());
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
