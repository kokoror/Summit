package com.summit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.summit.model.Features;
import com.summit.model.PreferredFeatures;
import com.summit.model.Users;


public class PreferredFeaturesDao {
	protected ConnectionManager connectionManager;

	private static PreferredFeaturesDao instance = null;
	protected PreferredFeaturesDao() {
		connectionManager = new ConnectionManager();
	}
	public static PreferredFeaturesDao getInstance() {
		if(instance == null) {
			instance = new PreferredFeaturesDao();
		}
		return instance;
	}
	

	public PreferredFeatures create(PreferredFeatures preferredFeature) throws SQLException {
		String insertPreferredFeature =
				"INSERT INTO PreferredFeatures(UserName,FeatureId) " +
				"VALUES(?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;

			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertPreferredFeature,
						Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setString(1, preferredFeature.getUserName().getUsername());
				insertStmt.setInt(2, preferredFeature.getFeature().getFeatureId());

				insertStmt.executeUpdate();
				
				resultKey = insertStmt.getGeneratedKeys();
				int preferredFeatureId = -1;
				if(resultKey.next()) {
					preferredFeatureId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				
				preferredFeature.setPreferredFeatureId(preferredFeatureId);;
				return preferredFeature;
				
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
	
	public PreferredFeatures getPreferredFeatureById(int preferredFeatureId) throws SQLException {
		String selectPreferredFeature =
				"SELECT PreferredFeatureId,UserName,FeatureId " +
				"FROM PreferredFeatures " +
				"WHERE PreferredFeatureId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectPreferredFeature);
				
				selectStmt.setInt(1, preferredFeatureId);
				results = selectStmt.executeQuery();
				
				UsersDao usersDao = UsersDao.getInstance();
				FeaturesDao featuresDao = FeaturesDao.getInstance();

				if(results.next()) {
					String userName = results.getString("UserName");
					int featureId = results.getInt("FeatureId");
					
					Users user = usersDao.getUserByUserName(userName);
					Features feature = featuresDao.getFeatureById(featureId);
					
					PreferredFeatures preferredFeature = new PreferredFeatures(preferredFeatureId, user, feature);
					
					return preferredFeature;
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
	
	

	
	public List<PreferredFeatures> getPreferredFeatureByUserName(String userName) throws SQLException {
		
		List<PreferredFeatures> preferredFeatures = new ArrayList<PreferredFeatures>();
		String selectPreferredFeature =
				"SELECT PreferredFeatureId,UserName,FeatureId " +
				"FROM PreferredFeatures " +
				"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPreferredFeature);
			
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			FeaturesDao featuresDao = FeaturesDao.getInstance();
					
			
			while(results.next()) {
				
				int preferredFeatureId = results.getInt("PreferredFeatureId");
				String resultUserName = results.getString("UserName");
				int featureId = results.getInt("FeatureId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Features feature = featuresDao.getFeatureById(featureId);
				
				PreferredFeatures preferredFeature = new PreferredFeatures(preferredFeatureId, user, feature);

				preferredFeatures.add(preferredFeature);
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
		return preferredFeatures;
	}
	
	
	public List<PreferredFeatures> getPreferredFeatureByFeatureId(int featureId) throws SQLException {
		
		List<PreferredFeatures> preferredFeatures = new ArrayList<PreferredFeatures>();
		String selectPreferredFeature =
				"SELECT PreferredFeatureId,UserName,FeatureId " +
				"FROM PreferredFeatures " +
				"WHERE FeatureId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPreferredFeature);
			
			selectStmt.setInt(1, featureId);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			FeaturesDao featuresDao = FeaturesDao.getInstance();
					
			
			while(results.next()) {
				
				int preferredFeatureId = results.getInt("PreferredFeatureId");
				String userName = results.getString("UserName");
				int resultFeatureId = results.getInt("FeatureId");
				
				Users user = usersDao.getUserByUserName(userName);
				Features feature = featuresDao.getFeatureById(resultFeatureId);
				
				PreferredFeatures preferredFeature = new PreferredFeatures(preferredFeatureId, user, feature);

				preferredFeatures.add(preferredFeature);
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
		return preferredFeatures;
	}
	
	
	public PreferredFeatures delete(PreferredFeatures preferredFeature) throws SQLException {
		String deletePreferredFeature = "DELETE FROM PreferredFeatures WHERE PreferredFeatureId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePreferredFeature);
			deleteStmt.setInt(1, preferredFeature.getPreferredFeatureId());
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
