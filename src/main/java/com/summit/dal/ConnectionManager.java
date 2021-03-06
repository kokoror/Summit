//Submitted by : Shweta
package com.summit.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//updates
/**
 * Use ConnectionManager to connect to your database instance.
 * 
 * ConnectionManager uses the MySQL Connector/J driver to connect to your local MySQL instance.
 * 
 * In our example, we will create a DAO (data access object) java class to interact with
 * each MySQL table. The DAO java classes will use ConnectionManager to open and close
 * connections.
 * 
 * Instructions:
 * 1. Install MySQL Community Server. During installation, you will need to set up a user,
 * password, and port. Keep track of these values.
 * 2. Download and install Connector/J: http://dev.mysql.com/downloads/connector/j/
 * 3. Add the Connector/J JAR to your buildpath. This allows your application to use the
 * Connector/J library. You can add the JAR using either of the following methods:
 *   A. When creating a new Java project, on the "Java Settings" page, go to the 
 *   "Libraries" tab.
 *   Click on the "Add External JARs" button.
 *   Navigate to the Connector/J JAR. On Windows, this looks something like:
 *   C:\Program Files (x86)\MySQL\Connector.J 8.0\mysql-connector-java-8.0.16-bin.jar
 *   B. If you already have a Java project created, then go to your project properties.
 *   Click on the "Java Build Path" option.
 *   Click on the "Libraries" tab, click on the "Add External Jars" button, and
 *   navigate to the Connector/J JAR.
 * 4. Update the "private final" variables below.
 */
public class ConnectionManager {

	// User to connect to your database instance. By default, this is "root2".
	private final String user = "root";
	// Password for the user.
	private final String password = "root";
	// URI to your database server. If running on the same machine, then this is "localhost".
	private final String hostName = "127.0.0.1";
	// Port to your database server. By default, this is 3307.
	private final int port= 3306;
	// Name of the MySQL schema that contains your tables.
	private final String schema = "summit";
	// Default timezone for MySQL server.
	private final String timezone = "UTC";

	/** Get the connection to the database instance. */
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(
			    "jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.schema + "?useSSL=false",
			    user,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/** Close the connection to the database instance. */
	public void closeConnection(Connection connection) throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
