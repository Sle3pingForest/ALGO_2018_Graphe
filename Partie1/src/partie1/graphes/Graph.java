package partie1.graphes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.io.*;
import javax.swing.*;

import partie1.AldousBroder.AldousBroder;
import partie1.kruskal.Kruskal;
import partie1.wilson.Wilson;

import java.awt.*;
import java.awt.image.*;

public class Graph{


    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double width = screenSize.getWidth();
    public static double height = screenSize.getHeight();
	private ArrayList<Edge>[] adj;
	private int[] coordX;
	private int[] coordY;
	private final int V;
	protected HashMap<Integer,Integer> tabParent;
	protected HashMap<Integer,Boolean> tabVisited;
	private int E;
	public static ArrayList<Edge> listCouvrant;

	@SuppressWarnings("unchecked")
	public Graph(int N)
	{
		this.tabParent = new HashMap<>();
		this.tabVisited = new HashMap<>();
		listCouvrant = new ArrayList<>();
		this.V = N;
		this.E = 0;
		adj = (ArrayList<Edge>[]) new ArrayList[N];
		for (int v= 0; v < N; v++){
			tabParent.put(v, v);// on associe le parent qui est lui meme
			adj[v] = new ArrayList<Edge>();
		}
		coordX = new int[N];
		coordY = new int[N];
		for (int v= 0; v < N; v++)
			coordX[v] = 0;
		for (int v= 0; v < N; v++)
			coordY[v] = 0;
	}

	public int vertices()
	{
		return V;
	}

	public void setCoordinate(int i, int x, int y)
	{
		coordX[i] = x;
		coordY[i] = y;
	}


	public void addEdge(Edge e)
	{
		int v = e.from;
		int w = e.to;
		adj[v].add(e);
		adj[w].add(e);
	}

	public ArrayList<Edge> adj(int v)
	{
		return new ArrayList<Edge>(adj[v]);
	}

	// recupere larete qui a f et t en from et/ou to
	public Edge getEdge(int f, int t) {
		Edge edge = null;
		ArrayList<Edge> le = adj(f);
		for (Edge e : le) {
			if (  (e.getFrom() == f && e.getTo() == t)  || (e.getFrom() == t && e.getTo() == f)) 
				edge = e;
		}
		return edge;
	}

	public ArrayList<Edge> edges()
	{
		ArrayList<Edge> list = new ArrayList<Edge>();
		for (int v = 0; v < V; v++)
			for (Edge e : adj(v)) {
				if (e.from == v)
					list.add(e);
			}
		return list;
	}

	public HashMap<Integer, Boolean>  getsommetUse(){
		for(Edge e : edges()){
			tabVisited.put(e.getFrom(),false);
			tabVisited.put(e.getTo(), false);
		}
		return tabVisited;
	}

	public HashMap<Integer, Integer> getTabParent() {
		return tabParent;
	}

	public void setTabParent(int key, int val) {
		this.tabParent.put(key, val);
	}

	public HashMap<Integer, Boolean> getTabVisited() {
		return tabVisited;
	}

	public void setTabVisited(HashMap<Integer, Boolean> tabVisited) {

		this.tabVisited = tabVisited;
	}

	static Graph example(){
		Graph g = new Graph(4);
		Graph g2 = new Graph(4);
		g2.setCoordinate(0, 100,100);
		g2.setCoordinate(1, 300,300);
		g2.setCoordinate(2, 300,100);
		g2.setCoordinate(3, 100,300);
		
		g.addEdge(new Edge(0,1));
		g.addEdge(new Edge(0,2));
		g.addEdge(new Edge(0,3));
		g.addEdge(new Edge(1,2));
		g.addEdge(new Edge(1,3));
		
		algoKrusKal(g);
		for( Edge e : g.edges()){
			g2.addEdge(e);
		}
		return g2;
	}
	
	static Graph example2(){
		Graph g = new Graph(4);
		Graph g2 = new Graph(4);
		g2.setCoordinate(0, 100,100);
		g2.setCoordinate(1, 300,300);
		g2.setCoordinate(2, 300,100);
		g2.setCoordinate(3, 100,300);
		
		g.addEdge(new Edge(0,1));
		g.addEdge(new Edge(0,2));
		g.addEdge(new Edge(0,3));
		g.addEdge(new Edge(1,2));
		g.addEdge(new Edge(1,3));
		
		algoAlbousBroder(g);
		for( Edge e : g.edges()){
			g2.addEdge(e);
		}
		return g2;
	}
	
	static Graph example3(){
		Graph g = new Graph(4);
		Graph g2 = new Graph(4);
		g2.setCoordinate(0, 100,100);
		g2.setCoordinate(1, 300,300);
		g2.setCoordinate(2, 300,100);
		g2.setCoordinate(3, 100,300);
		
		g.addEdge(new Edge(0,1));
		g.addEdge(new Edge(0,2));
		g.addEdge(new Edge(0,3));
		g.addEdge(new Edge(1,2));
		g.addEdge(new Edge(1,3));
		algoWilson(g );
		for( Edge e : g.edges()){
			g2.addEdge(e);
		}
		return g2;
	}

	

	public static void algoKrusKal(Graph graph){
		Kruskal k = new Kruskal(graph);
		k.kruskal();
		listCouvrant = k.getListFinal();
	}

	public static void algoAlbousBroder(Graph graph ){
		AldousBroder ab = new AldousBroder(graph);
		ab.albousBroder();
		listCouvrant = ab.getListFinal();
	}

	public static void algoWilson(Graph graph ){
		Wilson al = new Wilson(graph);
		al.wilson();
		listCouvrant = al.getListFinal();
	}

	static Graph Grid(int n,int choixAlgo){
		Graph g = new Graph(n*n);
		Graph g2 = new Graph(n*n);
		int i,j;
		for (i = 0 ; i < n; i ++) 
			for (j = 0 ; j < n; j ++) 
				g2.setCoordinate(n*i+j, 50+((int)width*i)/n,50+((int)height*j)/n);

		for (i = 0 ; i < n; i ++) 
			for (j = 0 ; j < n; j ++){
				if (i < n-1) 
					g.addEdge(new Edge(n*i+j,n*(i+1)+j));
				if (j < n-1) 
					g.addEdge(new Edge(n*i+j,n*i+j+1));
			}
		if(choixAlgo == 1) {
			algoKrusKal(g);
		}
		else if(choixAlgo == 2) {
			algoAlbousBroder(g);
		}
		else if (choixAlgo == 3) {
			algoWilson(g );
		}
		for( Edge e : g.edges()){
			g2.addEdge(e);
		}
		return g2;
	}


	public BufferedImage toImage(){
        
		BufferedImage image = new BufferedImage((int)width,(int) height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.fillRect(0, 0, (int)width, (int)height);
		g2d.setColor(Color.BLACK);
		BasicStroke bs = new BasicStroke(2);
		g2d.setStroke(bs);
		// dessine les arêtes 
		for (Edge e: edges())
		{
			int i = e.from;
			int j = e.to;
			if (e.used)
				g2d.setColor(Color.RED);
			else
				g2d.setColor(Color.GRAY);

			g2d.drawLine(coordX[i], coordY[i], coordX[j], coordY[j]);
		}
		// dessine les sommets 
		for (int i = 0; i < V; i++)
		{
			g2d.setColor(Color.WHITE);
			g2d.fillOval(coordX[i]-15, coordY[i]-15,30,30);
			g2d.setColor(Color.BLACK);
			g2d.drawOval(coordX[i]-15, coordY[i]-15,30,30);
			g2d.drawString(Integer.toString(i), coordX[i], coordY[i]);
		}

		return image;
	}


	public void writeFile(String s)
	{
		try
		{                      
			PrintWriter writer = new PrintWriter(s, "UTF-8");
			writer.println("digraph G{");
			for (Edge e: edges())
				writer.println(e.from + "->" + e.to+";");
			writer.println("}");
			writer.close();
		}
		catch (IOException e)
		{
		}                                             
	}    

}
