package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.NonPersistentDAO;
import dto.UserDTO;

public class Controller {
	private Scanner sc;
	private IUserDAO users;

	public Controller() {
		this.sc = new Scanner(System.in);
		this.users = new NonPersistentDAO();
	}

	public void start() {
		while(1<2){
			System.out.println("vælg:");
			System.out.println("1, Opret bruger");
			System.out.println("2, Vis brugere");
			System.out.println("3, Opdater bruger");
			System.out.println("4, Slet brugere.");
			switch (sc.nextInt()) {
			case 1:
				UserDTO newUser = createUser();
				try {
					users.createUser(newUser);
				} catch (DALException e) {
					e.printStackTrace();
				}
				
				break;
			case 2:
				showUsers();
				break;
			case 3:
				System.out.println("lav en ny user der udskifter den anden med samme id");
				UserDTO user = createUser();
				UserDTO oldUser = null;
				try {
					oldUser = users.getUser(user.getUserId());
				} catch (DALException e) {
					e.printStackTrace();
					break;
				}
				System.out.println("Vil du udskifte " + oldUser + " med " + user + "?");
				System.out.println("tryk 1 for ja, 0 for nej");
				if(sc.nextInt() == 1){
					try {
						users.updateUser(user);
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 4:
				deleteUser();
				break;

			default:
				break;
			}
			System.out.println();
		}
		
	}

	private void deleteUser() {
		System.out.println("hvilken user id?");
		int id = sc.nextInt();
		try {
			users.deleteUser(id);
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updateUser() {
		// TODO Auto-generated method stub
		
	}

	private void showUsers() {
		try {
			System.out.println(users.getUserList().toString());
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private UserDTO createUser() {
		UserDTO user = new UserDTO();
		System.out.println("indtast ønsket userid mellem 11 og 99:");
		while(true){
			int i = sc.nextInt();
			if (i < 100 && i > 10){
				user.setUserId(i);
				sc.nextLine();
				break;
			}
			System.out.println("Prøv igen:");
		}
		System.out.println("indtast navn:");
		user.setUserName(sc.nextLine());
		
		Scanner ini = new Scanner(user.getUserName());
		String initials = "";
		while(ini.hasNext()){
			initials = initials + ini.next().charAt(0);
		}
		user.setIni(initials);
		ini.close();
		
		System.out.println("indtast ønsket password:");
		user.setPassword(sc.nextLine());
		
		System.out.println("indtast cpr nr:");
		user.setCpr(sc.nextLine());

		System.out.println("hvilke roller vil du have?");
		Set<String> roles= new HashSet<String>();
		loop:
		while(true){
			System.out.println("dine roller: " + Arrays.toString(roles.toArray()));
			System.out.println("Hvilken vil du tilføje?");
			System.out.println("1: Admin, 2: Pharmacist, 3: Foreman, 4: Operator 5: Ikke flere roller");
			switch (sc.nextInt()) {
			case 1:
				roles.add("Admin");
				break;
			case 2:
				roles.add("Pharmacist");
				break;
			case 3:
				roles.add("Foreman");
				break;
			case 4:
				roles.add("Operator");
				break;
			case 5:
				List<String> roles2= new ArrayList<String>();
				roles2.addAll(roles);
				user.setRoles(roles2);
				break loop;
				
			default:
				break;
			}
		}
		return user;
		
		
	}

}
