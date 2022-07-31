package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.ReimbType;
import com.revature.utils.ConnectionUtil;

public class ReimbTypeDAO implements ReimbTypeDAOInterface {
	
	static final Logger log = LogManager.getLogger();

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
			
			log.warn("Failed getting all ReimbTypes. SQL Exception occured: " + e);
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
			
			log.warn("Failed getting ReimbType by ID. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean insertReimbType(ReimbType reimbType) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO reimb_types (reimb_type_name) "
					+ "VALUES (?);";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reimbType.getReimb_type_name());
			
			preparedStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			
			log.warn("Failed inserting ReimbType. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteReimbTypeWithId(int reimb_type_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "DELETE FROM reimb_types WHERE reimb_type_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimb_type_id);
			
			preparedStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			
			log.warn("Failed deleting ReimbType. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return false;
	}

}
