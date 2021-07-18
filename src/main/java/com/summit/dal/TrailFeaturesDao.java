package com.summit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
		
		List<TrailFeatures> reviews = new ArrayList<TrailFeatures>();
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
					
			////////////////////////
			
			///////////////////////////////////
			
			while(results.next()) {
				
				int trailFeatrueId = results.getInt("TrailFeatureId");
				int resultTrailId = results.getInt("TrailId");
				int featureId = results.getInt("FeatureId");
				
				Trails trail = trailsDao.getTrailsById(trailId);
				Features feature = featuresDao.getFeatureById(featureId);
				
				Date created =  new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				float rating = results.getFloat("Rating");
				String resultUserName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
				
				Reviews review = new Reviews(reviewId, created, content, 
						rating, user, restaurant);
				reviews.add(review);
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
		return reviews;
	}
	
	
	public List<Reviews> getReviewsByRestaurantId(int restaurantId) throws SQLException {
		
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
				"SELECT ReviewId,Created,Content,Rating,UserName,RestaurantId " +
				"FROM Reviews " +
				"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			
			while(results.next()) {
				
				int reviewId = results.getInt("ReviewId");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				float rating = results.getFloat("Rating");
				String userName = results.getString("UserName");
				int resultRestaurantId = results.getInt("RestaurantId");
				
				Users user = usersDao.getUserByUserName(userName);
				Restaurants restaurant = restaurantsDao.getRestaurantById(resultRestaurantId);
				
				Reviews review = new Reviews(reviewId, created, content, 
						rating, user, restaurant);
				reviews.add(review);
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
		return reviews;
	}
	
	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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
