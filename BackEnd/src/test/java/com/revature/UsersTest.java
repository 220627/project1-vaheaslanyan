package com.revature;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import com.revature.daos.UserDAO;
import com.revature.models.User;

public class UsersTest {
	
	static private UserDAO mockUserDAO;
	
	@BeforeAll
	static public void setup() {
		mockUserDAO = spy(new UserDAO());
		
	}
	
	
	@Test
	public void testGetAllUsers() {
		
		ArrayList<User> mockUserList = mock(ArrayList.class);
		
		OngoingStubbing<ArrayList<User>> mockReturnedList = when(mockUserDAO.getAllUsers()).thenReturn(mockUserList);
		
		assertNotNull(mockReturnedList);
	}
	
//	@Test
//	public void testInsertUser() {
//		
//		User mockUser = spy(new User("user", "asdahfas", "Gago", "Drago", "gago@drago.com", 1));
//		
//		ArrayList <User> mockUserList = mock(ArrayList.class);
//		
//		mockUserList = mockUserDAO.getAllUsers();
//		//verify(mockUserDAO).getAllUsers();
//		
//		int preInsert = mockUserList.size();
//		
//		
//		//verify(mockUserDAO).insertUser(mockUser);
//		
//		mockUserList = mockUserDAO.getAllUsers();
//		
//		int postInsert = mockUserList.size();
//		
//		assertTrue(preInsert < postInsert);
//		
//		
//	}
	
	
}

