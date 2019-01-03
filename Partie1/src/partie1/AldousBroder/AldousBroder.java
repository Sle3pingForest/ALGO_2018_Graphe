package partie1.AldousBroder;

import java.util.ArrayList;
import java.util.Random;

import partie1.graphes.Edge;
import partie1.graphes.Graph;

public class AldousBroder {

	protected Graph g ;
	protected Edge init;
	protected int sommetInit;
	
	public AldousBroder(Graph g){
		this.g = g;
	}
	
	public void choixSommetInit(){
		sommetInit = this.g.edges().get(0).getFrom();
	}
	
	public void choixChemin(){
		//on stock tous les sommets relier a sommetInit dans une arrayliste;
		ArrayList<Edge> list = this.g.adj(sommetInit);
		
		
		//on choisit le sommet
		int taille = list.size();
		Random r = new Random();
		int valeur = r.nextInt(taille);
		if(!this.g.adj(sommetInit).get(valeur).isUsed()){
			this.g.adj(sommetInit).get(valeur).setUsed(true);
			if(this.sommetInit == this.g.adj(sommetInit).get(valeur).getFrom() ){
				this.sommetInit = this.g.adj(sommetInit).get(valeur).getTo();
			}
			else{
				this.sommetInit = this.g.adj(sommetInit).get(valeur).getFrom();
			}
		}
		else{
			System.out.println("le sommet " + sommetInit + "est deja utilise");
		}
	}
	
	//public void
}
