package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.SerialDAO;
import dto.UserDTO;

public class CreateUserTest {

	IUserDAO users;
	UserDTO user;
	
	@Before
	public void setUp() throws Exception {
		user = new UserDTO();
		users = new SerialDAO();
	}

	@After
	public void tearDown() throws Exception {
		user = null;
		users.deleteUser(68);
		users = null;
	}

	@Test
	public void createUserTest() {
		user.setUserId(68);
		try {
			users.createUser(user);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDTO unexpected = null;
		UserDTO actual = null;
		try {
			actual = users.getUser(68);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(unexpected, actual);
	}
	
	@Test
	public void ShowUser(){
		
		

	}

}
