package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.ReimbType;
import com.revature.utils.ConnectionUtil;

public class ReimbTypeDAO implements ReimbTypeDAOInterface {

	@Override
	public ArrayList<ReimbType> getAllReimbTypes() {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			ArrayList <ReimbType> reimbTypeList = new ArrayList<>();
			
			String sql = "SELECT * FROM reimb_types;";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				ReimbType reimbType = new ReimbType(
						resultSet.getInt("reimb_type_id"),
						resultSet.getString("reimb_type_name")
						);
				
				reimbTypeList.add(reimbType);			
			}
			
			return reimbTypeList;
			
			
		} catch (SQLException e) {
			System.out.println("Failed getting all ReimbTypes: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ReimbType getReimbTypeById(int reimt_type_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM reimb_types WHERE reimb_type_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimt_type_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				ReimbType reimbType = new ReimbType(
						resultSet.getInt("reimb_type_id"),
						resultSet.getString("reimb_type_name")
						);
				
				return reimbType;
				
			}
			
		} catch (SQLException e) {
			System.out.println("Failed getting ReimbType by ID: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ReimbType insertReimbType(ReimbType reimbType) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO reimb_type (reimb_type_name) "
					+ "VALUES (?);";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reimbType.getReimb_type_name());
			
			preparedStatement.executeUpdate();
			
			return reimbType;
			
		} catch (SQLException e) {
			System.out.println("Failed inserting ReimbType: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ReimbType deleteReimbType(ReimbType reimbType) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "DELETE FROM reimb_types WHERE reimb_type_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimbType.getReimb_type_id());
			
			preparedStatement.executeUpdate();
			
			return reimbType;
			
		} catch (SQLException e) {
			System.out.println("Failed deleting ReimbType: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return null;
	}

}