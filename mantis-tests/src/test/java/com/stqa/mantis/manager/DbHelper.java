package com.stqa.mantis.manager;

import com.stqa.mantis.model.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DbHelper {
  public Set<User> usersNotAdminSetJdbc() {
    try {
      Connection conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" +
                      "user=root&password=&serverTimezone=UTC");
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select username from mantis_user_table where access_level != 90");
      Set<User>users = new HashSet<>();
      while (rs.next()) {
        users.add(new User().setName(rs.getString("username")));

      }
      rs.close();
      stmt.close();
      conn.close();

      return users;


    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return null;
  }
}
