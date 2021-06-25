package dfs2dconsole;

import java.util.Scanner;

public class dfs {
	public static void main(String[] args) throws InterruptedException {
		int x,y;
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue sur l'application DFS/BFS ! by CK.");
		int[][] tab = creerTab();
		
		System.out.println("on commence par quelle case ? (ecrire de maniere 'naturelle', 1 1 va aller dans la premiere case haut/gauche)");
		x=Integer.parseInt(sc.nextLine());
		y=Integer.parseInt(sc.nextLine());
		tab[x][y]=100000;
		affichertab(tab);
		String choix;
		System.out.println("ON FAIT QUOI ? 'dfs' / 'bfs' / 'bye' : ");
		
		choix = sc.nextLine();
		while (!choix.equals("bye")) {
			if (choix.equals("bfs")){
				while(checkSpaceLeft(tab)) {
					bfs(tab);
					modifierTab(tab);
					Thread.sleep(700);
					sauter5Ligne();
					affichertab(tab);
				}
				System.out.println("FINI ! ALLEZ SALUT");
				break;
			}
			
			else if (choix.equals("dfs")) {
				depfs(tab,x,y,1);
				System.out.println("fini ! ");
				break;
			}
			else if (choix.equals("bye")){
				System.out.println("au revoir");
			}
			else {
				System.out.println("saisie incorrect, retape chacal : ");
				choix = sc.nextLine();
			}
		}
		
	}
	
	
	public static void sauter5Ligne() {
		for (int i = 0; i<5;i++)
			System.out.println();
	}
	public static int[][] creerTab() {
		Scanner sc = new Scanner (System.in);
		int tailleX,tailleY;
		System.out.print("Nous travaillerons sur un tableau, quelle seront ses dimmensions ?(donne la hauteur) : "  );
		tailleX = Integer.parseInt(sc.nextLine());
		System.out.print("donne la largeur : ");
		tailleY = Integer.parseInt(sc.nextLine());
		
		//je fais un tableau une case plus grand parceque jai la flemme de gerer les outof bounds.
		
		int[][] tabAffiché = new int [tailleX+2][tailleY+2];
		
		// 25 % de chance qu'un mur pop
		for (int i = 0; i < tabAffiché.length; i++) {
			for (int j = 0; j < tabAffiché[0].length; j++) {
				if(Math.random()<0.25)
					tabAffiché[i][j]=-1;
			}
		}
		
		// un couvre les rebord de murs.
		for (int i=0;i<=tailleY+1;i++) {
			tabAffiché[0][i]=-1;
			tabAffiché[tailleX+1][i]=-1;
		}
		
		for (int j=0;j<=tailleX+1;j++) {
			tabAffiché[j][0]=-1;
			tabAffiché[j][tailleY+1]=-1;
		}

		return tabAffiché;
	}


	
	public static void affichertab(int[][] affiché) {
		// simple scan du tableau, et affichage en conséquence.
		for (int ligne = 0; ligne<affiché.length;ligne++) {
			for (int colonne = 0; colonne<affiché[0].length;colonne++) {
				switch (affiché[ligne][colonne]) {
					case -1:
						System.out.print("| ❌ |");
						break;
					case 0:
						System.out.print("|    |");
						break;
					case 100000:
						System.out.print("|  ■ |");
						break;	
					case 222 :
						System.out.print("| 🟧 |");
						break;	
					default:
						System.out.print("| 🟩 |"); 
				}
			}
	
			//affichage de la ligne de séparation; une case fait 6 '-', donc j'affiche (6 '-') * nbDeCase
			System.out.println();
			for(int k=0;k<affiché[0].length;k++) {
				System.out.print("------");
			}
			System.out.println();
		}
	}
	
	
	public static void transformerTab(int[][] tab) {
		// alors la, il fallait que jfasse ca tahu ... va voir bfs() tu vas capter.
		for (int ligne = 0; ligne<tab.length;ligne++) {
			for (int colonne = 0; colonne<tab[0].length;colonne++) {
				if (tab[ligne][colonne]==2)
					tab[ligne][colonne]=1;
			}
	
		}
	}
	
	
	public static void bfs (int[][] tab) {
	//jpeux pas juste transformer direct en 1 des que jpasse a coté; ducoup jle passe en 2. ce qui veux dire QUE APRES avoir scan tt le tableau il passeront en 1. 
	// explication : imagine je suis case 1/1. jvai modif la case en dessous tahu (la 2/1). et le scan il va repasser en 2/1 apres, donc il va modif la 3/1 etc etc. donc me faut
	// un maniere de dire au programme "c'est pas un 1 mais ca va le devenir". cf "transformertableau()" qui change les 2 en 1. 
		
		for (int ligne = 1; ligne<tab.length;ligne++) {
			for (int colonne = 1; colonne<tab[0].length;colonne++) {
				if (tab[ligne][colonne]==100000) {
					// ca check les 4 cases autours, j'ai pas trouvé moyen de rendre ca plus compact. 
					
					if (tab[ligne][colonne-1]!=-1 && tab[ligne][colonne-1]!=100000)
						tab[ligne][colonne-1]=2;
					if (tab[ligne][colonne+1]!=-1 && tab[ligne][colonne+1]!=100000)
						tab[ligne][colonne+1]=2;
					
					if (tab[ligne+1][colonne]!=-1 && tab[ligne+1][colonne]!=100000)
						tab[ligne+1][colonne]=2;
					if (tab[ligne-1][colonne]!=-1 && tab[ligne-1][colonne]!=100000)
						tab[ligne-1][colonne]=2;	
				}
			}
		}
	}
	// ca return k si le point a un point libre proche; ca return f sinon.
	
	public static void depfs (int[][] tab,int debLigne,int debColonne,int generation) throws InterruptedException {
			
		if (!checkSpaceLeft(tab)) {
			return;
		}	
	
		if (tab[debLigne][debColonne-1]==0) {
			tab[debLigne][debColonne-1]=100000;
			tab[debLigne][debColonne]=generation++;
			debColonne--;
			Thread.sleep(700);
			sauter5Ligne();
			affichertab(tab);
			depfs(tab,debLigne,debColonne,generation);
		}
		
		if (tab[debLigne][debColonne+1]==0) {
			tab[debLigne][debColonne+1]=100000;
			tab[debLigne][debColonne]=generation++;
			debColonne++;
			Thread.sleep(700);
			sauter5Ligne();
			affichertab(tab);
			depfs(tab,debLigne,debColonne,generation);
		}
	
		if (tab[debLigne+1][debColonne]==0) {
			tab[debLigne+1][debColonne]=100000;
			tab[debLigne][debColonne]=generation++;
			debLigne++;
			Thread.sleep(700);
			sauter5Ligne();
			affichertab(tab);
			depfs(tab,debLigne,debColonne,generation);
		}
		
		if (tab[debLigne-1][debColonne]==0) {
			tab[debLigne-1][debColonne]=100000;
			tab[debLigne][debColonne]=generation++;
			debLigne--;
			Thread.sleep(700);
			sauter5Ligne();
			affichertab(tab);
			depfs(tab,debLigne,debColonne,generation);
		}
					
					
		
		// pas d'issue je revien à la case d'avant (generation -1); et je refais ma fonction en reccursif.
		
		if (tab[debLigne][debColonne-1]==generation-1) {
			tab[debLigne][debColonne]=222;
			debColonne--;
			Thread.sleep(700);
			sauter5Ligne();
			affichertab(tab);
			generation--;
			depfs(tab,debLigne,debColonne,generation);
		}
		if (tab[debLigne][debColonne+1]==generation-1) {
			tab[debLigne][debColonne]=222;
			debColonne++;
			Thread.sleep(700);
			sauter5Ligne();
			affichertab(tab);
			generation--;
			depfs(tab,debLigne,debColonne,generation);
		}
		if (tab[debLigne+1][debColonne]==generation-1) {
			tab[debLigne][debColonne]=222;
			debLigne++;
			Thread.sleep(700);
			sauter5Ligne();
			affichertab(tab);
			generation--;
			depfs(tab,debLigne,debColonne,generation);
		}
		if (tab[debLigne-1][debColonne]==generation-1) {
			tab[debLigne][debColonne]=222;
			debLigne--;
			Thread.sleep(700);
			sauter5Ligne();
			affichertab(tab);
			generation--;
			depfs(tab,debLigne,debColonne,generation);
		}

	}
	
	// apres avoir tout scan, c'est LA que je veux changer les '2' en '1'. 
	public static void modifierTab(int[][] tab) {
		for (int ligne = 0; ligne<tab.length;ligne++) {
			for (int colonne = 0; colonne<tab[0].length;colonne++) {
				if (tab[ligne][colonne]==2) 
					tab[ligne][colonne]=100000;
			}
		}
	}
	
	public static boolean checkSpaceLeft(int[][] tab) {
		for (int ligne = 0; ligne<tab.length;ligne++) {
			for (int colonne = 0; colonne<tab[0].length;colonne++) {
				if (tab[ligne][colonne]==0)
					return true;
			}
		}
		return false;
	}
	
}
