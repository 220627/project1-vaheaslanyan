package com.revature.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

public class UserRoleDAO implements UserRoleDAOInterface {

	@Override
	public ArrayList<UserRole> getAllUserRoles() {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			//Creating an empty ArrayList that will be populated with UserRoles
			ArrayList<UserRole> userRoleList = new ArrayList<>();
			
			String sql = "SELECT * FROM user_roles;";
			
			//Instantiating a Statement object and saving the result of query into a ResultSet object
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			//Iterating through resultSet with .next() method
			while(resultSet.next()) {
				
				UserRole userRole = new UserRole(
						resultSet.getInt("user_role_id"),
						resultSet.getString("user_role_name")
						);
				
				userRoleList.add(userRole);
			}
			
			return userRoleList;
			
		} catch (SQLException e) {
			System.out.println("Failed to get all UserRoles: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserRole getUserRoleById(int user_role_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			//Saving SQL in a String with '?' wildcard that is replaced with the help of PreparedStatement class below
			String sql = "SELECT * FROM user_roles WHERE user_role_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_role_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				UserRole userRole = new UserRole(
						resultSet.getInt("user_role_id"),
						resultSet.getString("user_role_name")
						);
				
				return userRole;
				
			}
			
		} catch (SQLException e) {
			System.out.println("Failed getting UserRole: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean insertUserRole(UserRole userRole) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO user_roles (user_role_name) "
					+ "VALUES (?);";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userRole.getUser_role_name());
			
			preparedStatement.executeUpdate();
			
			System.out.println("USerRole " + userRole.getUser_role_name() + " has been sucessfully added.");
			return true;
			
		} catch (SQLException e) {
			System.out.println("Failed inserting UserRole: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteUserRoleWithId(int user_role_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "DELETE FROM user_roles WHERE user_role_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_role_id);
			
			preparedStatement.executeUpdate();
			
			System.out.println("UserRole " + user_role_id + " successfully deleted.");
			return true;
			
		} catch (SQLException e) {
			System.out.println("Unable to delete UserRole: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return false;
	}

}
