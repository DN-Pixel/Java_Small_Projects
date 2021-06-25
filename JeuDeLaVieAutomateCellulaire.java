package jeudelavie;

import java.util.Scanner;

public class JeuDeLaVieAutomateCellulaire {
///une cellule morte possédant exactement trois voisines vivantes devient vivante (elle naît) ;
///une cellule vivante possédant deux ou trois voisines vivantes le reste, sinon elle meurt.
	public static void main (String[] Args) throws InterruptedException{
		
		char[][] jeu = creertab();
		
		do {
		modiftable(jeu);
		affichertab(jeu);
		Thread.sleep(250);
		for (int i = 0; i <=15; i++)
			System.out.println();
		}while(true);
		//not suppose to end tbh.
	}
	public static char[][] creertab() {
		Scanner sc = new Scanner (System.in);
		int larg,lon;
		int choixX,choixY;
		System.out.println("dimension tableau ? (larg/long) :"  );
		larg = Integer.parseInt(sc.nextLine());
		lon= Integer.parseInt(sc.nextLine());
		char[][] affiché = new char[larg+1][lon+1];
		
		for (int ligne = 0; ligne<larg+1;ligne++) {
			for (int colonne = 0; colonne<lon+1;colonne++) {
				affiché[ligne][colonne] = ' ';
			}
		}
	
		
		for (int ligne = 0; ligne<larg+1;ligne++) {
			for (int colonne = 0; colonne<lon+1;colonne++) {
				if(Math.random()<0.1)
				affiché[ligne][colonne] = '■';
			}
		}
		
		return affiché;
	}
	
	public static void affichertab(char[][] affiché) {
		
		for (int ligne = 0; ligne<affiché.length;ligne++) {
			for (int colonne = 0; colonne<affiché[0].length;colonne++) {
				System.out.print(affiché[ligne][colonne] + " ");
			}
			System.out.println();
		}
	}
	
	///une cellule morte possédant exactement trois voisines vivantes devient vivante (elle naît) ;
	///une cellule vivante possédant deux ou trois voisines vivantes le reste, sinon elle meurt.	
	public static void modiftable (char[][] affiché) {
		char[][] modif = lamortoucheche(affiché);
		for (int ligne = 0; ligne<affiché.length;ligne++) {
			for (int colonne = 0; colonne<affiché[0].length;colonne++) {
				affiché[ligne][colonne]=modif[ligne][colonne];
			}
		}
	}
	
	public static char[][] lamortoucheche (char[][] affiché) {
		
		char[][] modif = new char[affiché.length][affiché[0].length];
		
		for (int ligne = 0; ligne<affiché.length;ligne++) {
			for (int colonne = 0; colonne<affiché[0].length;colonne++) {
				modif[ligne][colonne] = 'W';
			}
		}
		//truc qui compte le nombre de truc en vie //
		for (int ligne = 1; ligne<affiché.length-1;ligne++) {
			for (int colonne = 1; colonne<affiché[0].length-1;colonne++) {
				
				int compteur = 0;
				//cas x 
				if (affiché[ligne][colonne]=='■') {
					
					for (int i = -1; i<2;i++) {
							if(affiché[ligne-1][colonne+i] == '■')
								compteur++;
						}
					if(affiché[ligne][colonne-1] == '■')
						compteur++;
					if(affiché[ligne][colonne+1] == '■')
						compteur++;
					for (int i = -1; i<2;i++) {
						if(affiché[ligne+1][colonne+i] == '■')
							compteur++;
					}
				
				if (compteur==2 || compteur == 3)
					modif[ligne][colonne]='■';
				else
					modif[ligne][colonne]=' ';
				}
				//cas pas x
				else {
					for (int i = -1; i<2;i++) {
						if(affiché[ligne-1][colonne+i] == '■')
							compteur++;
						}
					if(affiché[ligne][colonne-1] == '■')
						compteur++;
					if(affiché[ligne][colonne+1] == '■')
						compteur++;
					for (int i = -1; i<2;i++) {
						if(affiché[ligne+1][colonne+i] == '■')
							compteur++;
					}
					
					if (compteur==3)
						modif[ligne][colonne]='■';
					else
						modif[ligne][colonne]=' ';	
				}	
			}
		}
		return modif;	
	}
}

