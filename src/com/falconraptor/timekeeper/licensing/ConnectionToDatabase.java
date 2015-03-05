package com.falconraptor.timekeeper.licensing;

import com.falconraptor.utilities.logger.*;

import java.sql.*;

import static com.falconraptor.timekeeper.references.References.*;

public class ConnectionToDatabase {
	public Connection connection;

	public void connect () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://techraptor.us/techrapt_timekeeper", "techrapt_time", "timekeeper");
		} catch (Exception e) {
			Logger.logERROR(e);
		}
	}

	public void loadUsers () {
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM users";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				users.users.add(new User());
				users.users.get(users.users.size() - 1).username = resultSet.getString("Username");
				users.users.get(users.users.size() - 1).password = resultSet.getString("Password");
				users.users.get(users.users.size() - 1).email = resultSet.getString("Email");
				users.users.get(users.users.size() - 1).loggedin = resultSet.getBoolean("LoggedIn");
			}
		} catch (Exception e) {
			Logger.logERROR(e);
		}
	}
}
