package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbController;
import com.revature.controllers.ReimbTypeController;
import com.revature.controllers.UserController;
import com.revature.controllers.UserRoleController;
import com.revature.daos.UserDAO;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		
		establishConnection();
		setUpJavalin();
			
	}
	
	//Establishing conenction
	public static void establishConnection() {
		try(Connection connection = ConnectionUtil.getConnection()) {
			System.out.println("Connection established.");
		} catch (SQLException e) {
			System.out.println("Failed getting Customers. SQL Exception occured.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Failed getting Customers. Other Exception occured.");
			e.printStackTrace();
		}
	}
	
	//Setting up Javalin server and HTTP endpoints
	public static void setUpJavalin() {
		//Initializing Controllers
		AuthController authController = new AuthController();
		UserRoleController userRoleController = new UserRoleController();
		UserController userController = new UserController();
		ReimbTypeController reimbTypeController = new ReimbTypeController();
		ReimbController reimbController = new ReimbController();
		
		//Starting Javalin server on port 3000
		Javalin app = Javalin.create(
				
				//Specifying configurations for Javalin
				config -> {
					//Enabling HTTP requests from any origin
					config.enableCorsForAllOrigins();
				}
				
				).start(3000);
		
		/* Handling HTTP requests --------------------------------------------------------- */
		
		//Handling user login
		app.post("/login", authController.loginHandler);
		app.post("/logout", authController.logoutHandler);
		
		//Handling requests for UserRole
		app.get("/user_roles", userRoleController.getAllUserRolesHandler);
		app.get("/user_roles/:user_role_id", userRoleController.getUserRoleByIdHandler);
		app.post("/user_roles", userRoleController.insertUserRoleHandler);
		app.delete("/user_roles/:user_role_id", userRoleController.deleteUserRoleHandler);
		
		//Handling requests for Users
		app.get("/users", userController.getAllUsersHandler);
		app.get("/users/:user_id", userController.getUserByIdHandler);
		app.post("/users", userController.insertUserHandler);
		app.delete("/users/:user_id", userController.deleteUserHandler);
		
		//Handling requests for ReimbTypes
		app.get("/reimb_types", reimbTypeController.getAllReimbTypesHandler);
		app.get("/reimb_types/:reimb_type_id", reimbTypeController.getReimbTypeByIdHandler);
		app.post("/reimb_types", reimbTypeController.insertReimbTypeHandler);
		app.delete("/reimb_types/:reimb_type_id", reimbTypeController.deleteReimbTypeHandler);
		
		//Handling requests for Reimbs
		app.get("/reimbs", reimbController.getAllReimbsHandler);
		app.get("/reimbs/:reimb_id", reimbController.getReimbByIdHandler);
		app.post("/reimbs", reimbController.insertReimbHandler);
		app.delete("/reimbs/:reimb_id", reimbController.deleteReimbHandler);
	}
	
}


//               __
//          (___()'`;
//          /,    /`
//          \\"--\\