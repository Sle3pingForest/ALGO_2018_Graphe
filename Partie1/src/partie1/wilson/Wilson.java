package partie1.wilson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import partie1.graphes.Edge;
import partie1.graphes.Graph;

public class Wilson {
	
	protected Graph g;
	protected int sommetInit;
	protected int sommetInitRandom;
	protected ArrayList<Integer> listVisite;
	protected ArrayList<Integer> listSommet;
	protected HashMap<Integer, ArrayList<Integer>> listMemoire;
	
	public Wilson(Graph g){
		this.g = g;
		listVisite = new ArrayList<Integer>();
		listSommet = new ArrayList<>();
		listMemoire = new HashMap<>();
		
		for (Integer i : g.getsommetUse().keySet()) {
			listMemoire.put(i, new ArrayList<Integer>());
		}
	}
	
	public void choixSommetInit(){
		sommetInit = this.g.edges().get(0).getFrom();
		this.listVisite.add(sommetInit);
		
		sommetInitRandom = this.g.edges().get(0).getTo();

	}
	
	public boolean choixChemin(){
		boolean sommetVisite = false;
		ArrayList<Edge> list = this.g.adj(sommetInitRandom);
		listSommet.add(sommetInitRandom);
		listMemoire.get(sommetInitRandom).add(listSommet.size()-1);
		
		int taille = list.size();
		Random r = new Random();
		int valeur = r.nextInt(taille);
		int to = list.get(valeur).getTo();
		if (this.sommetInitRandom == list.get(valeur).getTo())
			to = list.get(valeur).getFrom(); 
			

		if(!this.listVisite.contains(to)){
			listSommet.add(to);
		}
		else{
			System.err.println("SOMMET DEJA VISITE " + to);
			// si on rencontre un sommet deja visite 
			// on set a true pour lancer la suppression de boucle
			sommetVisite = true;
		}
		if (this.sommetInit == this.g.adj(sommetInit).get(valeur).getTo())
			this.sommetInit = this.g.adj(sommetInit).get(valeur).getFrom(); 
		else 
			this.sommetInit = this.g.adj(sommetInit).get(valeur).getTo();
		
		return sommetVisite;
	}
	
	
	
public void wilson(){
		
		boolean estTotal = false;
		choixSommetInit();
		while(!estTotal){
			
			boolean arret = choixChemin();
			
			if (arret) {
				supprDuplicat();
			}
			
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

	public void supprDuplicat() {
		
		boolean duplicat = true;
		
		while (duplicat) {
			
			int sommetDuplique = -1;
			int min = listSommet.size();
			
			// on recupere le premier sommet duplique
			for (Integer i : listMemoire.keySet()) {
				if (listMemoire.get(i).size() >1) {
					if ( listMemoire.get(i).get(0) < min) {
						sommetDuplique = i;
					}
				}
			}
			if (sommetDuplique != -1) {
				// on supprime entre la premiere et la derniere occurence de sommet duplique
				ArrayList<Integer> l = listMemoire.get( sommetDuplique );
				int last = l.get( l.size() -1 );
				for (int i = min ; i < last ; i++) {
					listSommet.remove(min+1);
				}
				// on supprime lhistorique du sommet duplique
				listMemoire.put(sommetDuplique, new ArrayList<Integer>());
			} else {
				// on a pas trouve de sommet duplique on sarrete
				duplicat = false;
			}
		}
		
	}
	
	
}
