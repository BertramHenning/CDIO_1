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
import dal.SerialDAO;
import dto.UserDTO;

public class Controller {
	private IUserDAO users;
	private TUI tui;

	public Controller() {
		this.users = new SerialDAO();
		this.tui = new TUI();
	}

	public void start() {
		while(1<2){
			switch (tui.welcome()) {
			case 1:
				UserDTO newUser = createUser();
				try {
					users.createUser(newUser);
				} catch (DALException e) {
					System.out.println(e.getMessage());
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
			case 5:
				System.exit(0);
				break;

			default:
				break;
			}
			System.out.println();
		}
		
	}

	private void deleteUser() {
		int id = tui.uID();
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
			e.printStackTrace();
		}
}

	private void showUsers() {
		try {
			tui.visBrugere(users.getUserList().toString());
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private UserDTO createUser() {
		UserDTO user = new UserDTO();
		int id = tui.uID();
		user.setUserId(id);
		
		user.setUserName(tui.uNavn());
		
		Scanner ini = new Scanner(user.getUserName());
		String initials = "";
		while(ini.hasNext()){
			initials = initials + ini.next().charAt(0);
		}
		user.setIni(initials);
		ini.close();
		
		user.setPassword(generatePassword());
		
		user.setCpr(tui.cprNr());

		Set<String> roles= new HashSet<String>();
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
	public String generatePassword(){
		int antalTal = (int) (Math.random() * 2) + 2;
		int antalStoreBog = (int) (Math.random() * 2) + 2;
		int antalSmåBog = 8- antalTal - antalStoreBog;
		
		String[] SB = new String[antalStoreBog];
		
		for(int i = 0; i < SB.length; i++){
			String StoreB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			char a = StoreB.charAt((int) (Math.random() * 27));
			SB[i] ="" + a; 
		}
		String[] SmB = new String[antalSmåBog];
		
		for(int i = 0; i < SmB.length; i++){
			String SmåB = "abcdefghijklmnopqrstuvwxyz";
			char b = SmåB.charAt((int) (Math.random() * 27));
			SmB[i] = "" + b;
		}
		String[] Tal = new String[antalTal];
		
		for(int i = 0; i < Tal.length; i++){
			String tal = "0123456789";
			char c = tal.charAt((int) (Math.random() * 10));
			Tal[i] = "" + c;
		}
		String[] SBSmB = new String[SB.length + SmB.length];
		for(int i =0;i<SBSmB.length;i++){
		    SBSmB[i] = (i<SB.length)?SB[i]:SmB[i-SB.length];
		}
		
		String[] all = new String[SBSmB.length + Tal.length];
		for(int i = 0; i < all.length; i++){
			all[i] = (i < SBSmB.length)?SBSmB[i]:Tal[i-SBSmB.length];
		}
		
		for (int i = 0; i < all.length; i++) {
			float f = (float) Math.random() * all.length;
			int a = (int) f;

			String temp = all[i];
			all[i] = all[a];
			all[a] = temp;
		}
		return Arrays.toString(all);
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
