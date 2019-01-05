package partie1.graphes;
import java.io.*;


import java.util.*;

import org.omg.CosNaming.NamingContextPackage.AlreadyBound;

import partie1.AldousBroder.AldousBroder;
import partie1.kruskal.Kruskal;
import partie1.wilson.Wilson;
public class Test{


	public static void printLaby(Graph G, int size, String file){
		{
			/* suppose que G est une grille de taille size x size et 
           crée un .tex qui contient le labyrinthe correspondant */

			try
			{                      
				PrintWriter writer = new PrintWriter(file, "UTF-8");
				writer.println("\\documentclass{article}\\usepackage{tikz}\\begin{document}");
				writer.println("\\begin{tikzpicture}");

				for (int i = 0; i < size; i++)
					for (int j = 0; j < size; j++)
					{			
						writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , j));
						writer.println("\\draw (0.1,0.1) -- (0.4,0.1);");
						writer.println("\\draw (0.6,0.1) -- (0.9,0.1);");
						writer.println("\\draw (0.1,0.9) -- (0.4,0.9);");
						writer.println("\\draw (0.6,0.9) -- (0.9,0.9);");
						writer.println("\\draw (0.1,0.1) -- (0.1, 0.4);");
						writer.println("\\draw (0.1,0.6) -- (0.1, 0.9);");
						writer.println("\\draw (0.9,0.1) -- (0.9,0.4);");
						writer.println("\\draw (0.9,0.6) -- (0.9,0.9);");
						writer.println("\\end{scope}");
					}

				/* bord */
				for (int i = 0; i < size; i++)
				{
					writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , 0));
					writer.println("\\draw(0.4,0.1) -- (0.6, 0.1);");
					writer.println("\\end{scope}");			
					writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , size-1));
					writer.println("\\draw(0.4,0.9) -- (0.6, 0.9);");
					writer.println("\\end{scope}");
					if (i > 0)
					{
						writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", 0 , i));
						writer.println("\\draw(0.1,0.4) -- (0.1, 0.6);");
						writer.println("\\end{scope}");

					}
					if (i < size - 1)
					{
						writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", size -1 , i));
						writer.println("\\draw(0.9,0.4) -- (0.9, 0.6);");
						writer.println("\\end{scope}");

					}
					writer.println("\\draw (0,0.4) -- (0.1, 0.4);");
					writer.println("\\draw (0,0.6) -- (0.1, 0.6);");
					writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.4)  -- ++ (-0.1, 0); ", size , size -1));
					writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.6)  -- ++ (-0.1, 0); ", size , size -1));

				}


				for (Edge e: G.edges())
				{
					int i = e.from % size;
					int j = e.from / size;
					writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , j));
					if (e.to == e.from + size){
						/* arête verticale */

						if (!e.isUsed())
						{
							writer.println("\\draw (0.4,0.9) -- (0.6,0.9);");
							writer.println("\\draw (0.4,1.1) -- (0.6,1.1);");			    			    
						}
						else
						{
							writer.println("\\draw (0.4,0.9) -- (0.4,1.1);");
							writer.println("\\draw (0.6,0.9) -- (0.6,1.1);");			    			    
						}
					}
					else{
						/* arête horizontale */

						if (!e.isUsed())
						{
							writer.println("\\draw (0.9,0.4) -- (0.9,0.6);");
							writer.println("\\draw (1.1,0.4) -- (1.1,0.6);");			    			    
						}
						else
						{
							writer.println("\\draw (0.9,0.4) -- (1.1,0.4);");
							writer.println("\\draw (0.9,0.6) -- (1.1,0.6);");			    			    
						}
					}
					writer.println("\\end{scope}");
				}
				writer.println("\\end{tikzpicture}");
				writer.println("\\end{document}");
				writer.close();
			}
			catch (IOException e)
			{
			}                                             
		}    



	}	

	public static int[] testKruskal(){

		int[] tabProba = new int[8];
		for(int i = 0 ; i < 8 ; i ++){
			tabProba[i] =0;
		}
		int i = 0;
		while(i < 1000000){

			Graph G = Graph.example();
			int cas = checkCas(G);
			switch(cas) {
			case 0:
				tabProba[cas]++;
				break;
			case 1:
				tabProba[cas]++;
				break;
			case 2:
				tabProba[cas]++;
				break;
			case 3:
				tabProba[cas]++;
				break;
			case 4:
				tabProba[cas]++;
				break;
			case 5:
				tabProba[cas]++;
				break;
			case 6:
				tabProba[cas]++;
				break;
			case 7:
				tabProba[cas]++;
				break;
			}

			i++;
		}
		return tabProba;
	}

	public static int[] testAldousBroder(){

		int[] tabProba = new int[8];
		for(int i = 0 ; i < 8 ; i ++){
			tabProba[i] =0;
		}
		int i = 0;
		while(i < 1000000){

			Graph G = Graph.example2();
			int cas = checkCas(G);
			switch(cas) {
			case 0:
				tabProba[cas]++;
				break;
			case 1:
				tabProba[cas]++;
				break;
			case 2:
				tabProba[cas]++;
				break;
			case 3:
				tabProba[cas]++;
				break;
			case 4:
				tabProba[cas]++;
				break;
			case 5:
				tabProba[cas]++;
				break;
			case 6:
				tabProba[cas]++;
				break;
			case 7:
				tabProba[cas]++;
				break;
			}

			i++;
		}
		return tabProba;
	}
	
	public static double testCulDeSac(int methode) {
		int j = 0;
		double i = 0;
		int boucle = 1000;
		int taille = 20;
		while(j < boucle){
			Graph g = Graph.GridSansAlgo(taille);
			
			switch(methode) {
			case 1:
				Wilson w = new Wilson(g);
				w.wilson();
				break;
			case 2:
				Kruskal k = new Kruskal(g);
				k.kruskal();
				break;
			case 3:
				AldousBroder a = new AldousBroder(g);
				a.albousBroder();
				break;
			}
			i += culDeSac(g, taille);
			//System.out.println(i);
			j++;
		}
		return i / boucle;
	}
	
	//laby
	public static int culDeSac(Graph g, int taille) {
		
		int culDeSac = 0;
		
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				int nbCouvrant = 0;
				for (Edge e : g.adj(    i*taille  +  j  )) {
					if (e.isUsed()) nbCouvrant++;
				}
				if (nbCouvrant == 1) culDeSac++;
			}
		}
		return culDeSac;
	}
	
	
	// calcul la distance entre la sortie et lentree dun labyrinthe 20*20
		public static int distanceSortie(Graph g) {
			
			int longueur = 0;
			/*
			Wilson w = new Wilson(g);
			w.wilson(20, 4);
			
			for (Integer i : w.getListSommet()) {
				System.out.print(i + " - "  );
			}
			
			System.out.println();
			return w.getListSommet().size();*/
			//longueur = sortieLabyrinthe(g, );
			
			return longueur;
		}
		
		public static int sortieLabyrinthe(Graph g, int taille) {
		
			/*
			int sommetCourant = 4;
			int sommetInit = 20;
			ArrayList<Integer> listSommet = new ArrayList<>();
			
			Wilson w = new Wilson(g);
			return w.distanceSortie(20, 4);
			*/
			
			int[][] tab = new int[taille][taille];
			boolean trouve = false;

			// initialise les cases a -1 qui signifie pas encore calcule
			for(int v=0;v<tab.length;v++) {
				Arrays.fill(tab[v], -1);
			}

			tab[0][taille - 2] = 0;
			remplirTableau(tab, 0, taille - 2, taille, g);
			
			for (int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab[0].length; j++) {
					System.out.print(tab[i][j] + " ");
				}
				System.out.println();
			}
			return tab[taille-1][0];
			
		}
		
		// cherche un chemin du monstre vers le heros en valuant les cases
		// la valuation correspond au nombre de deplacement necessaire en partant du heros
		public static void remplirTableau(int[][] tab,int x,int y, int taille, Graph g) {
			int hauteur = taille, longueur = taille;
			boolean faire[] = new boolean[4];
			// tableau qui indique si la case a deja ete value
			Arrays.fill(faire, false);

			
					// regarde pour chaque direction s'il existe un chemin dont la case n'a pas deja ete value
					if ( g.getEdge(x * taille + y, x * (taille + 1) + y ) != null 
							&& g.getEdge(x * taille + y, x * (taille + 1) + y ).isUsed() 
							&&  tab[x+1][y] == -1) {
						tab[x+1][y] = tab[x][y] + 1;
						faire[0] = true;
					}
					if ( g.getEdge(x * taille + y, x *  (taille - 1) + y  ) != null 
							&& g.getEdge(x * taille + y, x *  (taille - 1) + y  ).isUsed() 
							&& tab[x-1][y] == -1) {
						tab[x-1][y] = tab[x][y] + 1;
						faire[1] = true;
					}
					if (  g.getEdge(x * taille + y, x *  (taille ) + y + 1 ) != null 
							&& g.getEdge(x * taille + y, x *  (taille ) + y + 1 ).isUsed()
							&& tab[x][y+1] == -1) {
						tab[x][y+1] = tab[x][y] + 1;
						faire[2] = true;
					}
					if ( g.getEdge(x * taille + y, x *  (taille ) + y - 1 ) != null 
							&& g.getEdge(x * taille + y, x *  (taille ) + y - 1 ).isUsed() 
							&& tab[x][y-1] == -1) {
						tab[x][y-1] = tab[x][y] + 1;
						faire[3] = true;
					}

					if (faire[0] ) remplirTableau(tab, x+1, y, taille, g);
					if (faire[1] ) remplirTableau(tab, x-1, y, taille, g);
					if (faire[2] ) remplirTableau(tab, x, y+1, taille, g);
					if (faire[3] ) remplirTableau(tab, x, y-1, taille, g);
		}
			
		
	
	
	
	

	public static int[] testWilson(){

		int[] tabProba = new int[8];
		for(int i = 0 ; i < 8 ; i ++){
			tabProba[i] =0;
		}
		int i = 0;
		while(i < 1000000){

			Graph G = Graph.example3();
			int cas = checkCas(G);
			switch(cas) {
			case 0:
				tabProba[cas]++;
				break;
			case 1:
				tabProba[cas]++;
				break;
			case 2:
				tabProba[cas]++;
				break;
			case 3:
				tabProba[cas]++;
				break;
			case 4:
				tabProba[cas]++;
				break;
			case 5:
				tabProba[cas]++;
				break;
			case 6:
				tabProba[cas]++;
				break;
			case 7:
				tabProba[cas]++;
				break;
			}

			i++;
		}
		return tabProba;
	}

	public static int checkCas(Graph g) {
		int k = 0;
		if (g.getEdge(0, 2).isUsed() && g.getEdge(0, 3).isUsed() && g.getEdge(0,1).isUsed()){
			k =  0;
		}
		else if (g.getEdge(0, 2).isUsed() && g.getEdge(2, 1).isUsed() && g.getEdge(1,3 ).isUsed()){
			k = 1;
		}
		else if (g.getEdge(0, 3).isUsed() && g.getEdge(3, 1).isUsed() && g.getEdge(1,2 ).isUsed()){
			k = 2;
		}
		else if (g.getEdge(0, 1).isUsed() && g.getEdge(1, 3).isUsed() && g.getEdge(1,2).isUsed()){
			k = 3;
		}
		else if (g.getEdge(0, 2).isUsed() && g.getEdge(0, 3).isUsed() && g.getEdge(1,2).isUsed()){
			k = 4;
		}
		else if (g.getEdge(0, 2).isUsed() && g.getEdge(0, 3).isUsed() && g.getEdge(1,3 ).isUsed()){
			k = 5;
		}
		else if (g.getEdge(0, 3).isUsed() && g.getEdge(0, 1).isUsed() && g.getEdge(1,2 ).isUsed()){
			k = 6;
		}
		else if (g.getEdge(0, 2).isUsed() && g.getEdge(0, 1).isUsed() && g.getEdge(1,3).isUsed()){
			k = 7;
		}

		return k;
	}

	public static void main(String[] args) {
		int size = 5;
		//Graph G = Graph.Grid(size);
		//Graph G = Graph.example();
		//Graph G = Graph.example2();
		//Graph G2 = Graph.example4();
		/*
		Graph G = Graph.GridSansAlgo(size);
		Wilson w = new Wilson(G);
		w.wilson();
		System.out.println(sortieLabyrinthe(G, size) );
		*/
		/*
		int [] tab = new int[8];
		//tab = testKruskal();
		//tab = testAldousBroder();
		tab = testWilson();
		for(int i = 0 ; i < 8 ; i ++){
			System.out.println("cas " + i  + "  nb de fois " + (double)tab[i]/10000 + "%");
		}
		*/
		
		System.out.println(" WILSON   " + testCulDeSac(1));
		
		System.out.println(" KRUSKAL  " + testCulDeSac(2));
/*
		Display d = new Display();
		d.setImage(G.toImage());
		System.out.println("appuyez sur une touche");
		new Scanner(System.in).nextLine();
		//d.close();
		printLaby(G,size, "toto.tex");

*/

	}
} 
