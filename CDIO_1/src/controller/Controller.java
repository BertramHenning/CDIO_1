package controller;

import java.util.ArrayList;
import ui.TUI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.NonPersistentDAO;
import dto.UserDTO;
import ui.TUI;

public class Controller {
	private Scanner sc;
	private IUserDAO users;
	private TUI tui;

	public Controller() {
		this.sc = new Scanner(System.in);
		this.users = new NonPersistentDAO();
		this.tui = new TUI();
	}

	public void start() {
		while(1<2){
			String input = tui.welcome();
			switch (input.charAt(0)-'0') {
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
				updateUser();
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
		
		UserDTO user = new UserDTO();
		try {
			user = users.getUser(tui.uID());
		} catch (DALException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			System.out.println("User findes ikke");
			return;
		}
		switch(tui.opdaterBruger()){
			case 1:	
				user.setUserName(tui.uNavn());
						Scanner ini = new Scanner(user.getUserName());
						String initials = "";
						while(ini.hasNext()){
							initials = initials + ini.next().charAt(0);
						}
						user.setIni(initials);
						ini.close();
				break;
			case 2: 	user.setPassword(tui.password());
				break;
			case 3:		user.setCpr(tui.cprNr());
				break;
			case 4:		addRole(user);	
				break;
			case 5:		removeRole(user);
				break;
			default:
				break;
	}
		try {
			users.updateUser(user);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		System.out.println("indtast �nsket userid mellem 11 og 99:");
		while(true){
			int i = sc.nextInt();
			if (i < 100 && i > 10){
				user.setUserId(i);
				sc.nextLine();
				break;
			}
			System.out.println("Pr�v igen:");
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
		
		System.out.println("indtast �nsket password:");
		user.setPassword(sc.nextLine());
		
		System.out.println("indtast cpr nr:");
		user.setCpr(sc.nextLine());

		System.out.println("hvilke roller vil du have?");
		Set<String> roles= new HashSet<String>();
		loop:
		while(true){
			System.out.println("dine roller: " + Arrays.toString(roles.toArray()));
			System.out.println("Hvilken vil du tilf�je?");
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
	public void addRole(UserDTO user){
		Set<String> roles= new HashSet<String>();
		roles.addAll(user.getRoles());
		loop:
		
		while(true){
			switch (tui.opdaterRolle(roles)) {
			case "1":
				roles.add("Admin");
				break;
			case "2":
				roles.add("Pharmacist");
				break;
			case "3":
				roles.add("Foreman");
				break;
			case "4":
				roles.add("Operator");
				break;
			case "5":
				List<String> roles2= new ArrayList<String>();
				roles2.addAll(roles);
				user.setRoles(roles2);
				break loop;
				
			default:
				break;
			}
		}

	}
	public void removeRole(UserDTO user){
		Set<String> roles= new HashSet<String>();
		roles.addAll(user.getRoles());
		loop:
		
		while(true){
			switch (tui.opdaterRolle2(roles)) {
			case "1":
				roles.remove("Admin");
				break;
			case "2":
				roles.remove("Pharmacist");
				break;
			case "3":
				roles.remove("Foreman");
				break;
			case "4":
				roles.remove("Operator");
				break;
			case "5":
				List<String> roles2= new ArrayList<String>();
				roles2.addAll(roles);
				user.setRoles(roles2);
				break loop;
				
			default:
				break;
			}
		}
		
		
	}
}
