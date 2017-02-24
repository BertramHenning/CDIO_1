package ui;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class TUI {
	
	Scanner valg = new Scanner(System.in);
	
	public String welcome() {
		System.out.println("Velkommen til bruger-administrationsmodulet.");
		System.out.println("Indtast 1 for at oprette en ny bruger.");
		System.out.println("Indtast 2 for at vis bruger.");
		System.out.println("Indtast 3 for at opdatere bruger.");
		System.out.println("Indtast 4 for at slette bruger.");
		System.out.println("Indtast 5 for at afslutte programmet");
		return valg.nextLine();
	}
	public int uID(){
		int out = 0;
		System.out.println("Indtast ID");
		out = valg.nextInt();
		valg.nextLine();
		return out;
	}
	public String uNavn(){
		String out = "";
		System.out.println("Indtast navn");
		out = valg.nextLine();
		return out;
	}
	public String cprNr(){
		String out = "";
		System.out.println("Indtast cpr-nr");
		out = valg.nextLine();
		return out;
	}
	public String antalRoller(){
		String out = "";
		System.out.println("Indtast antal roller");
		return out;
	}
	public String roles(){
		String out = "";
		System.out.println("Indtast hvilke roller du vil have");
		return out;
	}
	public String password(){
		String out = "";
		System.out.println("Indtast nyt password");
		return out;
	}
	public String opretBruger() {
		String out = "";
		out += uID();
		out += uNavn();
		out += cprNr();
		out += antalRoller();
		out += roles();
		return out; 
	}
	
	public void visBrugere(String brugere) {
		System.out.println(brugere);
	}
	
	public int opdaterBruger() {
		System.out.println("Indtast tal for at opdatere:");
		System.out.println("1 for navn");
		System.out.println("2 for password");
		System.out.println("3 for cpr-nr");
		System.out.println("4 for at tilføje roller");
		System.out.println("5 for at fjerne roller");
		int out = 0;
		out = valg.nextInt();
		valg.nextLine();
		
		return out;
	}
	public String opdaterRolle(Set<String> roles){
		String out = "";
		System.out.println("Dine roller: " + Arrays.toString(roles.toArray()));
		System.out.println("Hvilken vil du tilføje?");
		System.out.println("1: Admin, 2: Pharmacist, 3: Foreman, 4: Operator 5: Ikke flere roller");
		out = valg.nextLine();
		
		return out;
	}
	public String opdaterRolle2(Set<String>roles){
		String out = "";
		System.out.println("Dine roller: " + Arrays.toString(roles.toArray()));
		System.out.println("Hvilken vil du fjerne?");
		System.out.println("1: Admin, 2: Pharmacist, 3: Foreman, 4: Operator 5: Ikke flere roller");
		out = valg.nextLine();
		
		return out;
		
	}
	public int sletBruger() {
		System.out.println("Indtast ID for den bruger du ønsker at slette");
		return valg.nextInt();
	}
	
//	public void afslutProgram() {
//		
//	}
}
