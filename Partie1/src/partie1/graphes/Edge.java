package partie1.graphes;
// edge => est une classe ARRETE
public class Edge
{
   int from;
   int to;
   boolean used;
    Edge(int x, int y)
    {
	this.from = x;
	this.to = y;
	this.used = false;
    }
    
    final int other(int v)
    {
	if (this.from == v) return this.to; else return this.from;
    }

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}    
}
