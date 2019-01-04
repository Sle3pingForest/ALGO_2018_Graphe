package partie1.graphes;
import java.io.*;


import java.util.*;
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
		/*int [] tab = new int[8];
		//tab = testKruskal();
		//tab = testAldousBroder();
		//tab = testWilson();
		for(int i = 0 ; i < 8 ; i ++){
			System.out.println("cas " + i  + "  nb de fois " + (double)tab[i]/10000 + "%");
		}*/

		int size = 20;
		int choixAlgo = 3; // 1 = kruskal , 2 = albousbroder , 3 = wilson
		Graph G = Graph.Grid(size,choixAlgo);
		Display d = new Display();
		d.setImage(G.toImage());
		System.out.println("appuyez sur une touche");
		new Scanner(System.in).nextLine();
		printLaby(G,size, "toto.tex");

		d.close();


	}
} 
