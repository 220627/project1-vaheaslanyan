package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Reimb;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

public class ReimbDAO implements ReimbDAOInterface {

	@Override
	public ArrayList<Reimb> getAllReimbs() {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
		
			ArrayList <Reimb> reimbList = new ArrayList<>();
			
			String sql = "SELECT * FROM reimbs;";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				Reimb reimb = new Reimb(
						resultSet.getInt("reimb_id"), 
						resultSet.getDouble("reimb_amount"),
						resultSet.getString("reimb_submitted"), 
						resultSet.getString("reimb_resolved"), 
						resultSet.getString("reimb_description"), 
						resultSet.getString("reimb_receipt_url"), 
						resultSet.getInt("reimb_status_id_fk"), 
						resultSet.getInt("reimb_type_id_fk"), 
						resultSet.getInt("reimb_author_id_fk"), 
						resultSet.getInt("reimb_resolver_id_fk"), 
						null, //reimbStatus
						null, //reimbType 
						null, //author
						null //resolver
						);
				
				reimb.setReimbStatus(this.getReimbStatus(reimb));
				reimb.setReimbType(this.getReimbType(reimb));
				reimb.setReimbAuthor(this.getReimbAuthor(reimb));
				reimb.setReimbResolver(this.getReimbResolver(reimb));
				
				reimbList.add(reimb);
			}
			
			return reimbList;
			
			
		} catch (SQLException e) {
			System.out.println("Failed to get all Reimbs: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Reimb getReimbById(int reimb_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
		String sql = "SELECT * FROM reimbs WHERE reimb_id = ?;";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, reimb_id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
			while (resultSet.next()) {
				
				Reimb reimb = new Reimb(
						resultSet.getInt("reimb_id"), 
						resultSet.getDouble("reimb_amount"),
						resultSet.getString("reimb_submitted"), 
						resultSet.getString("reimb_resolved"), 
						resultSet.getString("reimb_description"), 
						resultSet.getString("reimb_receipt_url"), 
						resultSet.getInt("reimb_status_id_fk"), 
						resultSet.getInt("reimb_type_id_fk"), 
						resultSet.getInt("reimb_author_id_fk"), 
						resultSet.getInt("reimb_resolver_id_fk"), 
						null, //reimbStatus
						null, //reimbType 
						null, //author
						null //resolver
						);
				
				reimb.setReimbStatus(this.getReimbStatus(reimb));
				reimb.setReimbType(this.getReimbType(reimb));
				reimb.setReimbAuthor(this.getReimbAuthor(reimb));
				reimb.setReimbResolver(this.getReimbResolver(reimb));
				
				return reimb;
				
			}
			
		} catch (SQLException e) {
			System.out.println("Failed to get Reimb by ID: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean insertReimb(Reimb reimb) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO reimbs (reimb_amount, reimb_description, reimb_receipt_url, reimb_type_id_fk, reimb_author_id_fk) "
					+ "VALUES (?, ?, ?, ?, ?);";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, reimb.getReimb_amount());
			preparedStatement.setString(2, reimb.getReimb_description());
			preparedStatement.setString(3, reimb.getReimb_receipt_url());
			preparedStatement.setInt(4,  reimb.getReimb_type_id_fk());
			preparedStatement.setInt(5, reimb.getReimb_author_id_fk());
			
			preparedStatement.executeUpdate();
			System.out.println("Reimb with author ID " + reimb.getReimb_author_id_fk() + " successfully added");
			
			return true;
			
		} catch (SQLException e) {
			System.out.println("Failed to insert Reimb: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteReimbWithId(int reimb_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "DELETE FROM reimbs WHERE reimb_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimb_id);
			
			preparedStatement.executeQuery();
			System.out.println("Reimb with ID " + reimb_id + " successfully deleted.");
			
			return true;
			
		} catch (SQLException e) {
			System.out.println("Failed to delete Reimb: SQL Exception occured.");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/* MARK: - Helper Methods --------------------------------------------------------------------- */

	@Override
	public ReimbStatus getReimbStatus(Reimb reimb) {
		
		ReimbStatusDAO reimbStatusDAO = new ReimbStatusDAO();
		ReimbStatus reimbStatus = reimbStatusDAO.getReimbStatusById(reimb.getReimb_status_id_fk());
		
		return reimbStatus;
	}
	
	@Override
	public ReimbType getReimbType(Reimb reimb) {
		
		ReimbTypeDAO reimbTypeDAO = new ReimbTypeDAO();
		ReimbType reimbType = reimbTypeDAO.getReimbTypeById(reimb.getReimb_type_id_fk());
		
		return reimbType;
	}

	@Override
	public User getReimbAuthor(Reimb reimb) {

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserById(reimb.getReimb_author_id_fk());
		
		return user;
	}

	@Override
	public User getReimbResolver(Reimb reimb) {
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserById(reimb.getReimb_resolver_id_fk());
		
		return user;
	}

}
