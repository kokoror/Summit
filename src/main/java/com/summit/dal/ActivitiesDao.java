package com.summit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.summit.model.Activities;

public class ActivitiesDao {
	protected ConnectionManager connectionManager;
	private static ActivitiesDao instance = null;
	
	protected ActivitiesDao() {
		connectionManager = new ConnectionManager();
	}
	public static ActivitiesDao getInstance() {
		if(instance == null) {
			instance = new ActivitiesDao();
		}
		return instance;
	}
	
	public Activities create(Activities activity) throws SQLException {
		String insertActivity =
				"INSERT INTO Activities(ActivityTag) " +
				"VALUES(?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				
				insertStmt = connection.prepareStatement(insertActivity,
					Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setString(1, activity.getActivityTag());

				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int activityId = -1;
				if(resultKey.next()) {
					activityId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				activity.setActivityId(activityId);
				return activity;
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
				if(resultKey != null) {
					resultKey.close();
				}
			}
		}
	
	public Activities getActivityById(int activityId) throws SQLException {
		String selectActivity =
				"SELECT ActivityId,ActivityTag From Activities "
				+ "WHERE ActivityId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectActivity);
				
				selectStmt.setInt(1, activityId);
				results = selectStmt.executeQuery();
				
				if(results.next()) {
					String activityTag = results.getString("ActivityTag");
					Activities activity = new Activities(activityId, activityTag);
					return activity;
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
	
	public Activities delete(Activities activity) throws SQLException {

		String deleteActivity = "DELETE FROM Activities WHERE ActivityId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteActivity);
			deleteStmt.setInt(1, activity.getActivityId());
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
