package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.SerialDAO;
import dto.UserDTO;

public class UserTest {

	IUserDAO users;
	UserDTO user, updatedUser;
	
	@Before
	public void setUp() throws Exception {
		user = new UserDTO();
		updatedUser = new UserDTO();
		users = new SerialDAO();
	}

	@After
	public void tearDown() throws Exception {
		user = null;
		updatedUser = null;
		users.deleteUser(100);
		users = null;
	}
	
	/**
	 * JUnit test 1
	 */
	@Test
	public void createUserTest() {
		user.setUserId(100);
		try {
			users.createUser(user);
		} catch (DALException e) {
			e.printStackTrace();
		}
		UserDTO unexpected = null;
		UserDTO actual = null;
		try {
			actual = users.getUser(100);
		} catch (DALException e) {
			e.printStackTrace();
		}
		assertNotEquals(unexpected, actual);
	}
	
	/**
	 * JUnit test 2
	 */
	@Test
	public void showUserTest(){
		user.setIni("ABC");
		user.setUserId(100);
		user.setUserName("Abc B C");
		List<String> roles = new ArrayList<String>();
		roles.add("Admin");
		user.setRoles(roles);
		try {
			users.createUser(user);
		} catch (DALException e) {
			e.printStackTrace();
		}
		String actual = "";
		try {
			actual = users.getUser(100).toString();
		} catch (DALException e) {
			e.printStackTrace();
		}
		String expected = "\nUserDTO [userId=100, userName=Abc B C, ini=ABC, roles=[Admin]]";
		assertEquals(expected, actual);

	}

	/**
	 * JUnit test 3
	 */
	@Test
	public void updateUserTest() {
		String expected = "DEF";
		String actual = null;
		user.setUserId(100);
		user.setUserName("ABC");
		try {
			users.createUser(user);
		} catch (DALException e) {
			e.printStackTrace();
		}
		updatedUser.setUserId(100);
		updatedUser.setUserName("DEF");
		try {
			users.updateUser(updatedUser);
		} catch (DALException e) {
			e.printStackTrace();
		}
		try {
			actual = users.getUser(100).getUserName();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual);
		
	}

	/**
	 * JUnit test 4
	 */
	@Test
	public void deleteUserTest() {
		user.setUserId(100);
		try {
			users.createUser(user);
		} catch (DALException e) {
			e.printStackTrace();
		}
		String expected = "Ingen user med den id";
		String actual = null;
		try {
			users.deleteUser(100);
		} catch (DALException e) {
			e.printStackTrace();
		}
		try {
			users.getUser(100);
		} catch (DALException e) {
			actual = e.getMessage();
		}
		assertEquals(expected, actual);
		// creates user 100 again so teardown can delete it
		try {
			users.createUser(user);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	/**
	 * JUnit test 5
	 */
	@Test
	public void rolesTest() {
		user.setUserId(100);
		user.addRole("Admin");
		user.addRole("Operator");
		try {
			users.createUser(user);
		} catch (DALException e) {
			e.printStackTrace();
		}
		String expected = "[Admin, Operator]";
		String actual = null;
		try {
			user = users.getUser(100);
		} catch (DALException e) {
			e.printStackTrace();
		}
		actual = user.getRoles().toString();
		
		assertEquals(expected, actual);
	}
	

}
