package partie1.AldousBroder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import partie1.graphes.Edge;
import partie1.graphes.Graph;

public class AldousBroder {

	protected Graph g ;
	protected Edge init;
	protected int sommetInit;

	protected ArrayList<Edge> listInit;
	protected ArrayList<Edge> listFinal;

	public AldousBroder(Graph g){
		this.g = g;
		this.listFinal = new ArrayList<>();
		this.listInit = this.g.edges();
	}

	public void choixSommetInit(){
		//set tous les sommet  a no visite
		this.g.getsommetUse();
		//je recupere la premiere sommet du premier arret
		sommetInit = this.g.edges().get(0).getFrom();
		//comme je vas dessus je ses le sommet est visite

	}

	public void choixChemin(){
		//on stock tous les sommets relier a sommetInit dans une arrayliste;
		System.err.println("SOMMET DEPART " + sommetInit + "\n");
		ArrayList<Edge> list = this.g.adj(sommetInit);
		int taille = list.size();
		Random r = new Random();
		int valeur = r.nextInt(taille);
		int to = list.get(valeur).getTo();
		if (this.sommetInit == list.get(valeur).getTo())
			to = list.get(valeur).getFrom(); 
			

		this.g.getTabVisited().put(sommetInit, true);
		this.g.adj(sommetInit).get(valeur).setUsed(true);
		System.err.println("sommet a visite" + to);
		if(!this.g.getTabVisited().get(to)){
			listFinal.add(list.get(valeur));
		}
		else{
			System.err.println("SOMMET DEJA VISITE " + to);
		}
		if (this.sommetInit == this.g.adj(sommetInit).get(valeur).getTo())
			this.sommetInit = this.g.adj(sommetInit).get(valeur).getFrom(); 
		else 
			this.sommetInit = this.g.adj(sommetInit).get(valeur).getTo();

		
	}
	
	

	public void albousBroder(){
		
		boolean estTotal = false;
		choixSommetInit();
		while(!estTotal){
			choixChemin();
			Set entrySet = this.g.getTabVisited().entrySet();
			Iterator it = entrySet.iterator();
			boolean cont = true;
			while(it.hasNext() && cont){
				Map.Entry me = (Map.Entry)it.next();
			     System.out.println("Key is: "+me.getKey() + " & " + " value is: "+me.getValue());
			       if(!(boolean) me.getValue()){
			    	   estTotal = false;
			    	   cont = false;
			       }
			       else{
			    	   estTotal= true;
			       }
			}
		}
	}

	public ArrayList<Edge> getListFinal() {
		return listFinal;
	}

	public void setListFinal(ArrayList<Edge> listFinal) {
		this.listFinal = listFinal;
	}


}
