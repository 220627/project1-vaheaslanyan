package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AuthDAO implements AuthDAOInterface{
	
	UserDAO userDAO = new UserDAO();

	@Override
	public User login(String username, String password) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
		
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				User user = new User(
						resultSet.getInt("user_id"), 
						resultSet.getString("username"), 
						resultSet.getString("password"), 
						resultSet.getString("user_first_name"), 
						resultSet.getString("user_last_name"), 
						resultSet.getString("user_email"), 
						resultSet.getInt("user_role_id_fk") , 
						null //userRole
						);
				
				user.setUserRole(userDAO.getUserRoleForUser(user));
			
				return user;
				
			}
			
		} catch (SQLException e) {
			System.out.println("Login failed: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return null;
	}

}
