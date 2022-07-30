package com.revature;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import com.revature.controllers.ReimbController;
import com.revature.daos.ReimbDAO;

public class UpdateReimbStatusTest {
	
	static private ReimbController reimbController;
	static private ReimbDAO mockReimbDAO;
	
	@BeforeAll
	static public void setup() {
		reimbController = spy(new ReimbController());
		mockReimbDAO = spy(new ReimbDAO());
		
	}

	@Test
	public void testupdateReimbStatus() {
		
		when(mockReimbDAO.updateReimbStatus(anyInt(), anyInt())).thenReturn(true);
		
		assertTrue(mockReimbDAO.updateReimbStatus(1,2));
		
	}
	
}
