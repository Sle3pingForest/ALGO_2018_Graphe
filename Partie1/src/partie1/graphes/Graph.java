package partie1.graphes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.io.*;
import javax.swing.*;

import partie1.kruskal.Kruskal;

import java.awt.*;
import java.awt.image.*;

public class Graph{
   private ArrayList<Edge>[] adj;
   private int[] coordX;
   private int[] coordY;
   private final int V;
   protected HashMap<Integer,Integer> tabParent;
   private int E;

   @SuppressWarnings("unchecked")
   public Graph(int N)
         {
	   this.tabParent = new HashMap<>();
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
   
   

    public HashMap<Integer, Integer> getTabParent() {
	return tabParent;
}

public void setTabParent(int key, int val) {
	this.tabParent.put(key, val);
}


	static Graph example(){
	Graph g = new Graph(4);
	g.setCoordinate(0, 100,100);
	g.setCoordinate(1, 300,300);
	g.setCoordinate(2, 300,100);
	g.setCoordinate(3, 100,300);
	g.addEdge(new Edge(0,1));
	g.addEdge(new Edge(0,2));
	g.addEdge(new Edge(0,3));
	g.addEdge(new Edge(1,2));
	g.addEdge(new Edge(1,3));
	return g;
    }

    static Graph example2(){
    	Graph g = new Graph(16);
    	//g.setCoordinate(0, 50,50);
    	//g.setCoordinate(1, 50,100);
    	//g.setCoordinate(2, 50,150);
    	g.setCoordinate(3, 50,200);
    	
    	//g.setCoordinate(4, 100,50);
    	//g.setCoordinate(5, 100,100);
    	g.setCoordinate(6, 100,150);
    	g.setCoordinate(7, 100,200);

    	//g.setCoordinate(8, 150,50);
    	g.setCoordinate(9, 150,100);
    	g.setCoordinate(10, 150,150);
    	g.setCoordinate(11, 150,200);
    	
    	//g.setCoordinate(12, 200,50);
    	//g.setCoordinate(13, 200,100);
    	//g.setCoordinate(14, 200,150);
    	g.setCoordinate(15, 200,200);
    	
    	
    	g.addEdge(new Edge(9,10));
    	g.addEdge(new Edge(9,15));
    	g.addEdge(new Edge(9,6));
    	
    	g.addEdge(new Edge(3,7));
    	g.addEdge(new Edge(3,6));
    	g.addEdge(new Edge(6,7));
    	g.addEdge(new Edge(6,10));
    	g.addEdge(new Edge(7,11));
    	g.addEdge(new Edge(10,11));
    	g.addEdge(new Edge(11,15));
    	g.addEdge(new Edge(10,15));
    	

    	return g;
        }
    
    static Graph example3(){
    	Graph g = new Graph(16);
    	Graph g2 = new Graph(16);
    	//g.setCoordinate(0, 50,50);
    	//g.setCoordinate(1, 50,100);
    	//g.setCoordinate(2, 50,150);
    	g2.setCoordinate(3, 50,200);
    	
    	//g.setCoordinate(4, 100,50);
    	//g.setCoordinate(5, 100,100);
    	g2.setCoordinate(6, 100,150);
    	g2.setCoordinate(7, 100,200);

    	//g.setCoordinate(8, 150,50);
    	g2.setCoordinate(9, 150,100);
    	g2.setCoordinate(10, 150,150);
    	g2.setCoordinate(11, 150,200);
    	
    	//g.setCoordinate(12, 200,50);
    	//g.setCoordinate(13, 200,100);
    	//g.setCoordinate(14, 200,150);
    	g2.setCoordinate(15, 200,200);
    	
    	g.addEdge(new Edge(9,10));
    	g.addEdge(new Edge(9,15));
    	g.addEdge(new Edge(9,6));
    	
    	g.addEdge(new Edge(3,7));
    	g.addEdge(new Edge(3,6));
    	g.addEdge(new Edge(6,7));
    	g.addEdge(new Edge(6,10));
    	g.addEdge(new Edge(7,11));
    	g.addEdge(new Edge(10,11));
    	g.addEdge(new Edge(11,15));
    	g.addEdge(new Edge(10,15));
    	
    	Kruskal k = new Kruskal(g);
    	k.kruskal();
    	for( Edge e : k.getListFinal()){
    		g2.addEdge(e);
    	}
    	return g2;
    	
    }

    static Graph Grid(int n){
	Graph g = new Graph(n*n);
	int i,j;
	for (i = 0 ; i < n; i ++) 
	    for (j = 0 ; j < n; j ++) 
		g.setCoordinate(n*i+j, 50+(300*i)/n,50+(300*j)/n);

	for (i = 0 ; i < n; i ++) 
	    for (j = 0 ; j < n; j ++){
		if (i < n-1) 
		    g.addEdge(new Edge(n*i+j,n*(i+1)+j));
		if (j < n-1) 
		    g.addEdge(new Edge(n*i+j,n*i+j+1));
	    }
	return g;
    }
    

    public BufferedImage toImage(){
	BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
	Graphics2D g2d = image.createGraphics();
	g2d.setBackground(Color.WHITE);
	g2d.fillRect(0, 0, 400, 400);
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
