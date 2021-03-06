package com.summit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.summit.model.Features;
import com.summit.model.TrailFeatures;
import com.summit.model.Trails;


public class TrailFeaturesDao {
	protected ConnectionManager connectionManager;

	private static TrailFeaturesDao instance = null;
	protected TrailFeaturesDao() {
		connectionManager = new ConnectionManager();
	}
	public static TrailFeaturesDao getInstance() {
		if(instance == null) {
			instance = new TrailFeaturesDao();
		}
		return instance;
	}
	

	public TrailFeatures create(TrailFeatures trailFeature) throws SQLException {
		String insertTrailFeature =
				"INSERT INTO TrailFeatures(TrailId,FeatureId) " +
				"VALUES(?,?);";
			Connection connection = null;
			PreparedStatement insertStmt = null;
			ResultSet resultKey = null;

			try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertTrailFeature,
						Statement.RETURN_GENERATED_KEYS);
				
				insertStmt.setInt(1, trailFeature.getTrail().getTrailId());
				insertStmt.setInt(2, trailFeature.getFeature().getFeatureId());

				insertStmt.executeUpdate();
				
				resultKey = insertStmt.getGeneratedKeys();
				int trailFeatureId = -1;
				if(resultKey.next()) {
					trailFeatureId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				
				trailFeature.setTrailFeatureId(trailFeatureId);;
				return trailFeature;
				
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
	
	public TrailFeatures getTrailFeatureById(int trailFeatureId) throws SQLException {
		String selectTrailFeature =
				"SELECT TrailFeatureId,TrailId,FeatureId " +
				"FROM TrailFeatures " +
				"WHERE TrailFeatureId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectTrailFeature);
				
				selectStmt.setInt(1, trailFeatureId);
				results = selectStmt.executeQuery();
				
				TrailsDao trailsDao = TrailsDao.getInstance();
				FeaturesDao featuresDao = FeaturesDao.getInstance();

				if(results.next()) {
					int trailId = results.getInt("TrailId");
					int featureId = results.getInt("FeatureId");
					
					Trails trail = trailsDao.getTrailsById(trailId);
					Features feature = featuresDao.getFeatureById(featureId);
					
					TrailFeatures trailFeature = new TrailFeatures(trailFeatureId, trail, feature);
					
					return trailFeature;
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
	
	

	
	public List<TrailFeatures> getTrailFeatureByTrailId(int trailId) throws SQLException {
		
		List<TrailFeatures> trailFeatures = new ArrayList<TrailFeatures>();
		String selectRTrailFeature =
				"SELECT TrailFeatureId,TrailId,FeatureId " +
				"FROM TrailFeatures " +
				"WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRTrailFeature);
			
			selectStmt.setInt(1, trailId);
			results = selectStmt.executeQuery();
			
			TrailsDao trailsDao = TrailsDao.getInstance();
			FeaturesDao featuresDao = FeaturesDao.getInstance();
					
			
			while(results.next()) {
				
				int trailFeatrueId = results.getInt("TrailFeatureId");
				int resultTrailId = results.getInt("TrailId");
				int featureId = results.getInt("FeatureId");
				
				Trails trail = trailsDao.getTrailsById(resultTrailId);
				Features feature = featuresDao.getFeatureById(featureId);
				
				TrailFeatures trailFeature = new TrailFeatures(trailFeatrueId, trail, feature);

				trailFeatures.add(trailFeature);
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
		return trailFeatures;
	}
	
	public List<TrailFeatures> getTrailFeatureByFeatureId(int featureId) throws SQLException {
		
		List<TrailFeatures> trailFeatures = new ArrayList<TrailFeatures>();
		String selectRTrailFeature =
				"SELECT TrailFeatureId,TrailId,FeatureId " +
				"FROM TrailFeatures " +
				"WHERE FeatureId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRTrailFeature);
			
			selectStmt.setInt(1, featureId);
			results = selectStmt.executeQuery();
			
			TrailsDao trailsDao = TrailsDao.getInstance();
			FeaturesDao featuresDao = FeaturesDao.getInstance();
					
			
			while(results.next()) {
				
				int trailFeatrueId = results.getInt("TrailFeatureId");
				int trailId = results.getInt("TrailId");
				int resultFeatureId = results.getInt("FeatureId");
				
				Trails trail = trailsDao.getTrailsById(trailId);
				Features feature = featuresDao.getFeatureById(resultFeatureId);
				
				TrailFeatures trailFeature = new TrailFeatures(trailFeatrueId, trail, feature);

				trailFeatures.add(trailFeature);
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
		return trailFeatures;
	}
	
	
	
	public TrailFeatures delete(TrailFeatures trailFeature) throws SQLException {
		String deleteTrailFeature = "DELETE FROM TrailFeatures WHERE TrailFeatureId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTrailFeature);
			deleteStmt.setInt(1, trailFeature.getTrailFeatureId());
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
