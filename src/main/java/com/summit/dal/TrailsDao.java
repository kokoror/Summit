package com.summit.dal;

import com.summit.model.Trails;
import com.summit.model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrailsDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static TrailsDao instance = null;
  protected TrailsDao() {
    connectionManager = new ConnectionManager();
  }
  public static TrailsDao getInstance() {
    if(instance == null) {
      instance = new TrailsDao();
    }
    return instance;
  }

  /**
   * Save the Users instance by storing it in your MySQL instance.
   * This runs a INSERT statement.
   */
  public Trails create(Trails trail) throws SQLException {
    String insertTrail = "INSERT INTO trails(TrailName,Area,City,State,Country,Latitude,Longitude,ElevationGain,Difficulty,RouteType)\n"
        + "VALUES(?,?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertTrail, Statement.RETURN_GENERATED_KEYS);
      // PreparedStatement allows us to substitute specific types into the query template.
      // For an overview, see:
      // http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // For nullable fields, you can check the property first and then call setNull()
      // as applicable.
      insertStmt.setString(1, trail.getTrailName());
      insertStmt.setString(2, trail.getArea());
      insertStmt.setString(3, trail.getCity());
      insertStmt.setString(4, trail.getState());
      insertStmt.setString(5, trail.getCountry());
      insertStmt.setDouble(6, trail.getLatitude());
      insertStmt.setDouble(7, trail.getLongitude());
      insertStmt.setDouble(8, trail.getElevationGain());
      insertStmt.setString(9, trail.getLevel().level);
      insertStmt.setString(10, trail.getType().type);
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
      int trailsId = -1;
      if(resultKey.next()) {
        trailsId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      trail.setTrailId(trailsId);

      return trail;
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


  /**
   * Delete the Users instance.
   * This runs a DELETE statement.
   */
  public Trails delete(Trails trail) throws SQLException {
    String deleteTrail = "DELETE FROM Trails WHERE trailId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteTrail);
      deleteStmt.setInt(1, trail.getTrailId());
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

  /**
   * Get the Users record by fetching it from your MySQL instance.
   * This runs a SELECT statement and returns a single Persons instance.
   */
  public Trails getTrailsById(Integer trailId) throws SQLException {
    String selectTrail = "SELECT TrailId,TrailName,Area,City,State,Country,Latitude,Longitude,ElevationGain,Difficulty,RouteType "
        + "FROM Trails WHERE TrailId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectTrail);
      selectStmt.setInt(1, trailId);
      // Note that we call executeQuery(). This is used for a SELECT statement
      // because it returns a result set. For more information, see:
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
      results = selectStmt.executeQuery();
      // You can iterate the result set (although the example below only retrieves
      // the first record). The cursor is initially positioned before the row.
      // Furthermore, you can retrieve fields by name and by type.
      if(results.next()) {
        Integer resultTrailId = results.getInt("TrailId");
        String trailName = results.getString("TrailName");
        String area = results.getString("Area");
        String city = results.getString("City");
        String state = results.getString("State");
        String country = results.getString("Country");
        Double latitude = results.getDouble("Latitude");
        Double longitude = results.getDouble("Longitude");
        Double elevationGain = results.getDouble("ElevationGain");
        String difficulty = results.getString("Difficulty");
        String routeType = results.getString("RouteType");
        Trails trail = new Trails(resultTrailId,trailName,area,city,state,country,latitude,longitude,elevationGain,Trails.DifficultyLevel.valueOf(difficulty),Trails.RouteType.valueOf(routeType));
        return trail;
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
}
