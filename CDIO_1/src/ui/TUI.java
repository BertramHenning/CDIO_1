package ui;

import java.util.Scanner;

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
	
	public String opretBruger() {
		String out = "";
		System.out.println("Indtast dit ID");
		out += valg.nextLine();
		
		System.out.println("Indtast navn");
		out += valg.nextLine();
		
		System.out.println("Indtast cpr-nr");
		out += valg.nextLine();
		
		System.out.println("Indtast antal roller");
		out += valg.nextInt();
		
		System.out.println("Indtast hvilke roller du vil have");
		out += valg.nextLine();
		
		return out; 
	}
	
	public void visBrugere(String brugere) {
		System.out.println(brugere);
	}
	
//	public String opdaterBruger() {
//		
//	}
	
	public int sletBruger() {
		System.out.println("Indtast ID for den bruger du ønsker at slette");
		return valg.nextInt();
	}
	
//	public void afslutProgram() {
//		
//	}
}
