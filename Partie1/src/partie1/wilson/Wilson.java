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
	
	/**
	 * liste des sommets deja visites
	 */
	protected ArrayList<Integer> listVisite;
	
	/**
	 * liste des sommets pas encore visites
	 */
	protected ArrayList<Integer> listNonVisite;
	
	/**
	 * liste des sommets de la marche aleatoire
	 */
	protected ArrayList<Integer> listSommet;
	
	/**
	 * liste des aretes utilises
	 */
	protected ArrayList<Edge> listFinal;
	
	/**
	 * historique qui pour chaque sommet indique ses occurences
	 */
	protected HashMap<Integer, ArrayList<Integer>> listMemoire;

	public Wilson(Graph g){
		this.g = g;
		listVisite = new ArrayList<Integer>();
		listNonVisite = new ArrayList<>();
		listSommet = new ArrayList<>();
		listFinal = new ArrayList<>();
		listMemoire = new HashMap<>();

		for (Integer i : g.getsommetUse().keySet()) {
			listMemoire.put(i, new ArrayList<Integer>());
			listNonVisite.add(i);
		}
	}

	public void choixSommetInit(){
		sommetInit = this.g.edges().get(0).getFrom();
		this.listVisite.add(sommetInit);
		supprSommetNonVisite(sommetInit);

		sommetInitRandom = this.g.edges().get(0).getTo();
	}
	


	public boolean choixSommetNonVisite() {
		boolean reste = false;

		if (listNonVisite.size() > 0) {
			reste = true;
			int taille = listNonVisite.size();
			Random r = new Random();
			int valeur = r.nextInt(taille);
			sommetInitRandom = listNonVisite.get(valeur);
		}
		return reste;
	}
	
	public void setSommetInitRandom(int i) {
		sommetInitRandom = i;
	}

	// supprime de la listeNonVisite le sommet de valeur i
	public void supprSommetNonVisite(int i) {
		boolean suppr = false;
		int k = 0;
		while (!suppr && k < listNonVisite.size()) {
			if (listNonVisite.get(k) == i) {
				listNonVisite.remove(k);
				suppr = true;
			}
			k++;
		}
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


		if(this.listVisite.contains(to)){
			listSommet.add(to);
			sommetVisite = true;
		}
		
		if (this.sommetInitRandom == this.g.adj(sommetInitRandom).get(valeur).getTo())
			this.sommetInitRandom = this.g.adj(sommetInitRandom).get(valeur).getFrom(); 
		else 
			this.sommetInitRandom = this.g.adj(sommetInitRandom).get(valeur).getTo();

		return sommetVisite;
	}



	public void wilson(){

		boolean estTotal = false;
		choixSommetInit();
		while(!estTotal){

			boolean arret = choixChemin();

			// si on a rencontre un sommet deja visite
			if (arret) {
				// on supprime les boucles
				supprDuplicat();

				// on ajoute les sommets dans la liste visite 
				// et on les enleve de la liste non visite 
				//System.out.println(" AJOUT EDGE  \n " + listSommet);
				for (int i = 0; i < listSommet.size() -1; i++) {
					int k = listSommet.get(i);
					int l = listSommet.get(i+1);
					listVisite.add(k);
					listVisite.add(l);
					listFinal.add(g.getEdge(k, l));
					g.getEdge(k, l).setUsed(true);
					//System.out.println(g.getEdge(k, l));
				}
				//System.out.println();
				for (Integer i : listVisite) {
					if (listNonVisite.contains(i)) supprSommetNonVisite(i);
				}
				// on choisit un nouveau sommet non visite
				// s'il n y a plus de sommet non visite on sarrete
				if (!choixSommetNonVisite() )  {
					estTotal = true;
				} else {
					// On reset les list car on va faire un nouveau
					// tour de marche aleatoire
					resetListMemoire();
					resetListSommet();
				}
			}


		}
	}
	
	


	// supprime les boucles et garde les sommets non duplique dans la listSommet
	// qui vont etre ajoute dans la liste des sommets visite
	public void supprDuplicat() {

		boolean duplicat = true;
		int diff = 0;
		while (duplicat) {

			int sommetDuplique = -1;
			int min = listSommet.size();

			// on recupere le premier sommet duplique
			for (Integer i : listMemoire.keySet()) {
				if (listMemoire.get(i).size() > 1) {
					if ( listMemoire.get(i).get(0) < min) {
						sommetDuplique = i;
						min = listMemoire.get(i).get(0);
					}
				}
			}
			if (sommetDuplique != -1) {
				// on supprime entre la premiere et la derniere occurence de sommet duplique
				ArrayList<Integer> l = listMemoire.get( sommetDuplique );
				int last = l.get( l.size() -1 );
				for (int i = min  ; i < last ; i++) {
					listSommet.remove(min +1);
				}
				// on supprime lhistorique 
				resetListMemoire();
				
				// on reconstruit lhistorique sans les elements enleves
				makeListeMemoire();
				
			} else {
				// on a pas trouve de sommet duplique on sarrete
				duplicat = false;
			}
		}

	}
	
	
	public void makeListeMemoire() {
		int compteur =0;
		for (int i : listSommet) {
			listMemoire.get(i).add(compteur);
			compteur++;
		}
	}

	public void resetListMemoire() {
		for (Integer i : g.getsommetUse().keySet()) {
			listMemoire.put(i, new ArrayList<Integer>());
		}
	}
	
	public void resetListSommet() {
		listSommet = new ArrayList<>();
	}

	public  ArrayList<Integer> getListSommet() {
		return listSommet;
	}

	public ArrayList<Edge> getListFinal() {
		return listFinal;
	}
	
	/**
	 * affichage pour tester le comportement de la suppression des sommets de la marche
	 */
	public void affiche() {
		System.out.println(listSommet);
		for (Integer i : listMemoire.keySet()) {
			System.out.print(i + "  :");
			for (Integer k : listMemoire.get(i)) {
				System.out.print(k + ", " );
			}
			System.out.println();
		}
		System.out.println();
	}

	
	
	// -------------------------------------------- PARTIE LABYRINTHE ------------------------------------------------------------ //
	
	
	public void wilson(int somm, int depart){

		boolean estTotal = false;
		choixSommetInit(somm);

		int m = 0;
		boolean arret = choixChemin(depart);
		while(!estTotal){

			if (m != 0) arret = choixChemin();
			// si on a rencontre un sommet deja visite
			if (arret) {
				// on supprime les boucles
				supprDuplicat();

				// on ajoute les sommets dans la liste visite 
				// et on les enleve de la liste non visite 
				//System.out.println(" AJOUT EDGE  \n " + listSommet);
				for (int i = 0; i < listSommet.size() -1; i++) {
					int k = listSommet.get(i);
					int l = listSommet.get(i+1);
					listVisite.add(k);
					listVisite.add(l);
					listFinal.add(g.getEdge(k, l));
					g.getEdge(k, l).setUsed(true);
					//System.out.println(g.getEdge(k, l));
				}
				//System.out.println();
				for (Integer i : listVisite) {
					if (listNonVisite.contains(i)) supprSommetNonVisite(i);
				}
				// on choisit un nouveau sommet non visite
				// s'il n y a plus de sommet non visite on sarrete
				if ( sommetInitRandom == sommetInit )  {
					estTotal = true;
				} else {
					// On reset les list car on va faire un nouveau
					// tour de marche aleatoire
					resetListMemoire();
					resetListSommet();
				}
			}
			m++;
		}
	}
	
	
	public void choixSommetInit(int i){
		sommetInit = i;
		this.listVisite.add(sommetInit);
		supprSommetNonVisite(sommetInit);
		
		sommetInitRandom = this.g.edges().get(0).getTo();
	}

	
	public boolean choixChemin(int i){
		sommetInitRandom = i;
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


		if(this.listVisite.contains(to)){
			listSommet.add(to);
			sommetVisite = true;
		}
		
		if (this.sommetInitRandom == this.g.adj(sommetInitRandom).get(valeur).getTo())
			this.sommetInitRandom = this.g.adj(sommetInitRandom).get(valeur).getFrom(); 
		else 
			this.sommetInitRandom = this.g.adj(sommetInitRandom).get(valeur).getTo();

		return sommetVisite;
	}
	
	

	public int distanceSortie(int somm, int depart){
		boolean estTotal = false;
		choixSommetInit(somm);
		
		int longueur = 0;
		int m = 0;
		boolean arret = choixChemin(depart);
		while(!estTotal){

			if (m != 0) arret = choixChemin();
			
			if (!listSommet.contains(sommetInitRandom)) longueur++;
			// si on a rencontre un sommet deja visite
			if (arret) {
				if ( sommetInitRandom == sommetInit )  {
					estTotal = true;
				}
			}
			m++;
		}
		System.out.println();
		return longueur;
	}



}
