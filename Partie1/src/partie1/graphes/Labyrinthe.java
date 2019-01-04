package partie1.graphes;

import java.util.ArrayList;

public class Labyrinthe {
	
	public Graph g ;
	public ArrayList<Edge> chemins ;
	
	
	public Labyrinthe(int taille){
		Graph G = Graph.Grid(taille);
		this.g = G;
		this.chemins = new ArrayList<>();
		this.chemins = this.g.listCouvrant;
	}

}
