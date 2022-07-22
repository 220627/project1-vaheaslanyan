package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.AuthController;
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
	}
	
	
	
}


//               __
//          (___()'`;
//          /,    /`
//          \\"--\\