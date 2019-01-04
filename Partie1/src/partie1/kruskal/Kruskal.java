package partie1.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import partie1.graphes.Edge;
import partie1.graphes.Graph;

public class Kruskal {
	
	protected Graph g ;
	protected ArrayList<Edge> listInit;
	protected ArrayList<Edge> listFinal;
	
	public Kruskal(Graph g){
		this.g = g;
		this.listInit = this.g.edges();
		listFinal = new ArrayList<>();
	}
	 
	public int find(int from){
		if(this.g.getTabParent().get(from) == from){
			return from;
		}
		else{
			return find(this.g.getTabParent().get(from));
		}
	}
	
	public void kruskal(){
		Collections.shuffle(listInit); // on aura une list melanger
		for( Edge e : listInit){
			if(find(e.getFrom()) != find(e.getTo())){
				this.listFinal.add(e);
				e.setUsed(true);
				this.g.getTabParent().put(find(e.getFrom()), find(e.getTo()));
			}
		}
	}

	public ArrayList<Edge> getListFinal() {
		return listFinal;
	}

	public void setListFinal(ArrayList<Edge> listFinal) {
		this.listFinal = listFinal;
	}
	
	
	public void affichage(){
		for(Edge e: getListFinal()){
			System.out.println(e.toString());
		}
	}
	

}
