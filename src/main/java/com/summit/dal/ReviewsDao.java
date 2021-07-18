package com.summit.dal;

import com.summit.model.Reviews;
import com.summit.model.Trails;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static ReviewsDao instance = null;
  protected ReviewsDao() {
    connectionManager = new ConnectionManager();
  }
  public static ReviewsDao getInstance() {
    if(instance == null) {
      instance = new ReviewsDao();
    }
    return instance;
  }

  public Reviews create(Reviews review) throws SQLException {
    String insertReview = "INSERT INTO reviews(Created,VisitDate,Content,Rating,UserName,TrailId)\n"
                                    + "VALUES(?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
      // PreparedStatement allows us to substitute specific types into the query template.
      // For an overview, see:
      // http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // For nullable fields, you can check the property first and then call setNull()
      // as applicable.
      insertStmt.setTimestamp(1, review.getCreated());
      insertStmt.setDate(2, review.getVisitDate());
      insertStmt.setString(3,review.getContent());
      insertStmt.setDouble(4, review.getRating());
      insertStmt.setString(5, review.getUser().getUsername());
      insertStmt.setInt(6, review.getTrail().getTrailId());

      // Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
      // statements, and it returns an int for the row counts affected (or 0 if the
      // statement returns nothing). For more information, see:
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      insertStmt.executeUpdate();

      // Note 1: if this was an UPDATE statement, then the users fields should be
      // updated before returning to the caller.
      // Note 2: there are no auto-generated keys, so no update to perform on the
      // input param users.
      ResultSet resultKey = insertStmt.getGeneratedKeys();
      int reviewsId = -1;
      if(resultKey.next()) {
        reviewsId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      review.setReviewId(reviewsId);

      return review;
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

  public Reviews getReviewById(int reviewId) throws SQLException {
    String selectReview = "SELECT ReviewId,Created,VisitDate,Content,Rating,UserName,TrailId FROM reviews WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReview);
      selectStmt.setInt(1, reviewId);
      // Note that we call executeQuery(). This is used for a SELECT statement
      // because it returns a result set. For more information, see:
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
      results = selectStmt.executeQuery();
      // You can iterate the result set (although the example below only retrieves
      // the first record). The cursor is initially positioned before the row.
      // Furthermore, you can retrieve fields by name and by type.
      if(results.next()) {
        Integer resultReviewId = results.getInt("ReviewId");
        Timestamp created = results.getTimestamp("Created");
        Date visitDate = results.getDate("VisitDate");
        String content = results.getString("Content");
        Double rating = results.getDouble("Rating");
        String username = results.getString("UserName");
        Integer trailId = results.getInt("TrailId");

        return new Reviews(resultReviewId,created,content,rating,UsersDao.getInstance().getUserByUserName(username),visitDate,TrailsDao.getInstance().getTrailsById(trailId));
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

  public List<Reviews> getReviewsByUserName(String userName) throws SQLException {
    List<Reviews> reviews = new ArrayList<>();
    String selectReview = "SELECT ReviewId,Created,VisitDate,Content,Rating,UserName,TrailId FROM reviews WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReview);
      selectStmt.setString(1, userName);
      // Note that we call executeQuery(). This is used for a SELECT statement
      // because it returns a result set. For more information, see:
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
      results = selectStmt.executeQuery();
      // You can iterate the result set (although the example below only retrieves
      // the first record). The cursor is initially positioned before the row.
      // Furthermore, you can retrieve fields by name and by type.
      while(results.next()) {
        Integer resultReviewId = results.getInt("ReviewId");
        Timestamp created = results.getTimestamp("Created");
        Date visitDate = results.getDate("VisitDate");
        String content = results.getString("Content");
        Double rating = results.getDouble("Rating");
        String username = results.getString("UserName");
        Integer trailId = results.getInt("TrailId");

        reviews.add(new Reviews(resultReviewId,created,content,rating,UsersDao.getInstance().getUserByUserName(username),visitDate,TrailsDao.getInstance().getTrailsById(trailId)));
      }
      return reviews;
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
  }


  public List<Reviews> getReviewsByRestaurantId(int restaurantId) throws SQLException {
    List<Reviews> reviews = new ArrayList<>();
    String selectReview = "SELECT ReviewId,Created,VisitDate,Content,Rating,UserName,TrailId FROM reviews WHERE RestaurantId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReview);
      selectStmt.setInt(1, restaurantId);
      // Note that we call executeQuery(). This is used for a SELECT statement
      // because it returns a result set. For more information, see:
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
      results = selectStmt.executeQuery();
      // You can iterate the result set (although the example below only retrieves
      // the first record). The cursor is initially positioned before the row.
      // Furthermore, you can retrieve fields by name and by type.
      while(results.next()) {
        Integer resultReviewId = results.getInt("ReviewId");
        Timestamp created = results.getTimestamp("Created");
        Date visitDate = results.getDate("VisitDate");
        String content = results.getString("Content");
        Double rating = results.getDouble("Rating");
        String username = results.getString("UserName");
        Integer trailId = results.getInt("TrailId");

        reviews.add(new Reviews(resultReviewId,created,content,rating,UsersDao.getInstance().getUserByUserName(username),visitDate,TrailsDao.getInstance().getTrailsById(trailId)));
      }
      return reviews;
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
  }

  public Reviews delete(Reviews review) throws SQLException {
    String deleteUser = "DELETE FROM reviews WHERE reviewId = ?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteUser);
      deleteStmt.setInt(1, review.getReviewId());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the Users instance.
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
