package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.ReimbStatus;
import com.revature.utils.ConnectionUtil;

public class ReimbStatusDAO implements ReimbStatusDAOInterface {
	
	static final Logger log = LogManager.getLogger();
	
	@Override
	public ArrayList<ReimbStatus> getAllReimbStatuses() {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			ArrayList <ReimbStatus> reimbStatusList = new ArrayList<>();
			
			String sql = "SELECT * FROM reimb_statuses;";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				ReimbStatus reimbStatus = new ReimbStatus(
						resultSet.getInt("reimb_status_id"),
						resultSet.getString("reimb_status_name")
						);
				
				reimbStatusList.add(reimbStatus);			
			}
			
			return reimbStatusList;
			
			
		} catch (SQLException e) {
			
			log.warn("Failed getting all ReimbStatuses. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ReimbStatus getReimbStatusById(int reimb_status_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM reimb_statuses WHERE reimb_status_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimb_status_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				ReimbStatus reimbStatus = new ReimbStatus(
						resultSet.getInt("reimb_status_id"),
						resultSet.getString("reimb_status_name")
						);
				
				return reimbStatus;
				
			}
			
		} catch (SQLException e) {
			
			log.warn("Failed getting ReimbStatus by ID. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean insertReimbStatus(ReimbStatus reimbStatus) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO reimb_statuses (reimb_status_name) "
					+ "VALUES (?);";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reimbStatus.getReimb_status_name());
			
			preparedStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			
			log.warn("Failed inserting ReimbStatus. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteReimbStatusWithId(int reimb_status_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "DELETE FROM reimb_statuses WHERE reimb_status_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimb_status_id);
			
			preparedStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			
			log.warn("Failed deleting ReimbStatus. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return false;
	}
}
