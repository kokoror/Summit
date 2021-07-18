package com.summit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.summit.model.Features;


public class FeaturesDao {
	protected ConnectionManager connectionManager;
	private static FeaturesDao instance = null;
	
	protected FeaturesDao() {
		connectionManager = new ConnectionManager();
	}
	public static FeaturesDao getInstance() {
		if(instance == null) {
			instance = new FeaturesDao();
		}
		return instance;
	}
	
	public Features create(Features feature) throws SQLException {
		String insertFeature =
				"INSERT INTO Features(FeatureTag) " +
				"VALUES(?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;
			try {
				connection = connectionManager.getConnection();
				
				insertStmt = connection.prepareStatement(insertFeature,
					Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setString(1, feature.getFeatureTag());

				insertStmt.executeUpdate();
				
				// Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int featureId = -1;
				if(resultKey.next()) {
					featureId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				feature.setFeatureId(featureId);
				return feature;
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
	
	public Features getFeatureById(int featureId) throws SQLException {
		String selectFeature =
				"SELECT FeatureId,FeatureTag From Features "
				+ "WHERE FeatureId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectFeature);
				
				selectStmt.setInt(1, featureId);
				results = selectStmt.executeQuery();
				
				if(results.next()) {
					String featureTag = results.getString("FeatureTag");
					
					Features feature = new Features(featureId, featureTag);
					return feature;
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
	
	public Features delete(Features feature) throws SQLException {

		String deleteFeature = "DELETE FROM Features WHERE FeatureId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFeature);
			deleteStmt.setInt(1, feature.getFeatureId());
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
